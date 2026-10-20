package com.thuuz.gtv.game

import kotlin.math.max
import kotlin.math.min

enum class GameRating {
    DUD,
    OK,
    GOOD,
    GREAT
}

class GameExcitementCalculator {
    fun calculateGameExcitement(
        isUpcoming: Boolean,
        hasGexPredict: Boolean,
        gexPredict: Int,
        hasGex: Boolean,
        gex: Int,
        isFanOfHomeTeam: Boolean,
        isFanOfAwayTeam: Boolean,
        homeFanAdjust: Int,
        awayFanAdjust: Int
    ): Int {
        var result = 0
        if (isUpcoming) {
            result = calculatePredictedExcitement(hasGexPredict, gexPredict, isFanOfHomeTeam, isFanOfAwayTeam)
        } else if (hasGex) {
            result = gex
            val isFan = isFanOfHomeTeam || isFanOfAwayTeam
            if (isFan) {
                val z = max(0, homeFanAdjust)
                val y = max(0, awayFanAdjust)
                
                if (isFanOfHomeTeam && isFanOfAwayTeam) {
                    result += max(z, y)
                } else if (isFanOfHomeTeam) {
                    result += z
                } else {
                    result += y
                }
            }
        }
        return min(result, 100)
    }

    private fun calculatePredictedExcitement(
        hasGexPredict: Boolean,
        gexPredict: Int,
        isFanOfHomeTeam: Boolean,
        isFanOfAwayTeam: Boolean
    ): Int {
        if (!hasGexPredict) {
            return 0
        }
        val isFan = isFanOfHomeTeam || isFanOfAwayTeam
        if (isFan) {
            return max(gexPredict + 10, (gexPredict / 10) + 55)
        }
        return gexPredict
    }

    fun getGameRating(excitement: Int): GameRating {
        return when {
            excitement >= 85 -> GameRating.GREAT
            excitement >= 65 -> GameRating.GOOD
            excitement >= 40 -> GameRating.OK
            else -> GameRating.DUD
        }
    }
}
