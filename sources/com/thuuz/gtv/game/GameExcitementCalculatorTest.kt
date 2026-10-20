package com.thuuz.gtv.game

import org.junit.Assert.assertEquals
import org.junit.Test

class GameExcitementCalculatorTest {

    private val calculator = GameExcitementCalculator()

    @Test
    fun testUpcomingGameWithPredictNoFan() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = true,
            hasGexPredict = true,
            gexPredict = 50,
            hasGex = false,
            gex = 0,
            isFanOfHomeTeam = false,
            isFanOfAwayTeam = false,
            homeFanAdjust = 0,
            awayFanAdjust = 0
        )
        assertEquals(50, excitement)
    }

    @Test
    fun testUpcomingGameWithPredictAsFan() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = true,
            hasGexPredict = true,
            gexPredict = 50,
            hasGex = false,
            gex = 0,
            isFanOfHomeTeam = true,
            isFanOfAwayTeam = false,
            homeFanAdjust = 0,
            awayFanAdjust = 0
        )
        // max(50 + 10, (50 / 10) + 55) = max(60, 5 + 55) = 60
        assertEquals(60, excitement)
    }

    @Test
    fun testUpcomingGameWithPredictAsFanLowScore() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = true,
            hasGexPredict = true,
            gexPredict = 10,
            hasGex = false,
            gex = 0,
            isFanOfHomeTeam = true,
            isFanOfAwayTeam = false,
            homeFanAdjust = 0,
            awayFanAdjust = 0
        )
        // max(10 + 10, (10 / 10) + 55) = max(20, 1 + 55) = 56
        assertEquals(56, excitement)
    }

    @Test
    fun testLiveGameNoFan() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = false,
            hasGexPredict = false,
            gexPredict = 0,
            hasGex = true,
            gex = 70,
            isFanOfHomeTeam = false,
            isFanOfAwayTeam = false,
            homeFanAdjust = 0,
            awayFanAdjust = 0
        )
        assertEquals(70, excitement)
    }

    @Test
    fun testLiveGameHomeFan() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = false,
            hasGexPredict = false,
            gexPredict = 0,
            hasGex = true,
            gex = 70,
            isFanOfHomeTeam = true,
            isFanOfAwayTeam = false,
            homeFanAdjust = 15,
            awayFanAdjust = 5
        )
        assertEquals(85, excitement)
    }

    @Test
    fun testLiveGameAwayFan() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = false,
            hasGexPredict = false,
            gexPredict = 0,
            hasGex = true,
            gex = 70,
            isFanOfHomeTeam = false,
            isFanOfAwayTeam = true,
            homeFanAdjust = 15,
            awayFanAdjust = 5
        )
        assertEquals(75, excitement)
    }

    @Test
    fun testLiveGameBothFan() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = false,
            hasGexPredict = false,
            gexPredict = 0,
            hasGex = true,
            gex = 70,
            isFanOfHomeTeam = true,
            isFanOfAwayTeam = true,
            homeFanAdjust = 15,
            awayFanAdjust = 5
        )
        // max(15, 5) = 15
        assertEquals(85, excitement)
    }

    @Test
    fun testLiveGameCappedAt100() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = false,
            hasGexPredict = false,
            gexPredict = 0,
            hasGex = true,
            gex = 90,
            isFanOfHomeTeam = true,
            isFanOfAwayTeam = false,
            homeFanAdjust = 15,
            awayFanAdjust = 5
        )
        // 90 + 15 = 105 -> capped at 100
        assertEquals(100, excitement)
    }

    @Test
    fun testLiveGameNegativeAdjustIgnored() {
        val excitement = calculator.calculateGameExcitement(
            isUpcoming = false,
            hasGexPredict = false,
            gexPredict = 0,
            hasGex = true,
            gex = 70,
            isFanOfHomeTeam = true,
            isFanOfAwayTeam = false,
            homeFanAdjust = -10,
            awayFanAdjust = 0
        )
        // max(0, -10) = 0
        assertEquals(70, excitement)
    }

    @Test
    fun testGameRating() {
        assertEquals(GameRating.GREAT, calculator.getGameRating(85))
        assertEquals(GameRating.GREAT, calculator.getGameRating(100))
        assertEquals(GameRating.GOOD, calculator.getGameRating(65))
        assertEquals(GameRating.GOOD, calculator.getGameRating(84))
        assertEquals(GameRating.OK, calculator.getGameRating(40))
        assertEquals(GameRating.OK, calculator.getGameRating(64))
        assertEquals(GameRating.DUD, calculator.getGameRating(0))
        assertEquals(GameRating.DUD, calculator.getGameRating(39))
    }
}
