package com.thuuz.mob.data

import com.thuuz.mob.ui.compose.*
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test

class PolishAndStage4Test {

    private val repository = LiveSportsDataRepository()

    @Test
    fun testTeamNameMatching_FuzzyAndAbbreviations() {
        val favManager = FavoritesManager(DummyContext())
        favManager.addFavorite("Boston Celtics")

        // Exact match
        assertTrue(favManager.isFavorite("Boston Celtics"))
        // Substring / Short name match
        assertTrue(favManager.isFavorite("Celtics"))
        // ShortName parameter match
        assertTrue(favManager.isFavorite(teamName = "BOS Team", shortName = "Celtics"))
        // Reverse case: favorited "Celtics", checking full name
        favManager.addFavorite("Warriors")
        assertTrue(favManager.isFavorite("Golden State Warriors"))
        // Abbreviation match
        assertTrue(favManager.matchesFavorite("BOS", "Boston Celtics", "Celtics", "BOS"))
        assertTrue(favManager.matchesFavorite("Boston Celtics", "BOS Team", "Celtics", "BOS"))

        // Non-match
        assertFalse(favManager.isFavorite("Los Angeles Lakers"))
        assertFalse(favManager.isFavorite("Detroit Pistons"))
    }

    @Test
    fun testTightenedSubstringMatching_ShortTokensAndWordBoundaries() {
        val favMgr = FavoritesManager(DummyContext())

        // 1. Short token (<= 3 chars) false positive prevention:
        // "SEA" should NEVER match "Chelsea"
        assertFalse("SEA should not match Chelsea", favMgr.matchesFavorite("SEA", "Chelsea", "Chelsea", "CHE"))
        // "IND" should NEVER match "Cleveland Guardians"
        assertFalse("IND should not match Cleveland Guardians", favMgr.matchesFavorite("IND", "Cleveland Guardians", "Guardians", "CLE"))

        // 2. Short token exact abbreviations or exact word token:
        // "SEA" matches "Seattle Seahawks" with abbreviation "SEA"
        assertTrue(favMgr.matchesFavorite("SEA", "Seattle Seahawks", "Seahawks", "SEA"))
        // "IND" matches "Indiana Pacers" with abbreviation "IND"
        assertTrue(favMgr.matchesFavorite("IND", "Indiana Pacers", "Pacers", "IND"))
        // "LA" matches "LA Clippers" as an exact word token
        assertTrue(favMgr.matchesFavorite("LA", "LA Clippers", "Clippers", "LAC"))

        // 3. Tokens >= 4 characters require word boundary matching:
        // "Real" should NOT match "Villarreal" (embedded substring, not a word boundary)
        assertFalse(favMgr.matchesFavorite("Real", "Villarreal", "Villarreal", "VIL"))
        // "Real" matches "Real Madrid" (at word boundary)
        assertTrue(favMgr.matchesFavorite("Real", "Real Madrid", "Madrid", "RMD"))
        // "Warriors" matches "Golden State Warriors"
        assertTrue(favMgr.matchesFavorite("Warriors", "Golden State Warriors", "Warriors", "GSW"))

        // Apostrophe flexibility: 76'ers vs 76ers
        assertTrue(favMgr.matchesFavorite("76'ers", "Philadelphia 76ers", "76ers", "PHI"))
        assertTrue(favMgr.matchesFavorite("76ers", "76'ers", "76'ers", "PHI"))
    }

    @Test
    fun testSpreadPreservation_InMemoryRecalculation() {
        val upcomingCloseGame = Game(
            id = "game_close",
            homeTeam = Team("1", "Boston Celtics", null, 0, "Celtics", "BOS"),
            awayTeam = Team("2", "New York Knicks", null, 0, "Knicks", "NYK"),
            gex = 86,
            period = "Scheduled",
            timeRemaining = "",
            isLive = false,
            sport = "basketball",
            league = "nba",
            spread = 1.5
        )

        val upcomingBlowoutGame = Game(
            id = "game_blowout",
            homeTeam = Team("3", "Boston Celtics", null, 0, "Celtics", "BOS"),
            awayTeam = Team("4", "Detroit Pistons", null, 0, "Pistons", "DET"),
            gex = 38,
            period = "Scheduled",
            timeRemaining = "",
            isLive = false,
            sport = "basketball",
            league = "nba",
            spread = 16.5
        )

        val recalculated = repository.recalculateExcitement(listOf(upcomingCloseGame, upcomingBlowoutGame))
        assertEquals(2, recalculated.size)

        val closeResult = recalculated.first { it.id == "game_close" }
        assertEquals(1.5, closeResult.spread ?: 0.0, 0.01)
        assertTrue("Spread-based high excitement should be preserved (expected >= 80, got ${closeResult.gex})", closeResult.gex >= 80)

        val blowoutResult = recalculated.first { it.id == "game_blowout" }
        assertEquals(16.5, blowoutResult.spread ?: 0.0, 0.01)
        assertTrue("Spread-based low excitement should be preserved (expected <= 45, got ${blowoutResult.gex})", blowoutResult.gex <= 45)
    }

