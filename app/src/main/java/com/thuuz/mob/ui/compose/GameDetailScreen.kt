package com.thuuz.mob.ui.compose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    detail: GameDetailData?,
    isLoading: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    isTwoPane: Boolean = false,
    isNoSpoilers: Boolean = false,
    isScoreRevealed: Boolean = false,
    onRevealScore: () -> Unit = {}
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Summary & Watch", "Highlights", "Plays", "Box Score & Leaders")

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (detail != null) "${detail.awayTeam.name} @ ${detail.homeTeam.name}" else "Game Details",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    if (!isTwoPane) {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        if (isLoading && detail == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (detail == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Game details unavailable.")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Game Header with GEX circle and live score (honoring No Spoilers)
                GameDetailHeader(
                    detail = detail,
                    isNoSpoilers = isNoSpoilers,
                    isScoreRevealed = isScoreRevealed,
                    onRevealScore = onRevealScore
                )

                // Momentum & Excitement Win Probability Curve with Interactive Touch Scrubbing
                MomentumExcitementCurve(
                    winProbabilities = detail.winProbabilities,
                    homeTeamName = detail.homeTeam.name,
                    awayTeamName = detail.awayTeam.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    plays = detail.plays,
                    isNoSpoilers = isNoSpoilers,
                    isScoreRevealed = isScoreRevealed,
                    onRevealScore = onRevealScore
                )

                // Secondary Tab Navigation (Summary, Highlights, Plays, Box Score)
                PrimaryTabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                val label = if (index == 1 && detail.videos.isNotEmpty()) {
                                    "$title (${detail.videos.size})"
                                } else title
                                Text(
                                    text = label,
                                    fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            }
                        )
                    }
                }

                // Tab Content Switcher
                when (selectedTabIndex) {
                    0 -> SummaryAndWatchTab(detail)
                    1 -> VideoHighlightsTab(detail.videos)
                    2 -> ScoringPlaysTab(
                        plays = detail.plays,
                        isNoSpoilers = isNoSpoilers,
                        isScoreRevealed = isScoreRevealed,
                        onRevealScore = onRevealScore
                    )
                    3 -> BoxScoreAndLeadersTab(
                        boxStats = detail.teamBoxStats,
                        leaders = detail.leaders,
                        playerBoxScores = detail.playerBoxScores,
                        isNoSpoilers = isNoSpoilers,
                        isScoreRevealed = isScoreRevealed,
                        onRevealScore = onRevealScore
                    )
                }
            }
        }
    }
}

