package com.thuuz.mob.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thuuz.mob.data.FavoritesManager
import com.thuuz.mob.data.SettingsManager
import java.text.SimpleDateFormat
import java.util.*

enum class SportLeague(val displayName: String, val sportPath: String, val leaguePath: String) {
    ALL("All", "", ""),
    NBA("NBA", "basketball", "nba"),
    NHL("NHL", "hockey", "nhl"),
    NFL("NFL", "football", "nfl"),
    MLB("MLB", "baseball", "mlb"),
    NCAAF("NCAAF", "football", "college-football"),
    NCAAM("NCAAM", "basketball", "mens-college-basketball"),
    SOCCER("Soccer", "soccer", "eng.1")
}

enum class SortMode(val label: String) {
    EXCITEMENT("Excitement (GEX)"),
    LIVE_TIME("Live / Time First")
}

data class DateFilterItem(
    val label: String,
    val dateParam: String // YYYYMMDD
)

data class Team(
    val id: String,
    val name: String,
    val logoUrl: String? = null,
    val score: Int,
    val shortName: String = "",
    val abbreviation: String = ""
)

data class Game(
    val id: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val gex: Int, // 0-100
    val period: String,
    val timeRemaining: String,
    val isLive: Boolean,
    val broadcasts: List<String> = emptyList(),
    val teaser: String = "",
    val sport: String = "basketball",
    val league: String = "nba",
    val spread: Double? = null
)

// Detail screen support data structures
data class WinProbabilityPoint(
    val playId: String,
    val homeWinPercentage: Float, // 0.0 - 1.0
    val tiePercentage: Float = 0f
)

data class PlayItem(
    val text: String,
    val timeDetail: String,
    val homeScore: Int,
    val awayScore: Int,
    val type: String,
    val isScoring: Boolean
)

data class LeaderPlayer(
    val name: String,
    val jersey: String,
    val position: String,
    val statValue: String,
    val headshotUrl: String? = null
)

data class LeaderCategory(
    val category: String,
    val leader: LeaderPlayer
)

data class TeamBoxStat(
    val label: String,
    val awayValue: String,
    val homeValue: String
)

data class PlayerBoxStat(
    val id: String,
    val name: String,
    val jersey: String = "",
    val position: String = "",
    val isStarter: Boolean = false,
    val headshotUrl: String? = null,
    val stats: Map<String, String> = emptyMap(),
    val displayStatsSummary: String = ""
)

data class TeamPlayerBoxScore(
    val teamName: String,
    val teamAbbr: String,
    val teamLogoUrl: String? = null,
    val isHome: Boolean,
    val statHeaders: List<String> = emptyList(),
    val players: List<PlayerBoxStat> = emptyList()
)

data class VideoHighlight(
    val id: String,
    val headline: String,
    val description: String,
    val durationSeconds: Int,
    val durationDisplay: String,
    val thumbnailUrl: String?,
    val videoUrl: String
)

data class GameDetailData(
    val gameId: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val status: String,
    val isLive: Boolean,
    val gex: Int,
    val teaser: String,
    val broadcasts: List<String>,
    val winProbabilities: List<WinProbabilityPoint>,
    val plays: List<PlayItem>,
    val leaders: List<LeaderCategory>,
    val teamBoxStats: List<TeamBoxStat>,
    val playerBoxScores: List<TeamPlayerBoxScore> = emptyList(),
    val videos: List<VideoHighlight> = emptyList(),
    val sport: String? = "basketball",
    val league: String? = "nba"
)

enum class ExcitementRating(val label: String, val color: Color) {
    GREAT("GREAT", Color(0xFF2E7D32)), // Darker Thuuz Green
    GOOD("GOOD", Color(0xFF1976D2)),   // Thuuz Blue
    OK("OK", Color(0xFFF57C00)),       // Amber/Orange
    DUD("DUD", Color(0xFF757575))      // Gray
}

fun getExcitementRating(gex: Int): ExcitementRating {
    return when {
        gex >= 85 -> ExcitementRating.GREAT
        gex in 65..84 -> ExcitementRating.GOOD
        gex in 40..64 -> ExcitementRating.OK
        else -> ExcitementRating.DUD
    }
}

