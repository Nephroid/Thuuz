package com.thuuz.mob.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Team(
    val id: String,
    val name: String,
    val logoUrl: String? = null,
    val score: Int
)

data class Game(
    val id: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val gex: Int, // 0-100
    val period: String,
    val timeRemaining: String,
    val isLive: Boolean
)

enum class ExcitementRating(val label: String, val color: Color) {
    GREAT("GREAT", Color(0xFF4CAF50)), // Green
    GOOD("GOOD", Color(0xFF2196F3)),   // Blue
    OK("OK", Color(0xFFFFC107)),       // Yellow
    DUD("DUD", Color(0xFF9E9E9E))      // Gray
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
fun ScoreboardScreen(games: List<Game>, modifier: Modifier = Modifier) {
    // Sort games by GEX descending
    val sortedGames = games.sortedByDescending { it.gex }
    
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sortedGames) { game ->
            GameCard(game = game)
        }
    }
}

@Composable
fun GameCard(game: Game, modifier: Modifier = Modifier) {
    val excitement = getExcitementRating(game.gex)

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Status and Excitement Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Time/Status
                Text(
                    text = if (game.isLive) "${game.period} - ${game.timeRemaining}" else "Final",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (game.isLive) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )

                // Excitement Badge
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "GEX ${game.gex}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(excitement.color)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = excitement.label,
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            // Teams and Scores
            Column {
                TeamRow(team = game.awayTeam)
                Spacer(modifier = Modifier.height(8.dp))
                TeamRow(team = game.homeTeam)
            }
        }
    }
}

@Composable
fun TeamRow(team: Team) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Placeholder for logo
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = team.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            text = team.score.toString(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameCardPreview() {
    val sampleGame = Game(
        id = "1",
        homeTeam = Team(id = "GSW", name = "Golden State Warriors", score = 104),
        awayTeam = Team(id = "BOS", name = "Boston Celtics", score = 101),
        gex = 92,
        period = "Q4",
        timeRemaining = "2:14",
        isLive = true
    )
    MaterialTheme {
        GameCard(game = sampleGame, modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreboardScreenPreview() {
    val games = listOf(
        Game(
            id = "1",
            homeTeam = Team(id = "GSW", name = "Golden State", score = 104),
            awayTeam = Team(id = "BOS", name = "Boston", score = 101),
            gex = 92,
            period = "Q4",
            timeRemaining = "2:14",
            isLive = true
        ),
        Game(
            id = "2",
            homeTeam = Team(id = "LAL", name = "Lakers", score = 88),
            awayTeam = Team(id = "MIA", name = "Heat", score = 95),
            gex = 75,
            period = "Q3",
            timeRemaining = "5:00",
            isLive = true
        ),
        Game(
            id = "3",
            homeTeam = Team(id = "CHI", name = "Bulls", score = 110),
            awayTeam = Team(id = "NYK", name = "Knicks", score = 90),
            gex = 55,
            period = "Final",
            timeRemaining = "",
            isLive = false
        ),
        Game(
            id = "4",
            homeTeam = Team(id = "DET", name = "Pistons", score = 80),
            awayTeam = Team(id = "ORL", name = "Magic", score = 115),
            gex = 20,
            period = "Final",
            timeRemaining = "",
            isLive = false
        )
    )
    MaterialTheme {
        ScoreboardScreen(games = games)
    }
}
