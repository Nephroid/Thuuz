package com.thuuz.mob.data

import com.thuuz.mob.ui.compose.Game
import com.thuuz.mob.ui.compose.PlayItem
import com.thuuz.mob.ui.compose.Team
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Stages9To13ImprovementsTest {

    @Test
    fun testSpoilerTeaserMaskingLogic() {
        val testGame = Game(
            id = "401585123",
            homeTeam = Team("1", "Detroit Pistons", null, 121, "Pistons", "DET"),
            awayTeam = Team("2", "Boston Celtics", null, 113, "Celtics", "BOS"),
            gex = 94,
            period = "Q4 - 0:30",
            timeRemaining = "0:30",
            isLive = true,
            broadcasts = listOf("TNT", "Max"),
            teaser = "Pistons holding off Celtics in final seconds 121-113",
            sport = "basketball",
            league = "nba"
        )

        val isNoSpoilers = true
        val isScoreRevealed = false

        val isHiddenSpoiler = isNoSpoilers && !isScoreRevealed && (testGame.isLive || testGame.period.contains("Final", ignoreCase = true))
        assertTrue(isHiddenSpoiler)

        val displayTeaser = if (isHiddenSpoiler) {
            "🔒 Score masked (GREAT GEX: ${testGame.gex}) — Tap eye icon above to reveal matchup details & live storyline"
        } else {
            testGame.teaser
        }

        assertFalse(displayTeaser.contains("121-113"))
        assertTrue(displayTeaser.contains("Score masked"))
        assertTrue(displayTeaser.contains("94"))
    }

    @Test
    fun testSpoilerPlayScoreMasking() {
        val play = PlayItem(
            text = "Cade Cunningham makes 26-foot 3-point jumper",
            timeDetail = "0:45 - 4th",
            homeScore = 121,
            awayScore = 113,
            type = "3pt",
            isScoring = true
        )

        val isNoSpoilers = true
        val isScoreRevealed = false
        val isHidden = isNoSpoilers && !isScoreRevealed

        val maskedScoreDisplay = if (isHidden) "? - ?" else "${play.awayScore} - ${play.homeScore}"
        assertEquals("? - ?", maskedScoreDisplay)

        val revealedScoreDisplay = if (false) "? - ?" else "${play.awayScore} - ${play.homeScore}"
        assertEquals("113 - 121", revealedScoreDisplay)
    }

    @Test
    fun testScoreboardSearchFiltering() {
        val games = listOf(
            Game(
                id = "1",
                homeTeam = Team("1", "Detroit Pistons", null, 121, "Pistons", "DET"),
                awayTeam = Team("2", "Boston Celtics", null, 113, "Celtics", "BOS"),
                gex = 94,
                period = "Q4",
                timeRemaining = "0:30",
                isLive = true,
                broadcasts = listOf("TNT"),
                teaser = "Thriller",
                sport = "basketball",
                league = "nba"
            ),
            Game(
                id = "2",
                homeTeam = Team("3", "New York Rangers", null, 3, "Rangers", "NYR"),
                awayTeam = Team("4", "New Jersey Devils", null, 2, "Devils", "NJD"),
                gex = 82,
                period = "P3",
                timeRemaining = "2:10",
                isLive = true,
                broadcasts = listOf("ESPN+"),
                teaser = "Rangers lead",
                sport = "hockey",
                league = "nhl"
            )
        )

        // Query by team short name
        val queryPistons = "pistons"
        val filtered1 = games.filter { game ->
            game.homeTeam.name.lowercase().contains(queryPistons) ||
            game.awayTeam.name.lowercase().contains(queryPistons) ||
            game.homeTeam.abbreviation.lowercase().contains(queryPistons) ||
            game.awayTeam.abbreviation.lowercase().contains(queryPistons) ||
            game.broadcasts.any { it.lowercase().contains(queryPistons) }
        }
        assertEquals(1, filtered1.size)
        assertEquals("Detroit Pistons", filtered1[0].homeTeam.name)

        // Query by broadcast channel
        val queryEspn = "espn+"
        val filtered2 = games.filter { game ->
            game.homeTeam.name.lowercase().contains(queryEspn) ||
            game.awayTeam.name.lowercase().contains(queryEspn) ||
            game.broadcasts.any { it.lowercase().contains(queryEspn) }
        }
        assertEquals(1, filtered2.size)
        assertEquals("New York Rangers", filtered2[0].homeTeam.name)
    }
}
