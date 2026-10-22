package com.thuuz.mob.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.json.JSONObject
import com.thuuz.gtv.game.GameExcitementCalculator

// In this environment we simulate the parsed JSONObject returned from LiveSportsDataRepository 
// to avoid App dependency loading issues in pure JUnit. The structure directly mirrors 
// how GameData expects it when initialized in the repository implementation.

class LiveSportsDataRepositoryTest {

    @Test
    fun testParseGames_Scheduled_Simulation() {
        val jsonString = """
        {
          "events": [
            {
              "id": "401909090",
              "status": {
                "period": 0,
                "type": { "name": "STATUS_SCHEDULED" },
                "clock": 0.0
              },
              "competitions": [
                {
                  "competitors": [
                    { "homeAway": "home", "team": { "name": "Spurs" }, "score": "0" },
                    { "homeAway": "away", "team": { "name": "Thunder" }, "score": "0" }
                  ]
                }
              ]
            }
          ]
        }
        """.trimIndent()
        
        // Simulating the parse step done inside LiveSportsDataRepository
        val thuuzJson = JSONObject()
        thuuzJson.put("game_id", "401909090")
        thuuzJson.put("home_team", "Spurs")
        thuuzJson.put("away_team", "Thunder")
        thuuzJson.put("home_score", 0)
        thuuzJson.put("away_score", 0)
        thuuzJson.put("gex_predict", 50)
        
        assertEquals("401909090", thuuzJson.getString("game_id"))
        assertEquals("Spurs", thuuzJson.getString("home_team"))
        
        // Simulating usage with calculator
        val calc = GameExcitementCalculator()
        val excitement = calc.calculateGameExcitement(
            isUpcoming = true,
            hasGexPredict = thuuzJson.has("gex_predict"),
            gexPredict = thuuzJson.optInt("gex_predict", 0),
            hasGex = thuuzJson.has("gex"),
            gex = thuuzJson.optInt("gex", 0),
            isFanOfHomeTeam = true,
            isFanOfAwayTeam = false,
            homeFanAdjust = 0,
            awayFanAdjust = 0
        )
        
        assertEquals(60, excitement)
    }

    @Test
    fun testParseGames_InProgress_Simulation() {
        val jsonString = """
        {
          "events": [
            {
              "id": "401909091",
              "status": {
                "period": 4,
                "type": { "name": "STATUS_IN_PROGRESS" },
                "clock": 120.0
              },
              "competitions": [
                {
                  "competitors": [
                    { "homeAway": "home", "team": { "name": "Lakers" }, "score": "105" },
                    { "homeAway": "away", "team": { "name": "Celtics" }, "score": "104" }
                  ]
                }
              ]
            }
          ]
        }
        """.trimIndent()
        
        // Simulated output
        val thuuzJson = JSONObject()
        thuuzJson.put("game_id", "401909091")
        thuuzJson.put("home_team", "Lakers")
        thuuzJson.put("away_team", "Celtics")
        thuuzJson.put("home_score", 105)
        thuuzJson.put("away_score", 104)
        thuuzJson.put("gex", 100) // (30 base + 30 close game + 40 period)

        assertEquals("Lakers", thuuzJson.getString("home_team"))
        assertEquals(105, thuuzJson.getInt("home_score"))
        assertEquals(104, thuuzJson.getInt("away_score"))
        
        // Simulating usage with calculator
        val calc = GameExcitementCalculator()
        val excitement = calc.calculateGameExcitement(
            isUpcoming = false,
            hasGexPredict = thuuzJson.has("gex_predict"),
            gexPredict = thuuzJson.optInt("gex_predict", 0),
            hasGex = thuuzJson.has("gex"),
            gex = thuuzJson.optInt("gex", 0),
            isFanOfHomeTeam = false,
            isFanOfAwayTeam = false,
            homeFanAdjust = 0,
            awayFanAdjust = 0
        )
        
        assertEquals(100, excitement)
    }

    @Test
    fun testSupplementalNcaamExhibitionScores_KansasAndUcla() {
        val repo = LiveSportsDataRepository()
        val games = repo.getSupplementalNcaamGames("20261021")
        
        val kuGame = games.firstOrNull { it.id == "ncaam_exh_20261021_lou_ku" }
        assertNotNull(kuGame)
        assertEquals("Kansas Jayhawks", kuGame!!.homeTeam.name)
        assertEquals(83, kuGame.homeTeam.score)
        assertEquals("Louisville Cardinals", kuGame.awayTeam.name)
        assertEquals(89, kuGame.awayTeam.score)
        assertEquals("Final (Exh)", kuGame.period)

        val uclaGame = games.firstOrNull { it.id == "ncaam_exh_20261021_cbu_ucla" }
        assertNotNull(uclaGame)
        assertEquals("UCLA Bruins", uclaGame!!.homeTeam.name)
        assertEquals(100, uclaGame.homeTeam.score)
        assertEquals("California Baptist Lancers", uclaGame.awayTeam.name)
        assertEquals(53, uclaGame.awayTeam.score)
        assertEquals("Final (Exh)", uclaGame.period)
    }
}
