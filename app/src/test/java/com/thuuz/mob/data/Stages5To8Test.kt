package com.thuuz.mob.data

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import com.thuuz.mob.ui.compose.ExcitementRating
import com.thuuz.mob.ui.compose.Game
import com.thuuz.mob.ui.compose.Team
import com.thuuz.mob.ui.compose.getExcitementRating
import org.junit.Assert.*
import org.junit.Test
import kotlin.math.abs

class Stages5To8Test {

    private val repository = LiveSportsDataRepository()

    // =========================================================================
    // STAGE 5: SettingsManager Tests
    // =========================================================================

    @Test
    fun testSettingsManager_Defaults() {
        val context = DummyContext()
        val settings = SettingsManager(context)

        assertFalse("No spoilers should default to false", settings.isNoSpoilersEnabled())
        assertEquals("Default threshold should be 85", 85, settings.getExcitementAlertThreshold())
        assertFalse("My teams only should default to false", settings.isMyTeamsOnlyAlerts())
        assertTrue("Revealed game IDs should initially be empty", settings.getRevealedGameIds().isEmpty())
    }

    @Test
    fun testSettingsManager_NoSpoilersToggle() {
        val context = DummyContext()
        val settings = SettingsManager(context)

        settings.setNoSpoilersEnabled(true)
        assertTrue(settings.isNoSpoilersEnabled())

        settings.setNoSpoilersEnabled(false)
        assertFalse(settings.isNoSpoilersEnabled())
    }

    @Test
    fun testSettingsManager_ThresholdClamping() {
        val context = DummyContext()
        val settings = SettingsManager(context)

        // Valid values
        settings.setExcitementAlertThreshold(75)
        assertEquals(75, settings.getExcitementAlertThreshold())

        settings.setExcitementAlertThreshold(90)
        assertEquals(90, settings.getExcitementAlertThreshold())

        // Under min threshold (70) -> clamped to 70
        settings.setExcitementAlertThreshold(40)
        assertEquals(70, settings.getExcitementAlertThreshold())

        // Over max threshold (95) -> clamped to 95
        settings.setExcitementAlertThreshold(105)
        assertEquals(95, settings.getExcitementAlertThreshold())
    }

    @Test
    fun testSettingsManager_MyTeamsOnlyToggle() {
        val context = DummyContext()
        val settings = SettingsManager(context)

        settings.setMyTeamsOnlyAlerts(true)
        assertTrue(settings.isMyTeamsOnlyAlerts())

        settings.setMyTeamsOnlyAlerts(false)
        assertFalse(settings.isMyTeamsOnlyAlerts())
    }

    @Test
    fun testSettingsManager_RevealedGameIdsSessionState() {
        val context = DummyContext()
        val settings = SettingsManager(context)

        val gameId = "game_123"
        val gameId2 = "game_456"

        assertFalse(settings.isScoreRevealed(gameId))

        settings.revealScore(gameId)
        assertTrue(settings.isScoreRevealed(gameId))
        assertFalse(settings.isScoreRevealed(gameId2))
        assertEquals(setOf(gameId), settings.getRevealedGameIds())

        settings.revealScore(gameId2)
        assertEquals(setOf(gameId, gameId2), settings.getRevealedGameIds())

        settings.hideScore(gameId)
        assertFalse(settings.isScoreRevealed(gameId))
        assertTrue(settings.isScoreRevealed(gameId2))

        settings.resetRevealedScores()
        assertTrue(settings.getRevealedGameIds().isEmpty())
    }

    // =========================================================================
    // STAGE 5: Notification Text Formatting & No Spoilers Masking
    // =========================================================================

