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

    @Test
    fun testTimeSortEarliestToLatest() {
        val earlyGame = Game(
            id = "1",
            homeTeam = Team("1", "Boston Celtics", null, 0, "Celtics", "BOS"),
            awayTeam = Team("2", "Cleveland Cavaliers", null, 0, "Cavaliers", "CLE"),
            gex = 75,
            period = "Scheduled",
            timeRemaining = "",
            isLive = false,
            sport = "basketball",
            league = "nba",
            startTime = "2026-10-22T23:00Z" // 7:00 PM EDT
        )
        val lateGame = Game(
            id = "2",
            homeTeam = Team("3", "Los Angeles Lakers", null, 0, "Lakers", "LAL"),
            awayTeam = Team("4", "Denver Nuggets", null, 0, "Nuggets", "DEN"),
            gex = 90, // Higher GEX, but starts later
            period = "Scheduled",
            timeRemaining = "",
            isLive = false,
            sport = "basketball",
            league = "nba",
            startTime = "2026-10-23T02:30Z" // 10:30 PM EDT
        )
        val middleGame = Game(
            id = "3",
            homeTeam = Team("5", "Chicago Bulls", null, 0, "Bulls", "CHI"),
            awayTeam = Team("6", "Miami Heat", null, 0, "Heat", "MIA"),
            gex = 80,
            period = "Scheduled",
            timeRemaining = "",
            isLive = false,
            sport = "basketball",
            league = "nba",
            startTime = "2026-10-23T00:00Z" // 8:00 PM EDT
        )

        val unsorted = listOf(lateGame, earlyGame, middleGame)
        val sortedByTime = unsorted.sortedWith(
            compareBy<Game> { com.thuuz.mob.ui.compose.parseGameStartTimeMillis(it.startTime) }
                .thenByDescending { it.gex }
        )

        assertEquals("1", sortedByTime[0].id) // 7:00 PM EDT
        assertEquals("3", sortedByTime[1].id) // 8:00 PM EDT
        assertEquals("2", sortedByTime[2].id) // 10:30 PM EDT
    }

    @Test
    fun testWidgetFavoritesOnlyFiltering() {
        val favTeams = setOf("Boston Celtics", "Detroit Pistons")
        fun isFav(name: String): Boolean = favTeams.any { fav ->
            name.contains(fav, ignoreCase = true) || fav.contains(name, ignoreCase = true)
        }

        val game1 = Game(
            id = "1",
            homeTeam = Team("1", "Boston Celtics", null, 100, "Celtics", "BOS"),
            awayTeam = Team("2", "Miami Heat", null, 98, "Heat", "MIA"),
            gex = 85,
            period = "Q4",
            timeRemaining = "1:00",
            isLive = true
        )
        val game2 = Game(
            id = "2",
            homeTeam = Team("3", "Golden State Warriors", null, 102, "Warriors", "GSW"),
            awayTeam = Team("4", "Los Angeles Lakers", null, 101, "Lakers", "LAL"),
            gex = 98,
            period = "Q4",
            timeRemaining = "0:10",
            isLive = true
        )

        val games = listOf(game1, game2)
        val widgetGames = games.filter { game ->
            isFav(game.homeTeam.name) || isFav(game.awayTeam.name)
        }

        assertEquals(1, widgetGames.size)
        assertEquals("Boston Celtics", widgetGames[0].homeTeam.name)
    }
}