    @Test
    fun testDynamicNetworkAndGameCastUrls() {
        // Direct gamecast link with gameId
        val detailWithId = GameDetailData(
            gameId = "401909088",
            homeTeam = Team("DET", "Detroit Pistons", null, 121),
            awayTeam = Team("BOS", "Boston Celtics", null, 113),
            status = "Final",
            isLive = false,
            gex = 90,
            teaser = "Great game",
            broadcasts = listOf("NBC", "Peacock"),
            winProbabilities = emptyList(),
            plays = emptyList(),
            leaders = emptyList(),
            teamBoxStats = emptyList(),
            sport = "basketball",
            league = "nba"
        )
        val url = getDirectGameStreamingUrl(detailWithId)
        assertEquals("https://www.espn.com/basketball/game/_/gameId/401909088", url)

        // Specific streaming services when gameId is not present
        val detailPeacock = detailWithId.copy(gameId = "", broadcasts = listOf("Peacock"))
        assertEquals("https://www.peacocktv.com", getDirectGameStreamingUrl(detailPeacock))

        val detailParamount = detailWithId.copy(gameId = "", broadcasts = listOf("CBS", "Paramount+"))
        assertEquals("https://www.paramountplus.com", getDirectGameStreamingUrl(detailParamount))

        val detailPrime = detailWithId.copy(gameId = "", broadcasts = listOf("Prime Video"))
        assertEquals("https://www.amazon.com/primevideo", getDirectGameStreamingUrl(detailPrime))

        // Individual network quick tune-in action URLs
        assertEquals("https://www.peacocktv.com", getBroadcastNetworkUrl("Peacock"))
        assertEquals("https://www.paramountplus.com", getBroadcastNetworkUrl("Paramount+"))
        assertEquals("https://www.amazon.com/primevideo", getBroadcastNetworkUrl("Prime Video"))
        assertEquals("https://www.max.com", getBroadcastNetworkUrl("TNT"))
        assertEquals("https://www.espn.com/watch/", getBroadcastNetworkUrl("ABC"))
    }

    @Test
    fun testRecalculateExcitement_InMemoryUpdate() {
        val games = listOf(
            Game(
                id = "1",
                homeTeam = Team("1", "Detroit Pistons", null, 100, "Pistons", "DET"),
                awayTeam = Team("2", "Boston Celtics", null, 98, "Celtics", "BOS"),
                gex = 50,
                period = "Q4",
                timeRemaining = "1:00",
                isLive = true,
                sport = "basketball",
                league = "nba"
            )
        )

        val updated = repository.recalculateExcitement(games)
        assertEquals(1, updated.size)
        // Late live close NBA game should be evaluated to high excitement (>= 88)
        assertTrue("Expected excitement >= 88, got ${updated[0].gex}", updated[0].gex >= 88)
        assertEquals(games[0].id, updated[0].id)
    }