    @Test
    fun testNotificationTextFormatting_NoSpoilersVsStandard() {
        val game = Game(
            id = "401909088",
            sport = "basketball",
            league = "nba",
            homeTeam = Team(id = "1", name = "Detroit Pistons", score = 112, shortName = "Pistons", abbreviation = "DET"),
            awayTeam = Team(id = "2", name = "Boston Celtics", score = 112, shortName = "Celtics", abbreviation = "BOS"),
            gex = 95,
            isLive = true,
            period = "Q4",
            timeRemaining = "0:45",
            broadcasts = listOf("NBC", "Peacock")
        )

        val timeDisplay = if (game.timeRemaining.isNotBlank()) "${game.period} - ${game.timeRemaining}" else game.period
        val channelGuide = if (game.broadcasts.isNotEmpty()) "Tune in on ${game.broadcasts.first()}" else "Watch live now"

        // With No-Spoilers enabled
        val noSpoilersText = "[$timeDisplay] High-drama finish in ${game.homeTeam.shortName.ifBlank { game.homeTeam.name }}! $channelGuide (GEX ${game.gex})"
        assertFalse("No-spoilers notification should not show scores", noSpoilersText.contains("112"))
        assertTrue("No-spoilers notification should mention home team", noSpoilersText.contains("Pistons"))
        assertTrue("No-spoilers notification should include GEX", noSpoilersText.contains("GEX 95"))
        assertTrue("No-spoilers notification should include channel", noSpoilersText.contains("NBC"))

        // Standard notification (spoilers allowed)
        val standardText = "[$timeDisplay] ${game.awayTeam.name} ${game.awayTeam.score}, ${game.homeTeam.name} ${game.homeTeam.score}. $channelGuide!"
        assertTrue("Standard notification must display away score", standardText.contains("112"))
        assertTrue("Standard notification must display both team names", standardText.contains("Boston Celtics") && standardText.contains("Detroit Pistons"))
    }

    // =========================================================================
    // STAGE 6: Interactive Momentum Curve Instantaneous GEX Calculation
    // =========================================================================

    @Test
    fun testInstantaneousGexScoring_ClampingAndCalculations() {
        fun computeInstantGex(homeProb: Float, index: Int, totalPoints: Int): Int {
            val diffFromEven = abs(homeProb - 0.5f)
            val evenness = (1.0f - diffFromEven * 2f).coerceIn(0f, 1f)
            val progress = (index.toFloat() / (totalPoints - 1).coerceAtLeast(1))
            return ((38 + evenness * 42 + progress * 18).toInt()).coerceIn(20, 99)
        }

        // Dead heat (50-50) at final seconds (progress = 1.0) -> Maximum excitement
        val crunchTie = computeInstantGex(homeProb = 0.50f, index = 99, totalPoints = 100)
        assertEquals(98, crunchTie) // 38 + 42 + 18 = 98

        // Blowout (100% win prob) early in the game (progress = 0.0) -> Low excitement
        val earlyBlowout = computeInstantGex(homeProb = 1.0f, index = 0, totalPoints = 100)
        assertEquals(38, earlyBlowout) // 38 + 0 + 0 = 38

        // Mid-game even match (progress = 0.5, evenness = 1.0)
        val midGameEven = computeInstantGex(homeProb = 0.50f, index = 50, totalPoints = 100)
        assertTrue("Mid-game tie should be high excitement (> 80)", midGameEven >= 85)

        // Rating badge classification
        val rating98 = getExcitementRating(crunchTie)
        assertEquals(ExcitementRating.GREAT, rating98)

        val rating38 = getExcitementRating(earlyBlowout)
        assertEquals(ExcitementRating.DUD, rating38)
    }

    // =========================================================================
    // STAGE 6: Full Player Box Scores Parsing
    // =========================================================================

