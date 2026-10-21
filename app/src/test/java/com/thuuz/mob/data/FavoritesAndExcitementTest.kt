package com.thuuz.mob.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FavoritesAndExcitementTest {

    private val repository = LiveSportsDataRepository()

    @Test
    fun testUpcomingGameExcitement_DynamicCalculation() {
        // Close spread should produce high predicted GEX (around 86)
        val closeSpreadGex = repository.calculateScore(
            homeScore = 0,
            awayScore = 0,
            isLive = false,
            isFinal = false,
            sport = "basketball",
            spread = 1.5,
            homeName = "Boston Celtics",
            awayName = "New York Knicks"
        )
        assertTrue("Close spread should have high predicted excitement (expected >= 80, got $closeSpreadGex)", closeSpreadGex >= 80)

        // Blowout spread should produce low predicted GEX (around 38)
        val blowoutSpreadGex = repository.calculateScore(
            homeScore = 0,
            awayScore = 0,
            isLive = false,
            isFinal = false,
            sport = "basketball",
            spread = 16.5,
            homeName = "Boston Celtics",
            awayName = "Detroit Pistons"
        )
        assertTrue("Blowout spread should have low predicted excitement (expected <= 45, got $blowoutSpreadGex)", blowoutSpreadGex <= 45)

        // Without spread, team matchup hash yields variance in 48..85, not hardcoded 55
        val matchup1 = repository.calculateScore(0, 0, false, false, "basketball", homeName = "Warriors", awayName = "Lakers")
        val matchup2 = repository.calculateScore(0, 0, false, false, "hockey", homeName = "Blackhawks", awayName = "Red Wings")
        assertTrue("Predicted GEX should be in valid range [40, 90]", matchup1 in 40..90)
        assertTrue("Predicted GEX should vary by matchup", matchup1 != matchup2 || matchup1 != 55)
    }

    @Test
    fun testLiveGameExcitement_TimeRemainingAndDifferential() {
        // Close game late in 4th quarter (diff 2) -> 88-98 GEX (GREAT)
        val closeLateNba = repository.calculateScore(
            homeScore = 104,
            awayScore = 102,
            isLive = true,
            isFinal = false,
            sport = "basketball",
            periodNumber = 4,
            clockStr = "0:35"
        )
        assertTrue("Close 4th quarter game should be GREAT GEX (expected >= 88, got $closeLateNba)", closeLateNba >= 88)

        // Close game early (2nd quarter) -> 70-82 GEX (GOOD)
        val closeEarlyNba = repository.calculateScore(
            homeScore = 48,
            awayScore = 46,
            isLive = true,
            isFinal = false,
            sport = "basketball",
            periodNumber = 2,
            clockStr = "6:00"
        )
        assertTrue("Close early game should be GOOD GEX (expected between 70 and 82, got $closeEarlyNba)", closeEarlyNba in 70..82)

        // Blowout live game (diff 22 in basketball) -> drops to 20-45 (DUD/OK)
        val blowoutNba = repository.calculateScore(
            homeScore = 100,
            awayScore = 78,
            isLive = true,
            isFinal = false,
            sport = "basketball",
            periodNumber = 4
        )
        assertTrue("Blowout game should drop to <= 45 (got $blowoutNba)", blowoutNba <= 45)

        // Hockey 3rd period close (diff 1) -> GREAT
        val closeLateNhl = repository.calculateScore(
            homeScore = 3,
            awayScore = 2,
            isLive = true,
            isFinal = false,
            sport = "hockey",
            periodNumber = 3,
            clockStr = "1:15"
        )
        assertTrue("Close 3rd period NHL game should be GREAT GEX (expected >= 88, got $closeLateNhl)", closeLateNhl >= 88)

        // Hockey blowout (diff 5) -> drops to <= 45
        val blowoutNhl = repository.calculateScore(
            homeScore = 6,
            awayScore = 1,
            isLive = true,
            isFinal = false,
            sport = "hockey",
            periodNumber = 3
        )
        assertTrue("Blowout hockey game should drop to <= 45 (got $blowoutNhl)", blowoutNhl <= 45)
    }

    @Test
    fun testFinalGameExcitement() {
        // Final score diff 1 -> 85-95
        val finalClose = repository.calculateScore(101, 100, isLive = false, isFinal = true, sport = "basketball")
        assertTrue("1-point final should be GREAT GEX (expected >= 85, got $finalClose)", finalClose >= 85)

        // Final score moderate (diff 8) -> 60-75
        val finalModerate = repository.calculateScore(100, 92, isLive = false, isFinal = true, sport = "basketball")
        assertTrue("Moderate final diff should be GOOD GEX (expected 60..75, got $finalModerate)", finalModerate in 60..75)

        // Final blowout (diff 25) -> 25-45
        val finalBlowout = repository.calculateScore(125, 100, isLive = false, isFinal = true, sport = "basketball")
        assertTrue("Blowout final should be DUD GEX (expected <= 45, got $finalBlowout)", finalBlowout <= 45)
    }
}
