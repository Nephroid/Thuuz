package com.thuuz.mob.data

import com.thuuz.gtv.game.GameExcitementCalculator
import com.thuuz.mob.ui.compose.Game
import com.thuuz.mob.ui.compose.GameDetailData
import com.thuuz.mob.ui.compose.LeaderCategory
import com.thuuz.mob.ui.compose.LeaderPlayer
import com.thuuz.mob.ui.compose.PlayItem
import com.thuuz.mob.ui.compose.PlayerBoxStat
import com.thuuz.mob.ui.compose.SportLeague
import com.thuuz.mob.ui.compose.Team
import com.thuuz.mob.ui.compose.TeamBoxStat
import com.thuuz.mob.ui.compose.TeamPlayerBoxScore
import com.thuuz.mob.ui.compose.VideoHighlight
import com.thuuz.mob.ui.compose.WinProbabilityPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.Locale
import kotlin.concurrent.thread
import kotlin.math.abs

class LiveSportsDataRepository(
    private val favoritesManager: FavoritesManager? = null
) {
    private val calculator = GameExcitementCalculator()

    companion object {
        private const val BASE_ESPN_API = "https://site.api.espn.com/apis/site/v2/sports"
    }

    interface FetchCallback {
        fun onSuccess(games: List<Game>)
        fun onError(error: Exception)
    }

    interface GameDetailCallback {
        fun onSuccess(detail: GameDetailData)
        fun onError(error: Exception)
    }

    // Parallel Concurrent Network Fetching across leagues
    fun fetchGames(
        leagues: List<SportLeague>,
        dateStr: String? = null,
        callback: FetchCallback
    ) {
        val targetLeagues = if (leagues.isEmpty()) {
            listOf(
                SportLeague.NBA,
                SportLeague.NHL,
                SportLeague.NFL,
                SportLeague.MLB,
                SportLeague.NCAAF,
                SportLeague.NCAAM,
                SportLeague.SOCCER
            )
        } else leagues

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val deferredList = targetLeagues.map { league ->
                    async {
                        try {
                            val endpoint = buildScoreboardUrl(league, dateStr)
                            val json = makeHttpRequest(endpoint)
                            if (json.isNotEmpty()) {
                                parseGames(json, league)
                            } else {
                                emptyList()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            emptyList()
                        }
                    }
                }
                val games = deferredList.awaitAll().flatten()
                callback.onSuccess(games)
            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }

    fun fetchLiveGamesSync(): List<Game> {
        val targetLeagues = listOf(
            SportLeague.NBA,
            SportLeague.NHL,
            SportLeague.NFL,
            SportLeague.MLB,
            SportLeague.NCAAF,
            SportLeague.NCAAM,
            SportLeague.SOCCER
        )
        val games = mutableListOf<Game>()
        for (league in targetLeagues) {
            try {
                val endpoint = buildScoreboardUrl(league, null)
                val json = makeHttpRequest(endpoint)
                if (json.isNotEmpty()) {
                    games.addAll(parseGames(json, league))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return games
    }

    fun fetchLiveGamesAsync(callback: FetchCallback) {
        fetchGames(emptyList(), null, callback)
    }

    // In-Memory GEX Recalculation without network reload
    fun recalculateExcitement(games: List<Game>): List<Game> {
        return games.map { game ->
            val updatedGex = calculateScore(
                homeScore = game.homeTeam.score,
                awayScore = game.awayTeam.score,
                isLive = game.isLive,
                isFinal = game.period.contains("Final", ignoreCase = true),
                sport = game.sport,
                periodNumber = extractPeriodNumber(game.period),
                clockStr = game.timeRemaining,
                spread = game.spread,
                homeName = game.homeTeam.name,
                awayName = game.awayTeam.name,
                homeShortName = game.homeTeam.shortName,
                awayShortName = game.awayTeam.shortName,
                homeAbbr = game.homeTeam.abbreviation,
                awayAbbr = game.awayTeam.abbreviation
            )
            game.copy(gex = updatedGex)
        }
    }

    fun extractPeriodNumber(periodStr: String): Int {
        val clean = periodStr.lowercase()
        return when {
            clean.contains("ot") -> 5
            clean.contains("4") -> 4
            clean.contains("3") -> 3
            clean.contains("2") -> 2
            clean.contains("1") -> 1
            clean.contains("final") -> 4
            else -> 0
        }
    }

    fun fetchGameDetail(
        sport: String,
        league: String,
        gameId: String,
        callback: GameDetailCallback
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = "$BASE_ESPN_API/$sport/$league/summary?event=$gameId"
                val json = makeHttpRequest(url)
                if (json.isNotEmpty()) {
                    val detail = parseGameDetail(json, gameId, sport, league)
                    callback.onSuccess(detail)
                } else {
                    callback.onError(Exception("Empty response for game detail"))
                }
            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }

    private fun buildScoreboardUrl(league: SportLeague, dateStr: String?): String {
        val base = "$BASE_ESPN_API/${league.sportPath}/${league.leaguePath}/scoreboard"
        return if (!dateStr.isNullOrBlank()) {
            "$base?dates=$dateStr"
        } else {
            base
        }
    }

    private fun makeHttpRequest(urlString: String): String {
        var connection: HttpURLConnection? = null
        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 8000
            connection.readTimeout = 8000
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                return response.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
        }
        return ""
    }

    fun parseGames(jsonString: String, league: SportLeague = SportLeague.NBA): List<Game> {
        val list = mutableListOf<Game>()
        if (jsonString.isEmpty()) return list

        try {
            val root = JSONObject(jsonString)
            if (!root.has("events")) return list
            val events = root.getJSONArray("events")

            for (i in 0 until events.length()) {
                val event = events.getJSONObject(i)
                val id = event.optString("id", i.toString())

                var periodStr = "Scheduled"
                var clockStr = ""
                var isLive = false
                var isFinal = false
                var periodNumber = 0

                if (event.has("status")) {
                    val statusObj = event.getJSONObject("status")
                    periodStr = statusObj.optJSONObject("type")?.optString("shortDetail") ?: "Scheduled"
                    val state = statusObj.optJSONObject("type")?.optString("state") ?: ""
                    isLive = state == "in"
                    isFinal = state == "post"
                    clockStr = statusObj.optString("displayClock", "")
                    periodNumber = statusObj.optInt("period", 0)
                }

                var homeName = "Home"
                var awayName = "Away"
                var homeShort = ""
                var awayShort = ""
                var homeAbbr = ""
                var awayAbbr = ""
                var homeLogo: String? = null
                var awayLogo: String? = null
                var homeScore = 0
                var awayScore = 0
                val broadcastsList = mutableListOf<String>()
                var teaser = ""
                var spread: Double? = null

                val competitions = event.optJSONArray("competitions")
                if (competitions != null && competitions.length() > 0) {
                    val competition = competitions.getJSONObject(0)

                    // Parse competitors with rich naming resolution (shortDisplayName, displayName, abbreviation)
                    val competitors = competition.optJSONArray("competitors")
                    if (competitors != null) {
                        for (j in 0 until competitors.length()) {
                            val comp = competitors.getJSONObject(j)
                            val teamObj = comp.optJSONObject("team")
                            val displayName = teamObj?.optString("displayName", teamObj.optString("name", "Team")) ?: "Team"
                            val shortDisplayName = teamObj?.optString("shortDisplayName", teamObj.optString("name", "")) ?: ""
                            val abbreviation = teamObj?.optString("abbreviation", "") ?: ""
                            val logo = teamObj?.optString("logo")
                            val score = comp.optString("score", "0").toIntOrNull() ?: 0

                            if (comp.optString("homeAway") == "home") {
                                homeName = displayName
                                homeShort = shortDisplayName
                                homeAbbr = abbreviation
                                homeLogo = logo
                                homeScore = score
                            } else {
                                awayName = displayName
                                awayShort = shortDisplayName
                                awayAbbr = abbreviation
                                awayLogo = logo
                                awayScore = score
                            }
                        }
                    }

                    // Parse TV broadcasts
                    extractBroadcasts(competition, broadcastsList)

                    // Parse odds / spread if available
                    val odds = competition.optJSONArray("odds")
                    if (odds != null && odds.length() > 0) {
                        val oddObj = odds.getJSONObject(0)
                        if (oddObj.has("spread")) {
                            spread = oddObj.optDouble("spread")
                        }
                    }

                    // Parse Teaser / Headlines
                    val headlines = competition.optJSONArray("headlines")
                    if (headlines != null && headlines.length() > 0) {
                        val headline = headlines.getJSONObject(0)
                        teaser = headline.optString("shortLinkText", headline.optString("description", ""))
                    }
                }

                // If no headlines teaser, generate a Thuuz-style match teaser
                if (teaser.isBlank()) {
                    teaser = when {
                        isLive && abs(homeScore - awayScore) <= 3 -> "Nail-biter down the wire! Catch the finish live."
                        isLive -> "Live action underway."
                        isFinal -> "Game concluded."
                        else -> "Upcoming matchup."
                    }
                }

                val gex = calculateScore(
                    homeScore = homeScore,
                    awayScore = awayScore,
                    isLive = isLive,
                    isFinal = isFinal,
                    sport = league.sportPath,
                    periodNumber = periodNumber,
                    clockStr = clockStr,
                    spread = spread,
                    homeName = homeName,
                    awayName = awayName,
                    homeShortName = homeShort,
                    awayShortName = awayShort,
                    homeAbbr = homeAbbr,
                    awayAbbr = awayAbbr
                )

                list.add(
                    Game(
                        id = id,
                        homeTeam = Team("home", homeName, homeLogo, homeScore, homeShort, homeAbbr),
                        awayTeam = Team("away", awayName, awayLogo, awayScore, awayShort, awayAbbr),
                        gex = gex,
                        period = periodStr,
                        timeRemaining = clockStr,
                        isLive = isLive,
                        broadcasts = broadcastsList,
                        teaser = teaser,
                        sport = league.sportPath,
                        league = league.leaguePath,
                        spread = spread
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    private fun extractBroadcasts(competition: JSONObject, broadcastsList: MutableList<String>) {
        val geoBroadcasts = competition.optJSONArray("geoBroadcasts")
        if (geoBroadcasts != null && geoBroadcasts.length() > 0) {
            for (k in 0 until geoBroadcasts.length()) {
                val gb = geoBroadcasts.optJSONObject(k)
                val media = gb?.optJSONObject("media")
                val shortName = media?.optString("shortName") ?: ""
                if (shortName.isNotBlank() && !broadcastsList.contains(shortName)) {
                    broadcastsList.add(shortName)
                }
            }
        }

        if (broadcastsList.isEmpty()) {
            val broadcasts = competition.optJSONArray("broadcasts")
            if (broadcasts != null) {
                for (k in 0 until broadcasts.length()) {
                    val b = broadcasts.optJSONObject(k)
                    val names = b?.optJSONArray("names")
                    if (names != null) {
                        for (n in 0 until names.length()) {
                            val name = names.optString(n)
                            if (name.isNotBlank() && !broadcastsList.contains(name)) {
                                broadcastsList.add(name)
                            }
                        }
                    }
                }
            }
        }
    }

    fun parseGameDetail(jsonString: String, gameId: String, sport: String, league: String): GameDetailData {
        val root = JSONObject(jsonString)

        // Parse header info
        var homeName = "Home"
        var awayName = "Away"
        var homeShort = ""
        var awayShort = ""
        var homeAbbr = ""
        var awayAbbr = ""
        var homeLogo: String? = null
        var awayLogo: String? = null
        var homeScore = 0
        var awayScore = 0
        var statusStr = "Status"
        var isLive = false
        var isFinal = false
        var periodNumber = 0
        var clockStr = ""
        var spread: Double? = null

        val header = root.optJSONObject("header")
        if (header != null) {
            val comps = header.optJSONArray("competitions")
            if (comps != null && comps.length() > 0) {
                val comp = comps.getJSONObject(0)
                val competitors = comp.optJSONArray("competitors")
                if (competitors != null) {
                    for (i in 0 until competitors.length()) {
                        val c = competitors.getJSONObject(i)
                        val team = c.optJSONObject("team")
                        val displayName = team?.optString("displayName", team.optString("name", "Team")) ?: "Team"
                        val shortDisplayName = team?.optString("shortDisplayName", team.optString("name", "")) ?: ""
                        val abbreviation = team?.optString("abbreviation", "") ?: ""
                        val logo = team?.optString("logo")
                        val score = c.optString("score", "0").toIntOrNull() ?: 0
                        if (c.optString("homeAway") == "home") {
                            homeName = displayName
                            homeShort = shortDisplayName
                            homeAbbr = abbreviation
                            homeLogo = logo
                            homeScore = score
                        } else {
                            awayName = displayName
                            awayShort = shortDisplayName
                            awayAbbr = abbreviation
                            awayLogo = logo
                            awayScore = score
                        }
                    }
                }
                val statusObj = comp.optJSONObject("status")
                if (statusObj != null) {
                    statusStr = statusObj.optJSONObject("type")?.optString("shortDetail") ?: ""
                    val state = statusObj.optJSONObject("type")?.optString("state") ?: ""
                    isLive = state == "in"
                    isFinal = state == "post"
                    periodNumber = statusObj.optInt("period", 0)
                    clockStr = statusObj.optString("displayClock", "")
                }
            }
        }

        // Win Probability curve
        val winProbPoints = mutableListOf<WinProbabilityPoint>()
        val winProbArray = root.optJSONArray("winprobability")
        if (winProbArray != null && winProbArray.length() > 0) {
            for (i in 0 until winProbArray.length()) {
                val wp = winProbArray.getJSONObject(i)
                val homePct = wp.optDouble("homeWinPercentage", 0.5).toFloat()
                val playId = wp.optString("playId", "")
                val tiePct = wp.optDouble("tiePercentage", 0.0).toFloat()
                winProbPoints.add(WinProbabilityPoint(playId, homePct, tiePct))
            }
        } else {
            // Generate synthetic curve from score progress if empty
            winProbPoints.add(WinProbabilityPoint("0", 0.50f, 0f))
            winProbPoints.add(WinProbabilityPoint("1", 0.52f, 0f))
            winProbPoints.add(WinProbabilityPoint("2", 0.48f, 0f))
            val finalHomeProb = if (homeScore > awayScore) 0.95f else if (awayScore > homeScore) 0.05f else 0.50f
            winProbPoints.add(WinProbabilityPoint("3", finalHomeProb, 0f))
        }

        // Broadcast channels & Odds
        val broadcasts = mutableListOf<String>()
        val comps = header?.optJSONArray("competitions")
        if (comps != null && comps.length() > 0) {
            extractBroadcasts(comps.getJSONObject(0), broadcasts)
            val odds = comps.getJSONObject(0).optJSONArray("odds")
            if (odds != null && odds.length() > 0) {
                val oddObj = odds.getJSONObject(0)
                if (oddObj.has("spread")) {
                    spread = oddObj.optDouble("spread")
                }
            }
        }

        // Scoring Plays & Key Moments (Robust parsing for All Sports + NFL/NCAAF drives)
        val rawPlaysList = mutableListOf<PlayItem>()
        val seenPlayKeys = mutableSetOf<String>()

        fun addPlay(text: String, timeDetail: String, hScore: Int, aScore: Int, type: String, isScoring: Boolean) {
            val cleanText = text.trim()
            if (cleanText.isBlank()) return
            val key = "${timeDetail.trim()}|$cleanText"
            if (seenPlayKeys.contains(key)) return
            seenPlayKeys.add(key)
            rawPlaysList.add(
                PlayItem(
                    text = cleanText,
                    timeDetail = timeDetail.ifBlank { "Play" },
                    homeScore = hScore,
                    awayScore = aScore,
                    type = type.ifBlank { if (isScoring) "Score" else "Play" },
                    isScoring = isScoring
                )
            )
        }

        // 1. Direct "plays" array (NBA, MLB, NHL)
        val playsArray = root.optJSONArray("plays")
        if (playsArray != null) {
            for (p in 0 until playsArray.length()) {
                val play = playsArray.getJSONObject(p)
                val text = play.optString("text", play.optString("description", ""))
                val clockObj = play.optJSONObject("clock")
                val displayValue = clockObj?.optString("displayValue", "") ?: play.optString("clock", "")
                val periodObj = play.optJSONObject("period")
                val periodDisplay = periodObj?.optString("displayValue", "") ?: "P${play.optInt("period", 0)}"
                val timeDetail = if (displayValue.isNotBlank()) "$periodDisplay $displayValue".trim() else periodDisplay
                val hScore = play.optInt("homeScore", homeScore)
                val aScore = play.optInt("awayScore", awayScore)
                val typeObj = play.optJSONObject("type")
                val typeText = typeObj?.optString("text", "Play") ?: "Play"
                val scoringPlay = play.optBoolean("scoringPlay", false)
                addPlay(text, timeDetail, hScore, aScore, typeText, scoringPlay)
            }
        }

        // 2. Direct "scoringPlays" array (often present in football / hockey / soccer)
        val scoringPlaysArray = root.optJSONArray("scoringPlays")
        if (scoringPlaysArray != null) {
            for (p in 0 until scoringPlaysArray.length()) {
                val play = scoringPlaysArray.getJSONObject(p)
                val text = play.optString("text", play.optString("description", ""))
                val clockObj = play.optJSONObject("clock")
                val displayValue = clockObj?.optString("displayValue", "") ?: play.optString("clock", "")
                val periodObj = play.optJSONObject("period")
                val periodDisplay = periodObj?.optString("displayValue", "") ?: "P${play.optInt("period", 0)}"
                val timeDetail = if (displayValue.isNotBlank()) "$periodDisplay $displayValue".trim() else periodDisplay
                val hScore = play.optInt("homeScore", homeScore)
                val aScore = play.optInt("awayScore", awayScore)
                val typeObj = play.optJSONObject("type")
                val typeText = typeObj?.optString("text", "Score") ?: "Score"
                addPlay(text, timeDetail, hScore, aScore, typeText, isScoring = true)
            }
        }

        // 3. Drives tree for NFL / NCAAF (drives.previous and drives.current)
        val drivesObj = root.optJSONObject("drives")
        if (drivesObj != null) {
            val driveArrays = listOfNotNull(
                drivesObj.optJSONArray("previous"),
                drivesObj.optJSONArray("current")
            )
            for (drives in driveArrays) {
                for (d in 0 until drives.length()) {
                    val drive = drives.getJSONObject(d)
                    val isDriveScore = drive.optBoolean("isScore", false)
                    val driveDesc = drive.optString("description", drive.optString("displayResult", ""))
                    val dPlays = drive.optJSONArray("plays")
                    if (dPlays != null && dPlays.length() > 0) {
                        for (p in 0 until dPlays.length()) {
                            val play = dPlays.getJSONObject(p)
                            val text = play.optString("text", play.optString("description", ""))
                            val clockObj = play.optJSONObject("clock")
                            val displayValue = clockObj?.optString("displayValue", "") ?: ""
                            val periodObj = play.optJSONObject("period")
                            val periodDisplay = periodObj?.optString("displayValue", "") ?: ""
                            val timeDetail = if (displayValue.isNotBlank()) "$periodDisplay $displayValue".trim() else periodDisplay
                            val hScore = play.optInt("homeScore", homeScore)
                            val aScore = play.optInt("awayScore", awayScore)
                            val typeObj = play.optJSONObject("type")
                            val typeText = typeObj?.optString("text", "Play") ?: "Play"
                            val isPlayScoring = play.optBoolean("scoringPlay", false) ||
                                (isDriveScore && p == dPlays.length() - 1)
                            addPlay(text, timeDetail, hScore, aScore, typeText, isPlayScoring)
                        }
                    } else if (driveDesc.isNotBlank()) {
                        val timeDetail = drive.optString("possessionTime", "Drive")
                        addPlay(driveDesc, timeDetail, homeScore, awayScore, "Drive", isDriveScore)
                    }
                }
            }
        }

        // Reverse Chronological Order: most recent plays at top of list
        val playsList = rawPlaysList.reversed()

        // Leaders
        val leaderCategories = mutableListOf<LeaderCategory>()
        val leadersArray = root.optJSONArray("leaders")
        if (leadersArray != null) {
            for (l in 0 until leadersArray.length()) {
                val catObj = leadersArray.getJSONObject(l)
                val categoryName = catObj.optString("displayName", catObj.optString("name", "Leader"))
                val leadersList = catObj.optJSONArray("leaders")
                if (leadersList != null && leadersList.length() > 0) {
                    val leaderItem = leadersList.getJSONObject(0)
                    val athlete = leaderItem.optJSONObject("athlete")
                    val athleteName = athlete?.optString("displayName", "Player") ?: "Player"
                    val jersey = athlete?.optString("jersey", "") ?: ""
                    val pos = athlete?.optJSONObject("position")?.optString("abbreviation", "") ?: ""
                    val headshot = athlete?.optJSONObject("headshot")?.optString("href")
                    val statValue = leaderItem.optString("displayValue", "")

                    leaderCategories.add(
                        LeaderCategory(
                            category = categoryName,
                            leader = LeaderPlayer(
                                name = athleteName,
                                jersey = jersey,
                                position = pos,
                                statValue = statValue,
                                headshotUrl = headshot
                            )
                        )
                    )
                }
            }
        }

        // Team Box Stats
        val teamStats = mutableListOf<TeamBoxStat>()
        val boxscore = root.optJSONObject("boxscore")
        val boxTeams = boxscore?.optJSONArray("teams")
        if (boxTeams != null && boxTeams.length() >= 2) {
            val awayStats = boxTeams.getJSONObject(0)
            val homeStats = boxTeams.getJSONObject(1)

            val awayStatList = awayStats.optJSONArray("statistics")
            val homeStatList = homeStats.optJSONArray("statistics")

            if (awayStatList != null && homeStatList != null) {
                val count = minOf(awayStatList.length(), homeStatList.length())
                for (s in 0 until count) {
                    val a = awayStatList.getJSONObject(s)
                    val h = homeStatList.getJSONObject(s)
                    val label = a.optString("displayName", a.optString("name", ""))
                    teamStats.add(
                        TeamBoxStat(
                            label = label,
                            awayValue = a.optString("displayValue", ""),
                            homeValue = h.optString("displayValue", "")
                        )
                    )
                }
            }
        }

        // Full Player Box Scores (NBA, NHL, NFL, MLB, NCAAF, NCAAM, Soccer)
        val playerBoxScores = mutableListOf<TeamPlayerBoxScore>()
        val boxPlayers = boxscore?.optJSONArray("players")
        if (boxPlayers != null && boxPlayers.length() > 0) {
            for (t in 0 until boxPlayers.length()) {
                val teamBox = boxPlayers.getJSONObject(t)
                val teamObj = teamBox.optJSONObject("team")
                val tName = teamObj?.optString("displayName", teamObj.optString("name", "Team")) ?: "Team"
                val tAbbr = teamObj?.optString("abbreviation", "") ?: ""
                val tLogo = teamObj?.optString("logo")
                val isHomeTeam = (tAbbr.isNotBlank() && tAbbr.equals(homeAbbr, ignoreCase = true)) ||
                                 tName.equals(homeName, ignoreCase = true) || (t == 1)

                val statsArr = teamBox.optJSONArray("statistics")
                val allPlayers = mutableListOf<PlayerBoxStat>()
                val statHeaders = mutableListOf<String>()

                if (statsArr != null) {
                    for (s in 0 until statsArr.length()) {
                        val statGroup = statsArr.getJSONObject(s)
                        val labelsArr = statGroup.optJSONArray("labels") ?: statGroup.optJSONArray("names")
                        val currentLabels = mutableListOf<String>()
                        if (labelsArr != null) {
                            for (l in 0 until labelsArr.length()) {
                                currentLabels.add(labelsArr.getString(l))
                            }
                        }
                        if (statHeaders.isEmpty() && currentLabels.isNotEmpty()) {
                            statHeaders.addAll(currentLabels)
                        }

                        val athletesArr = statGroup.optJSONArray("athletes")
                        if (athletesArr != null) {
                            for (a in 0 until athletesArr.length()) {
                                val athObj = athletesArr.getJSONObject(a)
                                val athleteData = athObj.optJSONObject("athlete")
                                val pName = athleteData?.optString("displayName", athleteData.optString("shortName", "Player")) ?: "Player"
                                val pId = athleteData?.optString("id", "$a") ?: "$a"
                                val pJersey = athleteData?.optString("jersey", "") ?: ""
                                val pPos = athleteData?.optJSONObject("position")?.optString("abbreviation", "") ?: ""
                                val pHeadshot = athleteData?.optJSONObject("headshot")?.optString("href")
                                val isStarter = athObj.optBoolean("starter", false)

                                val pStats = athObj.optJSONArray("stats")
                                val pStatsMap = mutableMapOf<String, String>()
                                if (pStats != null && currentLabels.isNotEmpty()) {
                                    for (st in 0 until minOf(pStats.length(), currentLabels.size)) {
                                        pStatsMap[currentLabels[st]] = pStats.getString(st)
                                    }
                                }

                                // Build summary
                                val summaryParts = mutableListOf<String>()
                                pStatsMap["PTS"]?.let { summaryParts.add("$it PTS") }
                                pStatsMap["REB"]?.let { summaryParts.add("$it REB") }
                                pStatsMap["AST"]?.let { summaryParts.add("$it AST") }
                                pStatsMap["G"]?.let { summaryParts.add("$it G") }
                                pStatsMap["A"]?.let { summaryParts.add("$it A") }
                                pStatsMap["H"]?.let { summaryParts.add("$it H") }
                                pStatsMap["R"]?.let { summaryParts.add("$it R") }
                                pStatsMap["HR"]?.let { summaryParts.add("$it HR") }
                                pStatsMap["YDS"]?.let { summaryParts.add("$it YDS") }
                                pStatsMap["TD"]?.let { summaryParts.add("$it TD") }
                                val summary = if (summaryParts.isNotEmpty()) summaryParts.joinToString(" · ")
                                              else pStatsMap.entries.take(3).joinToString(" · ") { "${it.value} ${it.key}" }

                                allPlayers.add(
                                    PlayerBoxStat(
                                        id = pId,
                                        name = pName,
                                        jersey = pJersey,
                                        position = pPos,
                                        isStarter = isStarter,
                                        headshotUrl = pHeadshot,
                                        stats = pStatsMap,
                                        displayStatsSummary = summary
                                    )
                                )
                            }
                        }
                    }
                }

                playerBoxScores.add(
                    TeamPlayerBoxScore(
                        teamName = tName,
                        teamAbbr = tAbbr.ifBlank { tName.take(3).uppercase() },
                        teamLogoUrl = tLogo,
                        isHome = isHomeTeam,
                        statHeaders = if (statHeaders.isNotEmpty()) statHeaders else listOf("MIN", "PTS", "REB", "AST"),
                        players = allPlayers
                    )
                )
            }
        }

        // In-App Video Highlights
        val videoHighlights = mutableListOf<VideoHighlight>()
        val videosArray = root.optJSONArray("videos")
        if (videosArray != null && videosArray.length() > 0) {
            for (v in 0 until videosArray.length()) {
                val vid = videosArray.getJSONObject(v)
                val id = vid.optString("id", "$v")
                val headline = vid.optString("headline", vid.optString("title", "Game Highlight"))
                val description = vid.optString("description", "")
                val durationSec = vid.optInt("duration", 0)
                val durationMins = durationSec / 60
                val durationRemSec = durationSec % 60
                val durationDisplay = if (durationSec > 0) String.format(Locale.US, "%d:%02d", durationMins, durationRemSec) else ""

                var thumbUrl: String? = null
                val thumbObj = vid.optJSONObject("thumbnail")
                if (thumbObj != null) {
                    thumbUrl = thumbObj.optString("href")
                } else if (vid.has("thumbnail")) {
                    thumbUrl = vid.optString("thumbnail")
                }
                if (thumbUrl.isNullOrBlank()) {
                    val imagesArr = vid.optJSONArray("images")
                    if (imagesArr != null && imagesArr.length() > 0) {
                        thumbUrl = imagesArr.getJSONObject(0).optString("url", imagesArr.getJSONObject(0).optString("href", ""))
                    }
                }

                var videoUrl = ""
                val linksObj = vid.optJSONObject("links")
                if (linksObj != null) {
                    val sourceObj = linksObj.optJSONObject("source")
                    val hdObj = sourceObj?.optJSONObject("HD")
                    val hdHref = hdObj?.optString("href", "")
                    if (!hdHref.isNullOrBlank()) {
                        videoUrl = hdHref
                    } else {
                        val mobileObj = linksObj.optJSONObject("mobile")
                        val mobileSource = mobileObj?.optJSONObject("source")?.optString("href", "")
                        val mobileHref = mobileObj?.optString("href", "")
                        val webHref = linksObj.optJSONObject("web")?.optString("href", "")
                        videoUrl = when {
                            !mobileSource.isNullOrBlank() -> mobileSource
                            !mobileHref.isNullOrBlank() -> mobileHref
                            !webHref.isNullOrBlank() -> webHref
                            else -> ""
                        }
                    }
                }
                if (videoUrl.isBlank()) {
                    videoUrl = vid.optString("url", "https://www.espn.com/video/clip/_/id/$id")
                }

                videoHighlights.add(
                    VideoHighlight(
                        id = id,
                        headline = headline,
                        description = description,
                        durationSeconds = durationSec,
                        durationDisplay = durationDisplay,
                        thumbnailUrl = if (thumbUrl.isNullOrBlank()) null else thumbUrl,
                        videoUrl = videoUrl
                    )
                )
            }
        }

        // Teaser / Game summary article
        var gameTeaser = ""
        val article = root.optJSONObject("article")
        if (article != null) {
            gameTeaser = article.optString("headline", article.optString("description", "") ?: "") ?: ""
        }
        if (gameTeaser.isBlank()) {
            gameTeaser = if (isLive) "Intense battle unfolding. Monitor momentum swings in real-time."
            else if (isFinal) "High energy finish! Review scoring plays and key momentum shifts above."
            else "Matchup scheduled. Check back near game time for excitement rating forecasts."
        }

        val gex = calculateScore(
            homeScore = homeScore,
            awayScore = awayScore,
            isLive = isLive,
            isFinal = isFinal,
            sport = sport,
            periodNumber = periodNumber,
            clockStr = clockStr,
            spread = spread,
            homeName = homeName,
            awayName = awayName,
            homeShortName = homeShort,
            awayShortName = awayShort,
            homeAbbr = homeAbbr,
            awayAbbr = awayAbbr
        )

        return GameDetailData(
            gameId = gameId,
            homeTeam = Team("home", homeName, homeLogo, homeScore, homeShort, homeAbbr),
            awayTeam = Team("away", awayName, awayLogo, awayScore, awayShort, awayAbbr),
            status = statusStr,
            isLive = isLive,
            gex = gex,
            teaser = gameTeaser,
            broadcasts = broadcasts,
            winProbabilities = winProbPoints,
            plays = playsList,
            leaders = leaderCategories,
            teamBoxStats = teamStats,
            playerBoxScores = playerBoxScores,
            videos = videoHighlights,
            sport = sport,
            league = league
        )
    }

    fun calculateScore(
        homeScore: Int,
        awayScore: Int,
        isLive: Boolean,
        isFinal: Boolean,
        sport: String = "basketball",
        periodNumber: Int = 0,
        clockStr: String = "",
        spread: Double? = null,
        homeName: String = "Home",
        awayName: String = "Away",
        homeShortName: String = "",
        awayShortName: String = "",
        homeAbbr: String = "",
        awayAbbr: String = ""
    ): Int {
        val isUpcoming = !isLive && !isFinal
        val isHockeyOrSoccerOrBaseball = sport == "hockey" || sport == "soccer" || sport == "baseball"

        var predictedGex = 55
        var calculatedGex = 50

        if (isUpcoming) {
            if (spread != null) {
                val absSpread = abs(spread)
                predictedGex = when {
                    absSpread <= 2.5 -> 86
                    absSpread <= 5.5 -> 74
                    absSpread <= 9.5 -> 60
                    else -> 38
                }
            } else {
                // Matchup variance between 48 and 85
                val seed = (homeName.trim() + awayName.trim() + sport).lowercase().replace(" ", "")
                val hash = abs(seed.hashCode())
                predictedGex = 48 + (hash % 38)
            }
        } else if (isFinal) {
            val diff = abs(homeScore - awayScore)
            calculatedGex = if (isHockeyOrSoccerOrBaseball) {
                when {
                    diff == 0 -> 92
                    diff == 1 -> 88
                    diff == 2 -> 68
                    diff == 3 -> 52
                    else -> 32
                }
            } else {
                when {
                    diff <= 1 -> 94
                    diff <= 3 -> 88
                    diff <= 6 -> 78
                    diff <= 10 -> 65
                    diff <= 15 -> 52
                    else -> 35
                }
            }
        } else {
            // isLive
            val diff = abs(homeScore - awayScore)
            val isLateGame = if (isHockeyOrSoccerOrBaseball) periodNumber >= 3 else periodNumber >= 4

            calculatedGex = if (isLateGame) {
                if (isHockeyOrSoccerOrBaseball) {
                    when {
                        diff <= 1 -> 95
                        diff == 2 -> 80
                        diff == 3 -> 58
                        else -> 28
                    }
                } else {
                    when {
                        diff <= 3 -> 94
                        diff <= 6 -> 84
                        diff <= 10 -> 70
                        diff <= 15 -> 52
                        else -> 28
                    }
                }
            } else {
                // Early or mid-game
                if (isHockeyOrSoccerOrBaseball) {
                    when {
                        diff <= 1 -> 76
                        diff == 2 -> 64
                        diff <= 3 -> 50
                        else -> 35
                    }
                } else {
                    when {
                        diff <= 6 -> 76
                        diff <= 12 -> 65
                        diff <= 18 -> 50
                        else -> 32
                    }
                }
            }
        }

        val isFanOfHome = favoritesManager?.isFavorite(homeName, homeShortName, homeAbbr) == true
        val isFanOfAway = favoritesManager?.isFavorite(awayName, awayShortName, awayAbbr) == true

        return calculator.calculateGameExcitement(
            isUpcoming = isUpcoming,
            hasGexPredict = true,
            gexPredict = predictedGex,
            hasGex = !isUpcoming,
            gex = calculatedGex.coerceIn(10, 99),
            isFanOfHomeTeam = isFanOfHome,
            isFanOfAwayTeam = isFanOfAway,
            homeFanAdjust = 15,
            awayFanAdjust = 15
        )
    }
}