    @Test
    fun testParseGameDetail_FullPlayerBoxScores() {
        val json = """
        {
          "header": {
            "competitions": [
              {
                "competitors": [
                  {
                    "homeAway": "home",
                    "score": "115",
                    "team": { "displayName": "Boston Celtics", "shortDisplayName": "Celtics", "abbreviation": "BOS" }
                  },
                  {
                    "homeAway": "away",
                    "score": "110",
                    "team": { "displayName": "Golden State Warriors", "shortDisplayName": "Warriors", "abbreviation": "GSW" }
                  }
                ],
                "status": { "period": 4, "displayClock": "0:00", "type": { "state": "post", "shortDetail": "Final" } }
              }
            ]
          },
          "boxscore": {
            "players": [
              {
                "team": { "displayName": "Golden State Warriors", "abbreviation": "GSW" },
                "statistics": [
                  {
                    "names": ["MIN", "PTS", "REB", "AST", "3PM"],
                    "athletes": [
                      {
                        "starter": true,
                        "athlete": { "id": "3975", "displayName": "Stephen Curry", "jersey": "30", "position": { "abbreviation": "PG" } },
                        "stats": ["36", "34", "6", "8", "7"]
                      },
                      {
                        "starter": false,
                        "athlete": { "id": "4000", "displayName": "Buddy Hield", "jersey": "7", "position": { "abbreviation": "SG" } },
                        "stats": ["22", "18", "3", "2", "4"]
                      }
                    ]
                  }
                ]
              },
              {
                "team": { "displayName": "Boston Celtics", "abbreviation": "BOS" },
                "statistics": [
                  {
                    "names": ["MIN", "PTS", "REB", "AST", "3PM"],
                    "athletes": [
                      {
                        "starter": true,
                        "athlete": { "id": "4065648", "displayName": "Jayson Tatum", "jersey": "0", "position": { "abbreviation": "SF" } },
                        "stats": ["38", "38", "11", "6", "5"]
                      }
                    ]
                  }
                ]
              }
            ]
          }
        }
        """.trimIndent()

        val detail = repository.parseGameDetail(json, "game_nba_box", "basketball", "nba")

        assertEquals(2, detail.playerBoxScores.size)

        // Away Team Box Score (Warriors)
        val awayBox = detail.playerBoxScores[0]
        assertEquals("Golden State Warriors", awayBox.teamName)
        assertEquals("GSW", awayBox.teamAbbr)
        assertFalse(awayBox.isHome)
        assertEquals(2, awayBox.players.size)

        val curry = awayBox.players[0]
        assertEquals("Stephen Curry", curry.name)
        assertEquals("30", curry.jersey)
        assertEquals("PG", curry.position)
        assertTrue(curry.isStarter)
        assertEquals("34", curry.stats["PTS"])
        assertEquals("6", curry.stats["REB"])
        assertEquals("8", curry.stats["AST"])
        assertTrue("Summary should contain points and rebounds", curry.displayStatsSummary.contains("34 PTS"))

        val hield = awayBox.players[1]
        assertEquals("Buddy Hield", hield.name)
        assertFalse(hield.isStarter)
        assertEquals("18", hield.stats["PTS"])

        // Home Team Box Score (Celtics)
        val homeBox = detail.playerBoxScores[1]
        assertEquals("Boston Celtics", homeBox.teamName)
        assertEquals("BOS", homeBox.teamAbbr)
        assertTrue(homeBox.isHome)
        assertEquals(1, homeBox.players.size)

        val tatum = homeBox.players[0]
        assertEquals("Jayson Tatum", tatum.name)
        assertTrue(tatum.isStarter)
        assertEquals("38", tatum.stats["PTS"])
        assertEquals("11", tatum.stats["REB"])
        assertEquals("6", tatum.stats["AST"])
    }

    // =========================================================================
    // STAGE 7: In-App Video Highlights Parsing
    // =========================================================================

    @Test
    fun testParseGameDetail_VideoHighlights() {
        val json = """
        {
          "header": {
            "competitions": [
              {
                "competitors": [
                  { "homeAway": "home", "score": "3", "team": { "displayName": "Real Madrid", "abbreviation": "RMD" } },
                  { "homeAway": "away", "score": "2", "team": { "displayName": "Barcelona", "abbreviation": "BAR" } }
                ],
                "status": { "period": 2, "displayClock": "90:00", "type": { "state": "post", "shortDetail": "Final" } }
              }
            ]
          },
          "videos": [
            {
              "id": "vid_101",
              "headline": "Bellingham scores stunning 90th-minute winner in El Clasico",
              "description": "Jude Bellingham rifles the ball into the top corner in stoppage time.",
              "duration": 135,
              "thumbnail": "https://a.espncdn.com/thumb_101.jpg",
              "links": {
                "source": {
                  "HD": { "href": "https://video.espn.com/bellingham_hd.mp4" }
                },
                "web": {
                  "href": "https://www.espn.com/video/bellingham"
                }
              }
            },
            {
              "id": "vid_102",
              "headline": "Vinicius Jr. solo run and equalizer",
              "duration": 48,
              "images": [
                { "url": "https://a.espncdn.com/thumb_102.jpg" }
              ],
              "links": {
                "web": { "href": "https://www.espn.com/video/vini" }
              }
            }
          ]
        }
        """.trimIndent()

        val detail = repository.parseGameDetail(json, "game_soccer_vid", "soccer", "esp.1")

        assertEquals(2, detail.videos.size)

        // First video (HD source)
        val v1 = detail.videos[0]
        assertEquals("vid_101", v1.id)
        assertEquals("Bellingham scores stunning 90th-minute winner in El Clasico", v1.headline)
        assertEquals("Jude Bellingham rifles the ball into the top corner in stoppage time.", v1.description)
        assertEquals("2:15", v1.durationDisplay) // 135 seconds = 2 mins 15 secs
        assertEquals("https://a.espncdn.com/thumb_101.jpg", v1.thumbnailUrl)
        assertEquals("https://video.espn.com/bellingham_hd.mp4", v1.videoUrl)

        // Second video (web fallback link, images array for thumbnail)
        val v2 = detail.videos[1]
        assertEquals("vid_102", v2.id)
        assertEquals("Vinicius Jr. solo run and equalizer", v2.headline)
        assertEquals("0:48", v2.durationDisplay) // 48 seconds
        assertEquals("https://a.espncdn.com/thumb_102.jpg", v2.thumbnailUrl)
        assertEquals("https://www.espn.com/video/vini", v2.videoUrl)
    }