    @Test
    fun testFootballDrivesAndReverseChronologicalPlaysParsing() {
        val summaryJson = """
        {
          "header": {
            "competitions": [
              {
                "competitors": [
                  { "homeAway": "home", "team": { "displayName": "Philadelphia Eagles", "shortDisplayName": "Eagles", "abbreviation": "PHI" }, "score": "24" },
                  { "homeAway": "away", "team": { "displayName": "Kansas City Chiefs", "shortDisplayName": "Chiefs", "abbreviation": "KC" }, "score": "21" }
                ],
                "status": {
                  "period": 4,
                  "displayClock": "1:50",
                  "type": { "state": "in", "shortDetail": "4th - 1:50" }
                }
              }
            ]
          },
          "drives": {
            "previous": [
              {
                "description": "KC Drive 1",
                "isScore": true,
                "plays": [
                  {
                    "text": "P.Mahomes pass short right to T.Kelce for 12 yards, TOUCHDOWN",
                    "clock": { "displayValue": "10:15" },
                    "period": { "displayValue": "1st" },
                    "homeScore": 0,
                    "awayScore": 7,
                    "type": { "text": "Touchdown" },
                    "scoringPlay": true
                  }
                ]
              },
              {
                "description": "PHI Drive 2",
                "isScore": true,
                "plays": [
                  {
                    "text": "J.Hurts scrambles for 5 yards, TOUCHDOWN",
                    "clock": { "displayValue": "1:50" },
                    "period": { "displayValue": "4th" },
                    "homeScore": 24,
                    "awayScore": 21,
                    "type": { "text": "Touchdown" },
                    "scoringPlay": true
                  }
                ]
              }
            ]
          }
        }
        """.trimIndent()

        val detail = repository.parseGameDetail(summaryJson, "football_123", "football", "nfl")

        assertNotNull(detail)
        assertEquals("Philadelphia Eagles", detail.homeTeam.name)
        assertEquals("Kansas City Chiefs", detail.awayTeam.name)
        assertEquals(2, detail.plays.size)

        // Plays MUST be in reverse chronological order: latest play (4th quarter) at index 0!
        val topPlay = detail.plays[0]
        assertTrue("Top play should be the 4th quarter play, got: ${topPlay.text}", topPlay.text.contains("Hurts"))
        assertTrue(topPlay.isScoring)

        val earlierPlay = detail.plays[1]
        assertTrue("Second play should be 1st quarter play, got: ${earlierPlay.text}", earlierPlay.text.contains("Mahomes"))
    }

    // DummyContext for test environment
    private class DummyContext : android.content.ContextWrapper(null) {
        private val inMemoryPrefs = InMemorySharedPreferences()
        override fun getSharedPreferences(name: String?, mode: Int): android.content.SharedPreferences {
            return inMemoryPrefs
        }
        override fun getApplicationContext(): android.content.Context = this
    }

    private class InMemorySharedPreferences : android.content.SharedPreferences {
        private val map = mutableMapOf<String, Any?>()

        override fun getAll(): MutableMap<String, *> = map
        override fun getString(key: String?, defValue: String?): String? = map[key] as? String ?: defValue
        override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
            val v = map[key]
            return if (v is Set<*>) (v as Set<String>).toMutableSet() else defValues
        }
        override fun getInt(key: String?, defValue: Int): Int = map[key] as? Int ?: defValue
        override fun getLong(key: String?, defValue: Long): Long = map[key] as? Long ?: defValue
        override fun getFloat(key: String?, defValue: Float): Float = map[key] as? Float ?: defValue
        override fun getBoolean(key: String?, defValue: Boolean): Boolean = map[key] as? Boolean ?: defValue
        override fun contains(key: String?): Boolean = map.containsKey(key)
        override fun edit(): android.content.SharedPreferences.Editor = EditorImpl()
        override fun registerOnSharedPreferenceChangeListener(listener: android.content.SharedPreferences.OnSharedPreferenceChangeListener?) {}
        override fun unregisterOnSharedPreferenceChangeListener(listener: android.content.SharedPreferences.OnSharedPreferenceChangeListener?) {}

        inner class EditorImpl : android.content.SharedPreferences.Editor {
            private val temp = mutableMapOf<String, Any?>()
            override fun putString(key: String?, value: String?): android.content.SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putStringSet(key: String?, values: MutableSet<String>?): android.content.SharedPreferences.Editor {
                if (key != null) temp[key] = values?.toSet(); return this
            }
            override fun putInt(key: String?, value: Int): android.content.SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putLong(key: String?, value: Long): android.content.SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putFloat(key: String?, value: Float): android.content.SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putBoolean(key: String?, value: Boolean): android.content.SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun remove(key: String?): android.content.SharedPreferences.Editor {
                if (key != null) temp[key] = null; return this
            }
            override fun clear(): android.content.SharedPreferences.Editor {
                temp.clear(); return this
            }
            override fun commit(): Boolean {
                apply(); return true
            }
            override fun apply() {
                for ((k, v) in temp) {
                    if (v == null) map.remove(k) else map[k] = v
                }
            }
        }
    }
}
