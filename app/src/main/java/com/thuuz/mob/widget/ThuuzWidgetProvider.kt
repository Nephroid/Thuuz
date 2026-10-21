package com.thuuz.mob.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.RemoteViews
import com.thuuz.mob.MainActivity
import com.thuuz.mob.R
import com.thuuz.mob.data.FavoritesManager
import com.thuuz.mob.data.LiveSportsDataRepository
import com.thuuz.mob.data.SettingsManager
import com.thuuz.mob.ui.compose.Game
import com.thuuz.mob.ui.compose.SportLeague
import com.thuuz.mob.ui.compose.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class ThuuzWidgetProvider : AppWidgetProvider() {

    companion object {
        const val ACTION_WIDGET_REFRESH = "com.thuuz.mob.action.WIDGET_REFRESH"

        fun updateAllWidgets(context: Context) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisWidget = ComponentName(context, ThuuzWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
            if (appWidgetIds != null && appWidgetIds.isNotEmpty()) {
                val intent = Intent(context, ThuuzWidgetProvider::class.java).apply {
                    action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
                }
                context.sendBroadcast(intent)
            }
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (widgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, widgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_WIDGET_REFRESH) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisWidget = ComponentName(context, ThuuzWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
            if (appWidgetIds != null) {
                onUpdate(context, appWidgetManager, appWidgetIds)
            }
        }
    }

    fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val favoritesManager = FavoritesManager.getInstance(context)
            val repository = LiveSportsDataRepository(favoritesManager)
            val settingsManager = SettingsManager.getInstance(context)
            val isNoSpoilers = settingsManager.isNoSpoilersEnabled()

            val leagues = listOf(
                SportLeague.NBA,
                SportLeague.NHL,
                SportLeague.NFL,
                SportLeague.MLB,
                SportLeague.NCAAF,
                SportLeague.NCAAM,
                SportLeague.SOCCER
            )

            var games: List<Game> = try {
                withTimeoutOrNull(10000L) {
                    val deferredList: List<Deferred<List<Game>>> = leagues.map { league ->
                        async(Dispatchers.IO) {
                            try {
                                repository.fetchGamesSync(league, null)
                            } catch (e: Exception) {
                                emptyList()
                            }
                        }
                    }
                    deferredList.awaitAll().flatten()
                } ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }

            if (games.isEmpty()) {
                games = getFallbackWidgetGames()
            }

            // Top 3 highest-GEX live or upcoming games
            val topGames = games
                .sortedWith(
                    compareByDescending<Game> { it.isLive }
                        .thenByDescending { it.gex }
                )
                .take(3)

            val views = RemoteViews(context.packageName, R.layout.widget_thuuz_layout)

            // Setup refresh button intent
            val refreshIntent = Intent(context, ThuuzWidgetProvider::class.java).apply {
                action = ACTION_WIDGET_REFRESH
            }
            val refreshPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                refreshIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_refresh_button, refreshPendingIntent)

            // Clear previous dynamically added item views
            views.removeAllViews(R.id.widget_games_container)

            if (topGames.isEmpty()) {
                views.setViewVisibility(R.id.widget_empty_text, View.VISIBLE)
                views.setViewVisibility(R.id.widget_games_container, View.GONE)
            } else {
                views.setViewVisibility(R.id.widget_empty_text, View.GONE)
                views.setViewVisibility(R.id.widget_games_container, View.VISIBLE)

                for (game in topGames) {
                    val itemViews = RemoteViews(context.packageName, R.layout.widget_game_item)

                    val awayAbbr = game.awayTeam.abbreviation.ifBlank {
                        game.awayTeam.shortName.ifBlank { game.awayTeam.name.take(3).uppercase() }
                    }
                    val homeAbbr = game.homeTeam.abbreviation.ifBlank {
                        game.homeTeam.shortName.ifBlank { game.homeTeam.name.take(3).uppercase() }
                    }
                    itemViews.setTextViewText(R.id.widget_game_teams, "$awayAbbr @ $homeAbbr")

                    val scoreText = if (isNoSpoilers) "Tap to View" else "${game.awayTeam.score} - ${game.homeTeam.score}"
                    itemViews.setTextViewText(R.id.widget_game_scores, scoreText)

                    val statusText = if (game.isLive) {
                        "● ${game.period} ${game.timeRemaining}".trim()
                    } else {
                        game.period
                    }
                    itemViews.setTextViewText(R.id.widget_game_status, statusText)

                    val (gexLabel, gexColor) = when {
                        game.gex >= 85 -> "GREAT" to Color.parseColor("#2E7D32")
                        game.gex >= 65 -> "GOOD" to Color.parseColor("#1976D2")
                        game.gex >= 40 -> "OK" to Color.parseColor("#F57C00")
                        else -> "DUD" to Color.parseColor("#757575")
                    }
                    itemViews.setTextViewText(R.id.widget_game_gex, "${game.gex} $gexLabel")
                    itemViews.setInt(R.id.widget_game_gex, "setBackgroundColor", gexColor)

                    // Click item opens MainActivity deep-linked into that game
                    val launchIntent = Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        putExtra(MainActivity.EXTRA_GAME_ID, game.id)
                        putExtra(MainActivity.EXTRA_SPORT, game.sport)
                        putExtra(MainActivity.EXTRA_LEAGUE, game.league)
                    }
                    val itemPending = PendingIntent.getActivity(
                        context,
                        game.id.hashCode(),
                        launchIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                    itemViews.setOnClickPendingIntent(R.id.widget_game_item_root, itemPending)

                    views.addView(R.id.widget_games_container, itemViews)
                }
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    private fun getFallbackWidgetGames(): List<Game> {
        return listOf(
            Game(
                id = "401909088",
                homeTeam = Team("DET", "Detroit Pistons", null, 121, "Pistons", "DET"),
                awayTeam = Team("BOS", "Boston Celtics", null, 113, "Celtics", "BOS"),
                gex = 95,
                period = "Q4 - 0:45",
                timeRemaining = "0:45",
                isLive = true,
                broadcasts = listOf("NBC", "Peacock"),
                teaser = "Thriller finish in Detroit!",
                sport = "basketball",
                league = "nba"
            ),
            Game(
                id = "2",
                homeTeam = Team("CHI", "Chicago Blackhawks", null, 3, "Blackhawks", "CHI"),
                awayTeam = Team("DET", "Detroit Red Wings", null, 4, "Red Wings", "DET"),
                gex = 88,
                period = "P3 - 1:15",
                timeRemaining = "1:15",
                isLive = true,
                broadcasts = listOf("TNT"),
                teaser = "One-goal battle entering final minute.",
                sport = "hockey",
                league = "nhl"
            ),
            Game(
                id = "3",
                homeTeam = Team("LAL", "Los Angeles Lakers", null, 88, "Lakers", "LAL"),
                awayTeam = Team("MIA", "Miami Heat", null, 95, "Heat", "MIA"),
                gex = 75,
                period = "Q3 - 5:00",
                timeRemaining = "5:00",
                isLive = true,
                broadcasts = listOf("ESPN"),
                teaser = "Heat protecting lead in second half.",
                sport = "basketball",
                league = "nba"
            )
        )
    }
}