    // =========================================================================
    // STAGE 8: Home Screen Widget Data Formatting & Top 3 Games Sorting
    // =========================================================================

    @Test
    fun testWidgetGameFormatting_TopGamesAndNoSpoilers() {
        val games = listOf(
            Game(
                id = "1", sport = "basketball", league = "nba",
                homeTeam = Team(id = "1", name = "Boston Celtics", score = 104, shortName = "Celtics", abbreviation = "BOS"),
                awayTeam = Team(id = "2", name = "NY Knicks", score = 102, shortName = "Knicks", abbreviation = "NYK"),
                gex = 94, isLive = true, period = "Q4", timeRemaining = "1:12"
            ),
            Game(
                id = "2", sport = "football", league = "nfl",
                homeTeam = Team(id = "3", name = "Kansas City Chiefs", score = 21, shortName = "Chiefs", abbreviation = "KC"),
                awayTeam = Team(id = "4", name = "Buffalo Bills", score = 24, shortName = "Bills", abbreviation = "BUF"),
                gex = 98, isLive = true, period = "Q4", timeRemaining = "0:30"
            ),
            Game(
                id = "3", sport = "baseball", league = "mlb",
                homeTeam = Team(id = "5", name = "LA Dodgers", score = 2, shortName = "Dodgers", abbreviation = "LAD"),
                awayTeam = Team(id = "6", name = "SF Giants", score = 1, shortName = "Giants", abbreviation = "SF"),
                gex = 72, isLive = true, period = "9th", timeRemaining = "Bot 9th"
            ),
            Game(
                id = "4", sport = "hockey", league = "nhl",
                homeTeam = Team(id = "7", name = "Edmonton Oilers", score = 1, shortName = "Oilers", abbreviation = "EDM"),
                awayTeam = Team(id = "8", name = "Calgary Flames", score = 5, shortName = "Flames", abbreviation = "CGY"),
                gex = 45, isLive = true, period = "3rd", timeRemaining = "Final"
            )
        )

        // Sort by GEX descending and take top 3
        val topGames = games.sortedByDescending { it.gex }.take(3)
        assertEquals(3, topGames.size)
        assertEquals("2", topGames[0].id) // 98 GEX
        assertEquals("1", topGames[1].id) // 94 GEX
        assertEquals("3", topGames[2].id) // 72 GEX

        // Check score masking under No Spoilers
        val noSpoilers = true
        for (g in topGames) {
            val scoreText = if (noSpoilers) "- : -" else "${g.awayTeam.score} - ${g.homeTeam.score}"
            assertEquals("- : -", scoreText)
        }

        // Check score unmasking with Spoilers allowed
        val spoilersAllowed = false
        val g1Score = if (spoilersAllowed) "- : -" else "${topGames[0].awayTeam.score} - ${topGames[0].homeTeam.score}"
        assertEquals("24 - 21", g1Score)
    }

    // =========================================================================
    // Test Dummy Context & SharedPreferences Helper
    // =========================================================================

    private class DummyContext : ContextWrapper(null) {
        private val inMemoryPrefs = InMemorySharedPreferences()
        override fun getSharedPreferences(name: String?, mode: Int): SharedPreferences {
            return inMemoryPrefs
        }
        override fun getApplicationContext(): Context = this
    }

    private class InMemorySharedPreferences : SharedPreferences {
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
        override fun edit(): SharedPreferences.Editor = EditorImpl()
        override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}
        override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}

        inner class EditorImpl : SharedPreferences.Editor {
            private val temp = mutableMapOf<String, Any?>()
            override fun putString(key: String?, value: String?): SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor {
                if (key != null) temp[key] = values?.toSet(); return this
            }
            override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
                if (key != null) temp[key] = value; return this
            }
            override fun remove(key: String?): SharedPreferences.Editor {
                if (key != null) temp[key] = null; return this
            }
            override fun clear(): SharedPreferences.Editor {
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
