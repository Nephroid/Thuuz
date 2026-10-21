package com.thuuz.mob

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.work.*
import com.thuuz.mob.data.FavoritesManager
import com.thuuz.mob.data.LiveSportsDataRepository
import com.thuuz.mob.data.SettingsManager
import com.thuuz.mob.ui.compose.*
import com.thuuz.mob.ui.theme.ThemeMode
import com.thuuz.mob.ui.theme.ThuuzTheme
import com.thuuz.mob.worker.ThrillerNotificationWorker
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private lateinit var favoritesManager: FavoritesManager
    private lateinit var settingsManager: SettingsManager
    private lateinit var repository: LiveSportsDataRepository

    private val pendingGameIdState = mutableStateOf<String?>(null)
    private val pendingSportState = mutableStateOf<String?>(null)
    private val pendingLeagueState = mutableStateOf<String?>(null)

    companion object {
        const val EXTRA_GAME_ID = "com.thuuz.mob.extra.GAME_ID"
        const val EXTRA_SPORT = "com.thuuz.mob.extra.SPORT"
        const val EXTRA_LEAGUE = "com.thuuz.mob.extra.LEAGUE"
    }

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _ ->
        // Permission result handled
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        favoritesManager = FavoritesManager.getInstance(this)
        settingsManager = SettingsManager.getInstance(this)
        repository = LiveSportsDataRepository(favoritesManager)

        ThrillerNotificationWorker.createNotificationChannel(this)
        checkAndRequestNotificationPermission()
        scheduleBackgroundThrillerWorker()

        processIntent(intent)

        setContent {
            val currentThemeModeStr = remember { mutableStateOf(settingsManager.getThemeMode()) }
            val themeMode = when (currentThemeModeStr.value.uppercase()) {
                "DARK" -> ThemeMode.DARK
                "LIGHT" -> ThemeMode.LIGHT
                else -> ThemeMode.SYSTEM
            }

            ThuuzTheme(themeMode = themeMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val todayParam = remember {
                        SimpleDateFormat("yyyyMMdd", Locale.US).format(Date())
                    }

                    var gamesState by remember { mutableStateOf<List<Game>>(emptyList()) }
                    var isLoadingGames by remember { mutableStateOf(false) }
                    var selectedSport by remember { mutableStateOf(SportLeague.ALL) }
                    var selectedDateParam by remember { mutableStateOf(todayParam) }

                    // Favorites & Settings state
                    var favoriteTeams by remember { mutableStateOf(favoritesManager.getFavoriteTeams()) }
                    var showMyTeamsOnly by remember { mutableStateOf(false) }
                    var isNoSpoilers by remember { mutableStateOf(settingsManager.isNoSpoilersEnabled()) }
                    var alertThreshold by remember { mutableStateOf(settingsManager.getExcitementAlertThreshold()) }
                    var myTeamsAlerts by remember { mutableStateOf(settingsManager.isMyTeamsOnlyAlerts()) }
                    var revealedGameIds by remember { mutableStateOf(settingsManager.getRevealedGameIds()) }

                    // Navigation state
                    var activeGameId by remember { mutableStateOf<String?>(null) }
                    var selectedGameForDetail by remember { mutableStateOf<Game?>(null) }
                    var gameDetailData by remember { mutableStateOf<GameDetailData?>(null) }
                    var isLoadingDetail by remember { mutableStateOf(false) }

                    // Handle deep link / notification opening
                    LaunchedEffect(pendingGameIdState.value) {
                        val gId = pendingGameIdState.value
                        if (gId != null) {
                            activeGameId = gId
                            selectedGameForDetail = gamesState.find { it.id == gId } ?: Game(
                                id = gId,
                                homeTeam = Team("home", "Detroit Pistons", null, 121, "Pistons", "DET"),
                                awayTeam = Team("away", "Boston Celtics", null, 113, "Celtics", "BOS"),
                                gex = 95,
                                period = "Q4 - 0:45",
                                timeRemaining = "0:45",
                                isLive = true,
                                broadcasts = listOf("NBC", "Peacock"),
                                teaser = "Thriller alert! Live action in crunch time.",
                                sport = pendingSportState.value ?: "basketball",
                                league = pendingLeagueState.value ?: "nba"
                            )
                            pendingGameIdState.value = null
                        }
                    }

                    // Concurrent load games function
                    fun loadGames(sport: SportLeague, dateParam: String) {
                        isLoadingGames = true
                        val leagues = if (sport == SportLeague.ALL) {
                            listOf(
                                SportLeague.NBA,
                                SportLeague.NHL,
                                SportLeague.NFL,
                                SportLeague.MLB,
                                SportLeague.NCAAF,
                                SportLeague.NCAAM,
                                SportLeague.SOCCER
                            )
                        } else {
                            listOf(sport)
                        }

                        repository.fetchGames(leagues, dateParam, object : LiveSportsDataRepository.FetchCallback {
                            override fun onSuccess(games: List<Game>) {
                                runOnUiThread {
                                    val resultGames = if (games.isNotEmpty()) games else getFallbackGames()
                                    gamesState = resultGames
                                    isLoadingGames = false
                                }
                            }

                            override fun onError(error: Exception) {
                                runOnUiThread {
                                    gamesState = getFallbackGames()
                                    isLoadingGames = false
                                }
                            }
                        })
                    }

                    // Initial load
                    LaunchedEffect(selectedSport, selectedDateParam) {
                        loadGames(selectedSport, selectedDateParam)
                    }

                    // 30-Second Auto-Refresh Ticker while app is open
                    LaunchedEffect(selectedSport, selectedDateParam, activeGameId) {
                        while (true) {
                            kotlinx.coroutines.delay(30_000L)
                            val leagues = if (selectedSport == SportLeague.ALL) {
                                listOf(
                                    SportLeague.NBA,
                                    SportLeague.NHL,
                                    SportLeague.NFL,
                                    SportLeague.MLB,
                                    SportLeague.NCAAF,
                                    SportLeague.NCAAM,
                                    SportLeague.SOCCER
                                )
                            } else {
                                listOf(selectedSport)
                            }

                            repository.fetchGames(leagues, selectedDateParam, object : LiveSportsDataRepository.FetchCallback {
                                override fun onSuccess(games: List<Game>) {
                                    runOnUiThread {
                                        if (games.isNotEmpty()) {
                                            gamesState = games
                                        }
                                    }
                                }
                                override fun onError(error: Exception) {}
                            })

                            // Auto-refresh active game detail if game is live
                            val curId = activeGameId
                            val curGame = selectedGameForDetail
                            if (curId != null && (curGame == null || curGame.isLive)) {
                                val currentSport = curGame?.sport ?: "basketball"
                                val currentLeague = curGame?.league ?: "nba"
                                repository.fetchGameDetail(
                                    sport = currentSport,
                                    league = currentLeague,
                                    gameId = curId,
                                    callback = object : LiveSportsDataRepository.GameDetailCallback {
                                        override fun onSuccess(detail: GameDetailData) {
                                            runOnUiThread {
                                                gameDetailData = detail
                                            }
                                        }
                                        override fun onError(error: Exception) {}
                                    }
                                )
                            }
                        }
                    }

                    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                        val isTwoPane = maxWidth >= 700.dp

                        // In two-pane mode, auto-select first or top-rated game if none selected yet
                        LaunchedEffect(isTwoPane, gamesState) {
                            if (isTwoPane && activeGameId == null && gamesState.isNotEmpty()) {
                                val topGame = gamesState.maxByOrNull { it.gex } ?: gamesState.first()
                                selectedGameForDetail = topGame
                                activeGameId = topGame.id
                            }
                        }

                        // Fetch game detail whenever activeGameId changes
                        LaunchedEffect(activeGameId) {
                            val gId = activeGameId
                            if (gId != null) {
                                val currentSport = selectedGameForDetail?.sport ?: "basketball"
                                val currentLeague = selectedGameForDetail?.league ?: "nba"
                                isLoadingDetail = true
                                repository.fetchGameDetail(
                                    sport = currentSport,
                                    league = currentLeague,
                                    gameId = gId,
                                    callback = object : LiveSportsDataRepository.GameDetailCallback {
                                        override fun onSuccess(detail: GameDetailData) {
                                            runOnUiThread {
                                                gameDetailData = detail
                                                isLoadingDetail = false
                                            }
                                        }

                                        override fun onError(error: Exception) {
                                            runOnUiThread {
                                                val g = selectedGameForDetail
                                                if (g != null) {
                                                    gameDetailData = GameDetailData(
                                                        gameId = g.id,
                                                        homeTeam = g.homeTeam,
                                                        awayTeam = g.awayTeam,
                                                        status = g.period,
                                                        isLive = g.isLive,
                                                        gex = g.gex,
                                                        teaser = g.teaser,
                                                        broadcasts = g.broadcasts,
                                                        winProbabilities = listOf(
                                                            WinProbabilityPoint("0", 0.5f),
                                                            WinProbabilityPoint("1", 0.7f),
                                                            WinProbabilityPoint("2", 0.4f),
                                                            WinProbabilityPoint("3", 0.9f)
                                                        ),
                                                        plays = listOf(
                                                            PlayItem("4th Quarter 0:12 - Game tying shot", "Q4 0:12", g.homeTeam.score, g.awayTeam.score, "Score", true)
                                                        ),
                                                        leaders = emptyList(),
                                                        teamBoxStats = emptyList(),
                                                        sport = g.sport,
                                                        league = g.league
                                                    )
                                                }
                                                isLoadingDetail = false
                                            }
                                        }
                                    }
                                )
                            } else {
                                gameDetailData = null
                            }
                        }

                        // Handle back press if game detail is open in single pane mode
                        BackHandler(enabled = !isTwoPane && activeGameId != null) {
                            activeGameId = null
                            selectedGameForDetail = null
                            gameDetailData = null
                        }

                        if (isTwoPane) {
                            // Two-Pane Foldable Layout (Pixel 10 Pro Fold unfolded inner display / tablet)
                            Row(modifier = Modifier.fillMaxSize()) {
                                // Left pane: Scoreboard (weight ~0.45)
                                Box(
                                    modifier = Modifier
                                        .weight(0.45f)
                                        .fillMaxHeight()
                                ) {
                                    ScoreboardScreen(
                                        games = gamesState,
                                        selectedSport = selectedSport,
                                        onSportSelected = { newSport ->
                                            selectedSport = newSport
                                        },
                                        selectedDateParam = selectedDateParam,
                                        onDateSelected = { newDate ->
                                            selectedDateParam = newDate
                                        },
                                        onGameClick = { clickedGame ->
                                            selectedGameForDetail = clickedGame
                                            activeGameId = clickedGame.id
                                        },
                                        onRefresh = {
                                            loadGames(selectedSport, selectedDateParam)
                                        },
                                        isLoading = isLoadingGames,
                                        favoriteTeams = favoriteTeams,
                                        onToggleFavorite = { teamName ->
                                            favoritesManager.toggleFavoriteTeam(teamName)
                                            favoriteTeams = favoritesManager.getFavoriteTeams()
                                            gamesState = repository.recalculateExcitement(gamesState)
                                        },
                                        showMyTeamsOnly = showMyTeamsOnly,
                                        onToggleMyTeamsOnly = {
                                            showMyTeamsOnly = !showMyTeamsOnly
                                        },
                                        onTestThrillerAlert = {
                                            ThrillerNotificationWorker.triggerTestNotification(this@MainActivity)
                                        },
                                        selectedGameId = activeGameId,
                                        isNoSpoilersEnabled = isNoSpoilers,
                                        onToggleNoSpoilers = { isNoSpoilers = it },
                                        alertThreshold = alertThreshold,
                                        onChangeAlertThreshold = { alertThreshold = it },
                                        isMyTeamsOnlyAlerts = myTeamsAlerts,
                                        onToggleMyTeamsOnlyAlerts = { myTeamsAlerts = it },
                                        revealedGameIds = revealedGameIds,
                                        onRevealScore = {
                                            revealedGameIds = settingsManager.getRevealedGameIds()
                                        }
                                    )
                                }

                                VerticalDivider(
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                    thickness = 1.dp
                                )

                                // Right pane: Game Details & Live Momentum (weight ~0.55)
                                Box(
                                    modifier = Modifier
                                        .weight(0.55f)
                                        .fillMaxHeight()
                                ) {
                                    if (activeGameId != null || selectedGameForDetail != null) {
                                        val isScoreRevealed = activeGameId != null && revealedGameIds.contains(activeGameId)
                                        GameDetailScreen(
                                            detail = gameDetailData,
                                            isLoading = isLoadingDetail,
                                            onBack = {},
                                            isTwoPane = true,
                                            isNoSpoilers = isNoSpoilers,
                                            isScoreRevealed = isScoreRevealed,
                                            onRevealScore = {
                                                activeGameId?.let { gId ->
                                                    settingsManager.revealScore(gId)
                                                    revealedGameIds = settingsManager.getRevealedGameIds()
                                                }
                                            }
                                        )
                                    } else {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Select a game to view details and live momentum",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            // Single Pane (Cover Display / Phone Mode)
                            if (activeGameId != null) {
                                val isScoreRevealed = activeGameId != null && revealedGameIds.contains(activeGameId)
                                GameDetailScreen(
                                    detail = gameDetailData,
                                    isLoading = isLoadingDetail,
                                    onBack = {
                                        activeGameId = null
                                        selectedGameForDetail = null
                                        gameDetailData = null
                                    },
                                    isTwoPane = false,
                                    isNoSpoilers = isNoSpoilers,
                                    isScoreRevealed = isScoreRevealed,
                                    onRevealScore = {
                                        activeGameId?.let { gId ->
                                            settingsManager.revealScore(gId)
                                            revealedGameIds = settingsManager.getRevealedGameIds()
                                        }
                                    }
                                )
                            } else {
                                ScoreboardScreen(
                                    games = gamesState,
                                    selectedSport = selectedSport,
                                    onSportSelected = { newSport ->
                                        selectedSport = newSport
                                    },
                                    selectedDateParam = selectedDateParam,
                                    onDateSelected = { newDate ->
                                        selectedDateParam = newDate
                                    },
                                    onGameClick = { clickedGame ->
                                        selectedGameForDetail = clickedGame
                                        activeGameId = clickedGame.id
                                    },
                                    onRefresh = {
                                        loadGames(selectedSport, selectedDateParam)
                                    },
                                    isLoading = isLoadingGames,
                                    favoriteTeams = favoriteTeams,
                                    onToggleFavorite = { teamName ->
                                        favoritesManager.toggleFavoriteTeam(teamName)
                                        favoriteTeams = favoritesManager.getFavoriteTeams()
                                        gamesState = repository.recalculateExcitement(gamesState)
                                    },
                                    showMyTeamsOnly = showMyTeamsOnly,
                                    onToggleMyTeamsOnly = {
                                        showMyTeamsOnly = !showMyTeamsOnly
                                    },
                                    onTestThrillerAlert = {
                                        ThrillerNotificationWorker.triggerTestNotification(this@MainActivity)
                                    },
                                    isNoSpoilersEnabled = isNoSpoilers,
                                    onToggleNoSpoilers = { isNoSpoilers = it },
                                    alertThreshold = alertThreshold,
                                    onChangeAlertThreshold = { alertThreshold = it },
                                    isMyTeamsOnlyAlerts = myTeamsAlerts,
                                    onToggleMyTeamsOnlyAlerts = { myTeamsAlerts = it },
                                    revealedGameIds = revealedGameIds,
                                    onRevealScore = {
                                        revealedGameIds = settingsManager.getRevealedGameIds()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        processIntent(intent)
    }

    private fun processIntent(intent: Intent?) {
        if (intent != null && intent.hasExtra(EXTRA_GAME_ID)) {
            val gId = intent.getStringExtra(EXTRA_GAME_ID)
            val sport = intent.getStringExtra(EXTRA_SPORT) ?: "basketball"
            val league = intent.getStringExtra(EXTRA_LEAGUE) ?: "nba"
            pendingGameIdState.value = gId
            pendingSportState.value = sport
            pendingLeagueState.value = league
        }
    }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun scheduleBackgroundThrillerWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<ThrillerNotificationWorker>(
            15, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "thuuz_thriller_alerts_work",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

    private fun getFallbackGames(): List<Game> {
        val calc = repository
        return listOf(
            Game(
                id = "401909088",
                homeTeam = Team("DET", "Detroit Pistons", "https://a.espncdn.com/i/teamlogos/nba/500/det.png", 121, "Pistons", "DET"),
                awayTeam = Team("BOS", "Boston Celtics", "https://a.espncdn.com/i/teamlogos/nba/500/bos.png", 113, "Celtics", "BOS"),
                gex = calc.calculateScore(121, 113, isLive = false, isFinal = true, sport = "basketball", homeName = "Detroit Pistons", awayName = "Boston Celtics", homeShortName = "Pistons", awayShortName = "Celtics", homeAbbr = "DET", awayAbbr = "BOS"),
                period = "Final",
                timeRemaining = "",
                isLive = false,
                broadcasts = listOf("NBC", "Peacock"),
                teaser = "NBA season tips off with Pistons rallying to beat Celtics 121-113, spoiling Paul George's debut",
                sport = "basketball",
                league = "nba"
            ),
            Game(
                id = "2",
                homeTeam = Team("CHI", "Chicago Blackhawks", null, 3, "Blackhawks", "CHI"),
                awayTeam = Team("DET", "Detroit Red Wings", null, 4, "Red Wings", "DET"),
                gex = calc.calculateScore(3, 4, isLive = true, isFinal = false, sport = "hockey", periodNumber = 3, clockStr = "1:15", homeName = "Chicago Blackhawks", awayName = "Detroit Red Wings", homeShortName = "Blackhawks", awayShortName = "Red Wings", homeAbbr = "CHI", awayAbbr = "DET"),
                period = "P3",
                timeRemaining = "1:15",
                isLive = true,
                broadcasts = listOf("TNT"),
                teaser = "One-goal battle entering the closing minute.",
                sport = "hockey",
                league = "nhl"
            ),
            Game(
                id = "3",
                homeTeam = Team("LAL", "Los Angeles Lakers", null, 88, "Lakers", "LAL"),
                awayTeam = Team("MIA", "Miami Heat", null, 95, "Heat", "MIA"),
                gex = calc.calculateScore(88, 95, isLive = true, isFinal = false, sport = "basketball", periodNumber = 3, clockStr = "5:00", homeName = "Los Angeles Lakers", awayName = "Miami Heat", homeShortName = "Lakers", awayShortName = "Heat", homeAbbr = "LAL", awayAbbr = "MIA"),
                period = "Q3",
                timeRemaining = "5:00",
                isLive = true,
                broadcasts = listOf("ESPN"),
                teaser = "Heat protecting lead as Lakers make a run.",
                sport = "basketball",
                league = "nba"
            ),
            Game(
                id = "4",
                homeTeam = Team("NYK", "New York Knicks", null, 90, "Knicks", "NYK"),
                awayTeam = Team("CHI", "Chicago Bulls", null, 110, "Bulls", "CHI"),
                gex = calc.calculateScore(90, 110, isLive = false, isFinal = true, sport = "basketball", homeName = "New York Knicks", awayName = "Chicago Bulls", homeShortName = "Knicks", awayShortName = "Bulls", homeAbbr = "NYK", awayAbbr = "CHI"),
                period = "Final",
                timeRemaining = "",
                isLive = false,
                broadcasts = listOf("MSG"),
                teaser = "Bulls pull away in the second half.",
                sport = "basketball",
                league = "nba"
            ),
            Game(
                id = "5",
                homeTeam = Team("CLE", "Cleveland Guardians", null, 7, "Guardians", "CLE"),
                awayTeam = Team("DET", "Detroit Tigers", null, 2, "Tigers", "DET"),
                gex = calc.calculateScore(7, 2, isLive = false, isFinal = true, sport = "baseball", homeName = "Cleveland Guardians", awayName = "Detroit Tigers", homeShortName = "Guardians", awayShortName = "Tigers", homeAbbr = "CLE", awayAbbr = "DET"),
                period = "Final",
                timeRemaining = "",
                isLive = false,
                broadcasts = listOf("Bally Sports"),
                teaser = "Guardians secure decisive victory.",
                sport = "baseball",
                league = "mlb"
            )
        )
    }
}