@Composable
fun GameDetailHeader(
    detail: GameDetailData,
    isNoSpoilers: Boolean = false,
    isScoreRevealed: Boolean = false,
    onRevealScore: () -> Unit = {}
) {
    val excitement = getExcitementRating(detail.gex)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Live / Status banner
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (detail.isLive) {
                    Surface(
                        shape = CircleShape,
                        color = Color.Red,
                        modifier = Modifier.size(8.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Text(
                    text = detail.status,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (detail.isLive) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Scoreboard: Away Team vs Home Team with GEX Pill in the center
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Away Team
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    if (!detail.awayTeam.logoUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = detail.awayTeam.logoUrl,
                            contentDescription = detail.awayTeam.name,
                            modifier = Modifier.size(44.dp)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(detail.awayTeam.name.take(1), fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = detail.awayTeam.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (isNoSpoilers && !isScoreRevealed) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable(onClick = onRevealScore)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "Reveal score",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Show",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Text(
                            text = detail.awayTeam.score.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                // Center Excitement Gauge
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(excitement.color),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${detail.gex}",
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 20.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = excitement.label,
                        color = excitement.color,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Black
                    )
                }

                // Home Team
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    if (!detail.homeTeam.logoUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = detail.homeTeam.logoUrl,
                            contentDescription = detail.homeTeam.name,
                            modifier = Modifier.size(44.dp)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(detail.homeTeam.name.take(1), fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = detail.homeTeam.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (isNoSpoilers && !isScoreRevealed) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable(onClick = onRevealScore)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "Reveal score",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Show",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Text(
                            text = detail.homeTeam.score.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MomentumExcitementCurve(
    winProbabilities: List<WinProbabilityPoint>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
    plays: List<PlayItem> = emptyList(),
    isNoSpoilers: Boolean = false,
    isScoreRevealed: Boolean = false,
    onRevealScore: () -> Unit = {}
) {
    var scrubbedIndex by remember { mutableStateOf<Int?>(null) }
    val isHiddenSpoiler = isNoSpoilers && !isScoreRevealed

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Win Probability & Momentum",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                if (isHiddenSpoiler) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.clickable(onClick = onRevealScore)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "Reveal",
                                modifier = Modifier.size(14.dp),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Reveal Graph",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                } else {
                    Text(
                        text = if (scrubbedIndex != null) "Tap outside to reset" else "50% baseline (drag to scrub)",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (scrubbedIndex != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = if (scrubbedIndex != null) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (isHiddenSpoiler) {
                // Spoiler Masked placeholder for Momentum
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .clickable(onClick = onRevealScore),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Hidden",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Win probability curve masked to avoid spoilers",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Tap here or 'Show' to uncover momentum",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                // Canvas Chart for Probability Swing with Touch/Drag Scrubbing & Dual-Color Split Gradient
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .pointerInput(winProbabilities) {
                            awaitEachGesture {
                                val down = awaitFirstDown(requireUnconsumed = false)
                                if (winProbabilities.isNotEmpty()) {
                                    val ratio = (down.position.x / size.width.toFloat()).coerceIn(0f, 1f)
                                    val idx = (ratio * (winProbabilities.size - 1)).toInt().coerceIn(0, winProbabilities.size - 1)
                                    // Toggle off if tapping same scrub index, otherwise set
                                    scrubbedIndex = if (scrubbedIndex == idx) null else idx
                                }
                                do {
                                    val event = awaitPointerEvent()
                                    val current = event.changes.firstOrNull()
                                    if (current != null && current.pressed && winProbabilities.isNotEmpty()) {
                                        current.consume()
                                        val ratio = (current.position.x / size.width.toFloat()).coerceIn(0f, 1f)
                                        val idx = (ratio * (winProbabilities.size - 1)).toInt().coerceIn(0, winProbabilities.size - 1)
                                        scrubbedIndex = idx
                                    }
                                } while (event.changes.any { it.pressed })
                            }
                        }
                ) {
                    val primaryColor = MaterialTheme.colorScheme.primary
                    val secondaryColor = MaterialTheme.colorScheme.secondary
                    val outlineColor = MaterialTheme.colorScheme.outlineVariant

                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val width = size.width
                        val height = size.height

                        // Center 50% line
                        val midY = height / 2f
                        drawLine(
                            color = outlineColor,
                            start = Offset(0f, midY),
                            end = Offset(width, midY),
                            strokeWidth = 1.5f,
                            pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        )

                        // Vertical Quarter / Period Divider Guides (25%, 50%, 75%)
                        for (q in 1..3) {
                            val qX = width * (q / 4f)
                            drawLine(
                                color = outlineColor.copy(alpha = 0.4f),
                                start = Offset(qX, 0f),
                                end = Offset(qX, height),
                                strokeWidth = 1f,
                                pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(4f, 8f), 0f)
                            )
                        }

                        if (winProbabilities.size >= 2) {
                            val path = Path()
                            val fillPath = Path()
                            val stepX = width / (winProbabilities.size - 1)

                            fillPath.moveTo(0f, midY)

                            winProbabilities.forEachIndexed { i, pt ->
                                val x = i * stepX
                                val y = height - (pt.homeWinPercentage * height)

                                if (i == 0) {
                                    path.moveTo(x, y)
                                    fillPath.lineTo(x, y)
                                } else {
                                    val prevX = (i - 1) * stepX
                                    val prevY = height - (winProbabilities[i - 1].homeWinPercentage * height)
                                    val cX1 = prevX + (stepX / 2f)
                                    val cX2 = prevX + (stepX / 2f)
                                    path.cubicTo(cX1, prevY, cX2, y, x, y)
                                    fillPath.cubicTo(cX1, prevY, cX2, y, x, y)
                                }
                            }

                            fillPath.lineTo(width, midY)
                            fillPath.close()

                            // Draw shaded area under/over momentum curve
                            drawPath(
                                path = fillPath,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        primaryColor.copy(alpha = 0.35f),
                                        Color.Transparent,
                                        secondaryColor.copy(alpha = 0.35f)
                                    )
                                )
                            )

                            // Draw smooth win probability momentum line
                            drawPath(
                                path = path,
                                color = primaryColor,
                                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                            )

                            // Draw scrubbing line and indicator circle
                            if (scrubbedIndex != null) {
                                val sIdx = scrubbedIndex!!.coerceIn(0, winProbabilities.size - 1)
                                val scrubX = sIdx * stepX
                                val scrubY = height - (winProbabilities[sIdx].homeWinPercentage * height)

                                drawLine(
                                    color = primaryColor.copy(alpha = 0.8f),
                                    start = Offset(scrubX, 0f),
                                    end = Offset(scrubX, height),
                                    strokeWidth = 2.dp.toPx()
                                )

                                drawCircle(
                                    color = Color.White,
                                    radius = 7.dp.toPx(),
                                    center = Offset(scrubX, scrubY)
                                )
                                drawCircle(
                                    color = primaryColor,
                                    radius = 4.5.dp.toPx(),
                                    center = Offset(scrubX, scrubY)
                                )
                            }
                        }
                    }
                }
            }

            // Scrubbing HUD / Tooltip
            if (scrubbedIndex != null && winProbabilities.isNotEmpty()) {
                val sIdx = scrubbedIndex!!.coerceIn(0, winProbabilities.size - 1)
                val pt = winProbabilities[sIdx]
                val homeProb = pt.homeWinPercentage
                val diffFromEven = abs(homeProb - 0.5f)
                val evenness = (1.0f - diffFromEven * 2f).coerceIn(0f, 1f)
                val progress = (sIdx.toFloat() / (winProbabilities.size - 1).coerceAtLeast(1))
                val instantGex = ((38 + evenness * 42 + progress * 18).toInt()).coerceIn(20, 99)
                val instantRating = getExcitementRating(instantGex)

                val matchedPlay = if (plays.isNotEmpty()) {
                    val chronoIndex = (progress * (plays.size - 1)).toInt().coerceIn(0, plays.size - 1)
                    plays[plays.size - 1 - chronoIndex]
                } else null

                val timeClockText = matchedPlay?.timeDetail?.ifBlank { null } ?: "T-${(progress * 100).toInt()}%"
                val playText = matchedPlay?.text ?: if (homeProb >= 0.5f) "$homeTeamName in command" else "$awayTeamName in command"
                val probText = if (homeProb >= 0.5f) "${(homeProb * 100).toInt()}% $homeTeamName Win Prob"
                               else "${((1f - homeProb) * 100).toInt()}% $awayTeamName Win Prob"

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = timeClockText,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = probText,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = instantRating.color
                            ) {
                                Text(
                                    text = "$instantGex GEX",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        if (playText.isNotBlank()) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = playText,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "▲ $homeTeamName Favored",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "▼ $awayTeamName Favored",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

fun getBroadcastNetworkUrl(channel: String): String {
    val clean = channel.lowercase().trim()
    return when {
        clean.contains("peacock") -> "https://www.peacocktv.com"
        clean.contains("paramount") || clean.contains("cbs") -> "https://www.paramountplus.com"
        clean.contains("prime") || clean.contains("amazon") -> "https://www.amazon.com/primevideo"
        clean.contains("tnt") || clean.contains("tbs") || clean.contains("max") -> "https://www.max.com"
        clean.contains("apple") -> "https://tv.apple.com"
        clean.contains("fox") || clean.contains("fs1") || clean.contains("fs2") -> "https://www.foxsports.com/live"
        clean.contains("espn") || clean.contains("abc") -> "https://www.espn.com/watch/"
        clean.contains("nbc") -> "https://www.nbcsports.com"
        else -> "https://www.google.com/search?q=watch+" + java.net.URLEncoder.encode(channel, "UTF-8") + "+live"
    }
}

fun getDirectGameStreamingUrl(detail: GameDetailData): String {
    if (detail.gameId.isNotBlank()) {
        val sportPath = if (!detail.sport.isNullOrBlank()) detail.sport else "sports"
        return "https://www.espn.com/$sportPath/game/_/gameId/${detail.gameId}"
    }
    val broadcastsLower = detail.broadcasts.map { it.lowercase() }
    return when {
        broadcastsLower.any { it.contains("peacock") } -> "https://www.peacocktv.com"
        broadcastsLower.any { it.contains("paramount") || it.contains("cbs") } -> "https://www.paramountplus.com"
        broadcastsLower.any { it.contains("prime") || it.contains("amazon") } -> "https://www.amazon.com/primevideo"
        else -> "https://www.espn.com/watch/"
    }
}

@Composable
fun SummaryAndWatchTab(detail: GameDetailData) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Teaser & Recap
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Game Recap & Storyline",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = detail.teaser,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // Where to Watch Section
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Where to Watch / Stream",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    if (detail.broadcasts.isNotEmpty()) {
                        Text(
                            text = "Tune In / Watch Options (Tap to Open):",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                        ) {
                            detail.broadcasts.forEach { channel ->
                                val channelUrl = getBroadcastNetworkUrl(channel)
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    modifier = Modifier.clickable {
                                        try {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(channelUrl))
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(
                                            text = "▶  $channel",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            text = "Broadcast listings unavailable for this market.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Primary GameCast / Watch Live button
                    val targetUrl = getDirectGameStreamingUrl(detail)
                    Button(
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(targetUrl))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Open ESPN Gamecast / Watch Live")
                    }
                }
            }
        }
    }
}

@Composable
fun ScoringPlaysTab(
    plays: List<PlayItem>,
    isNoSpoilers: Boolean = false,
    isScoreRevealed: Boolean = false,
    onRevealScore: () -> Unit = {}
) {
    var scoringOnly by remember { mutableStateOf(false) }
    val isHidden = isNoSpoilers && !isScoreRevealed

    val displayedPlays = remember(plays, scoringOnly) {
        if (scoringOnly) plays.filter { it.isScoring } else plays
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Toggle Filter Chips: "All Plays" vs "Scoring Plays Only"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterChip(
                selected = !scoringOnly,
                onClick = { scoringOnly = false },
                label = { Text("All Plays (${plays.size})") }
            )
            val scoringCount = plays.count { it.isScoring }
            FilterChip(
                selected = scoringOnly,
                onClick = { scoringOnly = true },
                label = { Text("Scoring Plays Only ($scoringCount)") }
            )
        }

        if (displayedPlays.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (scoringOnly) "No scoring plays recorded yet." else "No key plays recorded yet.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(displayedPlays) { play ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (play.isScoring) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                            else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = play.timeDetail,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    if (play.isScoring) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Surface(
                                            shape = RoundedCornerShape(4.dp),
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.padding(vertical = 1.dp)
                                        ) {
                                            Text(
                                                text = play.type.ifBlank { "SCORE" }.uppercase(),
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                style = MaterialTheme.typography.labelSmall,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = play.text,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            if (play.isScoring) {
                                Spacer(modifier = Modifier.width(12.dp))
                                if (isHidden) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant,
                                        modifier = Modifier.clickable(onClick = onRevealScore)
                                    ) {
                                        Text(
                                            text = "? - ?",
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                } else {
                                    Text(
                                        text = "${play.awayScore} - ${play.homeScore}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VideoHighlightsTab(videos: List<VideoHighlight>) {
    val context = LocalContext.current

    if (videos.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No video highlights available for this game yet.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(videos, key = { it.id }) { video ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.videoUrl))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column {
                        // Video Thumbnail Container with Duration Badge & Play Button Overlay
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .background(Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            if (!video.thumbnailUrl.isNullOrBlank()) {
                                AsyncImage(
                                    model = video.thumbnailUrl,
                                    contentDescription = video.headline,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            // Play Button Icon Overlay
                            Surface(
                                shape = CircleShape,
                                color = Color.Black.copy(alpha = 0.6f),
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Play Video",
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }

                            // Duration badge in bottom right corner
                            if (video.durationDisplay.isNotBlank()) {
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color.Black.copy(alpha = 0.75f),
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = video.durationDisplay,
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }

                        // Video Title & Description
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = video.headline,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            if (video.description.isNotBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = video.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BoxScoreAndLeadersTab(
    boxStats: List<TeamBoxStat>,
    leaders: List<LeaderCategory>,
    playerBoxScores: List<TeamPlayerBoxScore> = emptyList(),
    isNoSpoilers: Boolean = false,
    isScoreRevealed: Boolean = false,
    onRevealScore: () -> Unit = {}
) {
    var selectedTeamIndex by remember { mutableStateOf(0) }
    val isHidden = isNoSpoilers && !isScoreRevealed

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Full Player Box Scores Section
        if (playerBoxScores.isNotEmpty()) {
            item {
                Text(
                    text = "Player Box Scores",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            // Team Switcher Segmented Control
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    playerBoxScores.forEachIndexed { index, teamBox ->
                        val isSelected = selectedTeamIndex == index
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedTeamIndex = index },
                            label = {
                                Text(
                                    text = teamBox.teamAbbr.ifBlank { teamBox.teamName },
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            val currentTeamBox = playerBoxScores.getOrElse(selectedTeamIndex) { playerBoxScores.first() }
            val starters = currentTeamBox.players.filter { it.isStarter }
            val bench = currentTeamBox.players.filter { !it.isStarter }

            if (starters.isNotEmpty()) {
                item {
                    Text(
                        text = "STARTERS",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                items(starters, key = { "starter_${it.id}_${it.name}" }) { player ->
                    PlayerBoxScoreRow(player = player)
                }
            }

            if (bench.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "BENCH",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                items(bench, key = { "bench_${it.id}_${it.name}" }) { player ->
                    PlayerBoxScoreRow(player = player)
                }
            }

            if (starters.isEmpty() && bench.isEmpty() && currentTeamBox.players.isNotEmpty()) {
                items(currentTeamBox.players, key = { "player_${it.id}_${it.name}" }) { player ->
                    PlayerBoxScoreRow(player = player)
                }
            }
        }

        // Game Leaders
        if (leaders.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Top Performers & Leaders",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            items(leaders) { cat ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!cat.leader.headshotUrl.isNullOrBlank()) {
                            AsyncImage(
                                model = cat.leader.headshotUrl,
                                contentDescription = cat.leader.name,
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(cat.leader.name.take(1), fontWeight = FontWeight.Bold)
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = cat.category,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = cat.leader.name,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            if (cat.leader.position.isNotBlank() || cat.leader.jersey.isNotBlank()) {
                                Text(
                                    text = "#${cat.leader.jersey} · ${cat.leader.position}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Text(
                            text = cat.leader.statValue,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Team Box Stats comparison
        if (boxStats.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Team Statistics",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            items(boxStats) { stat ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isHidden && stat.label.contains("Point", ignoreCase = true)) "??" else stat.awayValue,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stat.label,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = if (isHidden && stat.label.contains("Point", ignoreCase = true)) "??" else stat.homeValue,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerBoxScoreRow(player: PlayerBoxStat) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    if (!player.headshotUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = player.headshotUrl,
                            contentDescription = player.name,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray.copy(alpha = 0.5f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(player.name.take(1), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = player.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        val posJersey = listOfNotNull(
                            if (player.jersey.isNotBlank()) "#${player.jersey}" else null,
                            if (player.position.isNotBlank()) player.position else null
                        ).joinToString(" · ")
                        if (posJersey.isNotBlank()) {
                            Text(
                                text = posJersey,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                if (player.displayStatsSummary.isNotBlank()) {
                    Text(
                        text = player.displayStatsSummary,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Stat breakdown chips
            if (player.stats.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    player.stats.forEach { (label, value) ->
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Row(modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)) {
                                Text(
                                    text = "$label: ",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = value,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