@Composable
fun SettingsDialog(
    isNoSpoilers: Boolean,
    onToggleNoSpoilers: (Boolean) -> Unit,
    alertThreshold: Int,
    onChangeAlertThreshold: (Int) -> Unit,
    isMyTeamsOnly: Boolean,
    onToggleMyTeamsOnly: (Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Thuuz Settings", fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // 1. No Spoilers Mode
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Text("No Spoilers Mode", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        Text(
                            "Hide live and final scores until tapped, so thriller ratings aren't spoiled.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = isNoSpoilers,
                        onCheckedChange = onToggleNoSpoilers
                    )
                }

                HorizontalDivider()

                // 2. Excitement Alert Threshold Slider
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Alert Threshold", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        Text(
                            "$alertThreshold+ GEX",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        "Notify when games cross excitement threshold (70-95 GEX)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Slider(
                        value = alertThreshold.toFloat(),
                        onValueChange = { onChangeAlertThreshold(it.toInt()) },
                        valueRange = 70f..95f,
                        steps = 24
                    )
                }

                HorizontalDivider()

                // 3. Only Alert for My Teams
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Text("Only Alert for My Teams", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        Text(
                            "Only send notifications for games involving starred favorite teams.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = isMyTeamsOnly,
                        onCheckedChange = onToggleMyTeamsOnly
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Done", fontWeight = FontWeight.Bold)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreboardScreen(
    games: List<Game>,
    selectedSport: SportLeague,
    onSportSelected: (SportLeague) -> Unit,
    selectedDateParam: String,
    onDateSelected: (String) -> Unit,
    onGameClick: (Game) -> Unit,
    onRefresh: () -> Unit,
    isLoading: Boolean = false,
    favoriteTeams: Set<String> = emptySet(),
    onToggleFavorite: (String) -> Unit = {},
    showMyTeamsOnly: Boolean = false,
    onToggleMyTeamsOnly: () -> Unit = {},
    onTestThrillerAlert: () -> Unit = {},
    selectedGameId: String? = null,
    isNoSpoilersEnabled: Boolean? = null,
    onToggleNoSpoilers: ((Boolean) -> Unit)? = null,
    alertThreshold: Int? = null,
    onChangeAlertThreshold: ((Int) -> Unit)? = null,
    isMyTeamsOnlyAlerts: Boolean? = null,
    onToggleMyTeamsOnlyAlerts: ((Boolean) -> Unit)? = null,
    revealedGameIds: Set<String>? = null,
    onRevealScore: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val favMgr = remember(context) { FavoritesManager.getInstance(context) }
    val settingsMgr = remember(context) { SettingsManager.getInstance(context) }

    var showSettingsDialog by remember { mutableStateOf(false) }

    var noSpoilersState by remember(isNoSpoilersEnabled) {
        mutableStateOf(isNoSpoilersEnabled ?: settingsMgr.isNoSpoilersEnabled())
    }
    var alertThresholdState by remember(alertThreshold) {
        mutableStateOf(alertThreshold ?: settingsMgr.getExcitementAlertThreshold())
    }
    var myTeamsAlertsState by remember(isMyTeamsOnlyAlerts) {
        mutableStateOf(isMyTeamsOnlyAlerts ?: settingsMgr.isMyTeamsOnlyAlerts())
    }
    var revealedGamesState by remember(revealedGameIds) {
        mutableStateOf(revealedGameIds ?: settingsMgr.getRevealedGameIds())
    }

    var sortMode by remember { mutableStateOf(SortMode.EXCITEMENT) }

    // Generate dates: 2 days ago, yesterday, today, tomorrow, +2 days
    val dates = remember { generateDateStrip() }

    // Combined filtering & sorting: Sport League + My Teams + SortMode
    val filteredGames = remember(games, selectedSport, showMyTeamsOnly, favoriteTeams, sortMode) {
        var result = games

        // Sport filter
        if (selectedSport != SportLeague.ALL) {
            result = result.filter {
                it.sport == selectedSport.sportPath && it.league == selectedSport.leaguePath
            }
        }

        // Combined My Teams filter
        if (showMyTeamsOnly) {
            result = result.filter { game ->
                favoriteTeams.any { fav ->
                    favMgr.matchesFavorite(fav, game.homeTeam.name, game.homeTeam.shortName, game.homeTeam.abbreviation) ||
                    favMgr.matchesFavorite(fav, game.awayTeam.name, game.awayTeam.shortName, game.awayTeam.abbreviation)
                }
            }
        }

        // Sort mode
        when (sortMode) {
            SortMode.EXCITEMENT -> result.sortedByDescending { it.gex }
            SortMode.LIVE_TIME -> result.sortedWith(
                compareByDescending<Game> { it.isLive }
                    .thenByDescending { !it.isLive && !it.period.contains("Final", ignoreCase = true) }
                    .thenByDescending { it.gex }
            )
        }
    }

    if (showSettingsDialog) {
        SettingsDialog(
            isNoSpoilers = noSpoilersState,
            onToggleNoSpoilers = { enabled ->
                noSpoilersState = enabled
                settingsMgr.setNoSpoilersEnabled(enabled)
                onToggleNoSpoilers?.invoke(enabled)
            },
            alertThreshold = alertThresholdState,
            onChangeAlertThreshold = { threshold ->
                alertThresholdState = threshold
                settingsMgr.setExcitementAlertThreshold(threshold)
                onChangeAlertThreshold?.invoke(threshold)
            },
            isMyTeamsOnly = myTeamsAlertsState,
            onToggleMyTeamsOnly = { enabled ->
                myTeamsAlertsState = enabled
                settingsMgr.setMyTeamsOnlyAlerts(enabled)
                onToggleMyTeamsOnlyAlerts?.invoke(enabled)
            },
            onDismiss = { showSettingsDialog = false }
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                // Main Header Title with Brand & Actions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF2E7D32),
                            modifier = Modifier.size(28.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "T",
                                    color = Color.White,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Thuuz Sports",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Quick Test Alert Button
                        FilledTonalButton(
                            onClick = onTestThrillerAlert,
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                            modifier = Modifier.height(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Test Alert",
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Test Alert", style = MaterialTheme.typography.labelSmall)
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        if (isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                        } else {
                            TextButton(onClick = onRefresh) {
                                Text("Refresh", style = MaterialTheme.typography.labelMedium)
                            }
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        // Settings Gear Icon
                        IconButton(
                            onClick = { showSettingsDialog = true },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Sport Filter Horizontal Chips + My Teams Filter Chip (Combined Filters)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // "My Teams" toggle chip (co-exists with sport chips)
                    FilterChip(
                        selected = showMyTeamsOnly,
                        onClick = onToggleMyTeamsOnly,
                        leadingIcon = {
                            Icon(
                                imageVector = if (showMyTeamsOnly) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "My Teams",
                                modifier = Modifier.size(16.dp),
                                tint = if (showMyTeamsOnly) Color(0xFFFFB300) else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        label = { Text("My Teams") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )

                    SportLeague.values().forEach { sport ->
                        val isSelected = (sport == selectedSport)
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                onSportSelected(sport)
                            },
                            label = { Text(sport.displayName) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }

                // Horizontal Date Strip
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    dates.forEach { item ->
                        val isSelected = item.dateParam == selectedDateParam
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable { onDateSelected(item.dateParam) }
                        ) {
                            Text(
                                text = item.label,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                // Subheader: Game Count & Sort Mode Toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (showMyTeamsOnly) "⭐ My Teams: ${filteredGames.size} Games" else "${filteredGames.size} Games",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sort:",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        FilterChip(
                            selected = sortMode == SortMode.EXCITEMENT,
                            onClick = { sortMode = SortMode.EXCITEMENT },
                            label = { Text("GEX", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            modifier = Modifier.height(28.dp)
                        )
                        FilterChip(
                            selected = sortMode == SortMode.LIVE_TIME,
                            onClick = { sortMode = SortMode.LIVE_TIME },
                            label = { Text("Live First", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            modifier = Modifier.height(28.dp)
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            }
        }
    ) { innerPadding ->
        if (filteredGames.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = if (isLoading) "Loading games..."
                        else if (showMyTeamsOnly) "No games found for your favorite teams for this sport/date.\nTap the star icon on any team to add them to My Teams!"
                        else "No games scheduled for this date & sport",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredGames, key = { it.id }) { game ->
                    val homeIsFav = favoriteTeams.any { fav ->
                        favMgr.matchesFavorite(fav, game.homeTeam.name, game.homeTeam.shortName, game.homeTeam.abbreviation)
                    }
                    val awayIsFav = favoriteTeams.any { fav ->
                        favMgr.matchesFavorite(fav, game.awayTeam.name, game.awayTeam.shortName, game.awayTeam.abbreviation)
                    }
                    val isRevealed = revealedGamesState.contains(game.id)

                    GameCard(
                        game = game,
                        onClick = { onGameClick(game) },
                        homeIsFavorite = homeIsFav,
                        awayIsFavorite = awayIsFav,
                        onToggleHomeFavorite = { onToggleFavorite(game.homeTeam.name) },
                        onToggleAwayFavorite = { onToggleFavorite(game.awayTeam.name) },
                        isSelected = (selectedGameId != null && game.id == selectedGameId),
                        isNoSpoilers = noSpoilersState,
                        isScoreRevealed = isRevealed,
                        onRevealScore = {
                            settingsMgr.revealScore(game.id)
                            revealedGamesState = settingsMgr.getRevealedGameIds()
                            onRevealScore?.invoke(game.id)
                        }
                    )
                }
            }
        }
    }
}

private fun generateDateStrip(): List<DateFilterItem> {
    val sdfParam = SimpleDateFormat("yyyyMMdd", Locale.US)
    val sdfDisplay = SimpleDateFormat("EEE, MMM d", Locale.US)

    val list = mutableListOf<DateFilterItem>()

    // Offset from -2 to +2 days
    for (offset in -2..2) {
        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_YEAR, offset)
        val param = sdfParam.format(c.time)
        val label = when (offset) {
            -1 -> "Yesterday"
            0 -> "Today"
            1 -> "Tomorrow"
            else -> sdfDisplay.format(c.time)
        }
        list.add(DateFilterItem(label, param))
    }
    return list
}

@Composable
fun GameCard(
    game: Game,
    onClick: () -> Unit,
    homeIsFavorite: Boolean = false,
    awayIsFavorite: Boolean = false,
    onToggleHomeFavorite: () -> Unit = {},
    onToggleAwayFavorite: () -> Unit = {},
    isSelected: Boolean = false,
    isNoSpoilers: Boolean = false,
    isScoreRevealed: Boolean = false,
    onRevealScore: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val excitement = getExcitementRating(game.gex)
    val hasFavorite = homeIsFavorite || awayIsFavorite

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 6.dp else 3.dp),
        shape = RoundedCornerShape(12.dp),
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Sport badge, Status, Broadcast badge, Favorite indicator, GEX pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Sport/League Badge + Time & Broadcast info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Compact Sport / League Badge Chip
                    val leagueBadge = when (game.league.lowercase()) {
                        "nba" -> "NBA"
                        "nhl" -> "NHL"
                        "nfl" -> "NFL"
                        "mlb" -> "MLB"
                        "college-football" -> "NCAAF"
                        "mens-college-basketball" -> "NCAAM"
                        "eng.1", "soccer" -> "SOCCER"
                        else -> game.league.ifBlank { game.sport }.uppercase()
                    }
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = leagueBadge,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }

                    if (game.isLive) {
                        Surface(
                            shape = CircleShape,
                            color = Color.Red,
                            modifier = Modifier.size(8.dp)
                        ) {}
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = if (game.isLive) {
                            if (game.timeRemaining.isNotBlank()) "${game.period} - ${game.timeRemaining}"
                            else game.period
                        } else game.period,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (game.isLive) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )

                    // TV Network badge
                    if (game.broadcasts.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = game.broadcasts.take(2).joinToString(" / "),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    // Favorite team star badge
                    if (hasFavorite) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Favorite Team Game",
                            tint = Color(0xFFFFB300),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Thuuz GEX Excitement Rating Pill
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${game.gex}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        color = excitement.color,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(excitement.color)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = excitement.label,
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Teams and Scores with Favorite toggle buttons
            Column {
                TeamRow(
                    team = game.awayTeam,
                    isFavorite = awayIsFavorite,
                    onToggleFavorite = onToggleAwayFavorite,
                    isNoSpoilers = isNoSpoilers,
                    isScoreRevealed = isScoreRevealed,
                    onRevealScore = onRevealScore
                )
                Spacer(modifier = Modifier.height(8.dp))
                TeamRow(
                    team = game.homeTeam,
                    isFavorite = homeIsFavorite,
                    onToggleFavorite = onToggleHomeFavorite,
                    isNoSpoilers = isNoSpoilers,
                    isScoreRevealed = isScoreRevealed,
                    onRevealScore = onRevealScore
                )
            }

            // Teaser line
            if (game.teaser.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = game.teaser,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun TeamRow(
    team: Team,
    isFavorite: Boolean = false,
    onToggleFavorite: () -> Unit = {},
    isNoSpoilers: Boolean = false,
    isScoreRevealed: Boolean = false,
    onRevealScore: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            if (!team.logoUrl.isNullOrBlank()) {
                AsyncImage(
                    model = team.logoUrl,
                    contentDescription = team.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = team.name.take(1),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = team.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )

            // Star icon button to favorite / unfavorite team
            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier.size(32.dp).padding(start = 4.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (isFavorite) Color(0xFFFFB300) else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        if (isNoSpoilers && !isScoreRevealed) {
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.clickable(onClick = onRevealScore)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "Reveal score",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Show",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            Text(
                text = team.score.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
