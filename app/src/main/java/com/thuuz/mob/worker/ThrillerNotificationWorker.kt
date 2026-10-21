package com.thuuz.mob.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.thuuz.mob.MainActivity
import com.thuuz.mob.R
import com.thuuz.mob.data.FavoritesManager
import com.thuuz.mob.data.LiveSportsDataRepository
import com.thuuz.mob.data.SettingsManager
import com.thuuz.mob.ui.compose.Game
import kotlin.math.abs

class ThrillerNotificationWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        const val CHANNEL_ID = "thuuz_thriller_alerts"
        const val CHANNEL_NAME = "Thuuz Thriller & Crunch-Time Alerts"

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Excitement alerts for close, high-stakes games and crunch time"
                    enableLights(true)
                    enableVibration(true)
                    setShowBadge(true)
                }
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                notificationManager?.createNotificationChannel(channel)
            }
        }

        fun triggerTestNotification(context: Context) {
            createNotificationChannel(context)

            val settingsManager = SettingsManager.getInstance(context)
            val noSpoilers = settingsManager.isNoSpoilersEnabled()

            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra(MainActivity.EXTRA_GAME_ID, "401909088")
                putExtra(MainActivity.EXTRA_SPORT, "basketball")
                putExtra(MainActivity.EXTRA_LEAGUE, "nba")
            }

            val pendingIntent = PendingIntent.getActivity(
                context,
                9999,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val title = "🚨 Thriller Alert: Boston Celtics @ Detroit Pistons (GEX 95)"
            val text = if (noSpoilers) {
                "[Q4 - 0:45] High-drama finish in Detroit! Tune in now on NBC / Peacock (GEX 95)"
            } else {
                "Tied 112-112 with 0:45 left in Q4! High drama in Detroit - Tune in now on NBC / Peacock!"
            }

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.launcher_icon)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(NotificationCompat.BigTextStyle().bigText(text))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

            val manager = NotificationManagerCompat.from(context)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
            ) {
                manager.notify(9999, notification)
            }
        }
    }

    override suspend fun doWork(): Result {
        return try {
            createNotificationChannel(appContext)

            val favoritesManager = FavoritesManager.getInstance(appContext)
            val settingsManager = SettingsManager.getInstance(appContext)
            val repository = LiveSportsDataRepository(favoritesManager)

            val alertThreshold = settingsManager.getExcitementAlertThreshold()
            val myTeamsOnly = settingsManager.isMyTeamsOnlyAlerts()

            val liveGames = repository.fetchLiveGamesSync().filter { it.isLive }
            val prefs = appContext.getSharedPreferences("thuuz_thriller_alerts_cache", Context.MODE_PRIVATE)
            val now = System.currentTimeMillis()

            for (game in liveGames) {
                val isFavGame = favoritesManager.isFavorite(game.homeTeam.name, game.homeTeam.shortName, game.homeTeam.abbreviation) ||
                                favoritesManager.isFavorite(game.awayTeam.name, game.awayTeam.shortName, game.awayTeam.abbreviation)

                // If user enabled "Only Alert for My Teams", skip non-favorite games
                if (myTeamsOnly && !isFavGame) {
                    continue
                }

                val thresholdForGame = if (isFavGame) (alertThreshold - 5).coerceAtLeast(60) else alertThreshold
                val crossesGex = game.gex >= thresholdForGame
                val isCrunch = isCrunchTime(game)

                if (crossesGex || isCrunch) {
                    val alertKey = "notified_${game.id}_${game.period.replace(" ", "_")}"
                    val lastNotified = prefs.getLong(alertKey, 0L)
                    // Deduplicate: avoid alerting within 15 minutes for the same game period
                    if (now - lastNotified < 15 * 60 * 1000L) {
                        continue
                    }

                    postGameNotification(game, isCrunch, settingsManager.isNoSpoilersEnabled())
                    prefs.edit().putLong(alertKey, now).apply()
                }
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    private fun isCrunchTime(game: Game): Boolean {
        if (!game.isLive) return false
        val diff = abs(game.homeTeam.score - game.awayTeam.score)
        val period = game.period.lowercase()
        val minutesLeft = parseMinutesLeft(game.timeRemaining)

        return when (game.sport) {
            "basketball" -> {
                // Q4 or OT, <= 3 mins remaining, score difference <= 5
                (period.contains("4th") || period.contains("q4") || period.contains("ot")) &&
                    (minutesLeft <= 3.0) && diff <= 5
            }
            "hockey" -> {
                // 3rd period or OT, <= 3 mins, score difference <= 1
                (period.contains("3rd") || period.contains("p3") || period.contains("ot")) &&
                    (minutesLeft <= 3.0) && diff <= 1
            }
            "football" -> {
                // Q4 or OT, <= 4 mins, one possession (score difference <= 8)
                (period.contains("4th") || period.contains("q4") || period.contains("ot")) &&
                    (minutesLeft <= 4.0) && diff <= 8
            }
            "baseball" -> {
                // 9th inning or extra innings, score difference <= 2
                (period.contains("9th") || period.contains("bottom 9") || period.contains("top 9") || period.contains("extra")) &&
                    diff <= 2
            }
            "soccer" -> {
                // 2nd half closing stages or extra time, score difference <= 1
                (period.contains("2nd") || period.contains("90") || period.contains("+")) &&
                    diff <= 1
            }
            else -> false
        }
    }

    private fun parseMinutesLeft(clockStr: String): Double {
        if (clockStr.isBlank()) return 2.0
        return try {
            val parts = clockStr.split(":")
            if (parts.size == 2) {
                val mins = parts[0].toDoubleOrNull() ?: 0.0
                val secs = parts[1].toDoubleOrNull() ?: 0.0
                mins + (secs / 60.0)
            } else {
                clockStr.toDoubleOrNull() ?: 2.0
            }
        } catch (e: Exception) {
            2.0
        }
    }

    private fun postGameNotification(game: Game, isCrunchTime: Boolean, noSpoilers: Boolean = false) {
        val intent = Intent(appContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(MainActivity.EXTRA_GAME_ID, game.id)
            putExtra(MainActivity.EXTRA_SPORT, game.sport)
            putExtra(MainActivity.EXTRA_LEAGUE, game.league)
        }

        val pendingIntent = PendingIntent.getActivity(
            appContext,
            game.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val headerPrefix = if (isCrunchTime) "🚨 CRUNCH TIME" else "🔥 Thriller Alert"
        val title = "$headerPrefix: ${game.awayTeam.name} @ ${game.homeTeam.name} (GEX ${game.gex})"
        val timeDisplay = if (game.timeRemaining.isNotBlank()) "${game.period} - ${game.timeRemaining}" else game.period
        val channelGuide = if (game.broadcasts.isNotEmpty()) "Tune in on ${game.broadcasts.first()}" else "Watch live now"
        val text = if (noSpoilers) {
            "[$timeDisplay] High-drama finish in ${game.homeTeam.shortName.ifBlank { game.homeTeam.name }}! $channelGuide (GEX ${game.gex})"
        } else {
            "[$timeDisplay] ${game.awayTeam.name} ${game.awayTeam.score}, ${game.homeTeam.name} ${game.homeTeam.score}. $channelGuide!"
        }

        val notification = NotificationCompat.Builder(appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.launcher_icon)
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        val manager = NotificationManagerCompat.from(appContext)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ActivityCompat.checkSelfPermission(appContext, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        ) {
            manager.notify(game.id.hashCode(), notification)
        }
    }
}
