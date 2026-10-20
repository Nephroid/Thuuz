package com.thuuz.mob.data

import com.thuuz.gtv.data.GameData
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class LiveSportsDataRepository {
    private val ESPN_NBA_URL = "https://site.api.espn.com/apis/site/v2/sports/basketball/nba/scoreboard"
    
    // Callback interface to prevent NetworkOnMainThreadException
    interface FetchCallback {
        fun onSuccess(games: List<GameData>)
        fun onError(error: Exception)
    }

    fun fetchLiveGamesAsync(callback: FetchCallback) {
        thread {
            try {
                val jsonResponse = makeHttpRequest(ESPN_NBA_URL)
                val games = parseGames(jsonResponse)
                callback.onSuccess(games)
            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }
    
    // Kept synchronous for backward compatibility or background thread usage
    fun fetchLiveGames(): List<GameData> {
        val jsonResponse = makeHttpRequest(ESPN_NBA_URL)
        return parseGames(jsonResponse)
    }
    
    private fun makeHttpRequest(urlString: String): String {
        var connection: HttpURLConnection? = null
        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            // Set reasonable timeouts
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
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

    internal fun parseGames(jsonString: String): List<GameData> {
        val games = mutableListOf<GameData>()
        if (jsonString.isEmpty()) return games

        try {
            val root = JSONObject(jsonString)
            val events = root.getJSONArray("events")
            
            for (i in 0 until events.length()) {
                val event = events.getJSONObject(i)
                val id = event.getString("id")
                
                var statusStr = "scheduled"
                var period = 0
                var clock = 0
                
                if (event.has("status")) {
                    val statusObj = event.getJSONObject("status")
                    if (statusObj.has("type")) {
                        val typeObj = statusObj.getJSONObject("type")
                        if (typeObj.has("name")) {
                            statusStr = typeObj.getString("name")
                        }
                    }
                    if (statusObj.has("period")) {
                        period = statusObj.getInt("period")
                    }
                    if (statusObj.has("clock")) {
                        clock = statusObj.getInt("clock")
                    }
                }
                
                var homeTeam = ""
                var awayTeam = ""
                var homeScore = 0
                var awayScore = 0
                
                if (event.has("competitions")) {
                    val competitions = event.getJSONArray("competitions")
                    if (competitions.length() > 0) {
                        val competition = competitions.getJSONObject(0)
                        if (competition.has("competitors")) {
                            val competitors = competition.getJSONArray("competitors")
                            for (j in 0 until competitors.length()) {
                                val competitor = competitors.getJSONObject(j)
                                val teamObj = competitor.getJSONObject("team")
                                val teamName = teamObj.getString("name")
                                val score = if (competitor.has("score")) competitor.getString("score").toIntOrNull() ?: 0 else 0
                                
                                val homeAway = competitor.getString("homeAway")
                                if (homeAway == "home") {
                                    homeTeam = teamName
                                    homeScore = score
                                } else {
                                    awayTeam = teamName
                                    awayScore = score
                                }
                            }
                        }
                    }
                }

                // Create the Thuuz JSON format
                val thuuzJson = JSONObject()
                thuuzJson.put("game_id", id)
                thuuzJson.put("home_team", homeTeam)
                thuuzJson.put("away_team", awayTeam)
                thuuzJson.put("home_score", homeScore)
                thuuzJson.put("away_score", awayScore)
                
                // Set excitement prediction or current excitement based on status
                if (statusStr.contains("IN_PROGRESS")) {
                    thuuzJson.put("gex", calculateBaseExcitement(homeScore, awayScore, period, clock))
                } else if (statusStr.contains("SCHEDULED")) {
                    // Pre-game prediction
                    thuuzJson.put("gex_predict", 50) // Default baseline for NBA
                } else if (statusStr.contains("FINAL")) {
                    thuuzJson.put("gex", calculateBaseExcitement(homeScore, awayScore, period, clock))
                }
                
                val gameData = GameData(thuuzJson, null)
                games.add(gameData)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return games
    }
    
    private fun calculateBaseExcitement(homeScore: Int, awayScore: Int, period: Int, clock: Int): Int {
        // A very simple heuristic to give it some data:
        // closer games in later periods are more exciting
        var excitement = 30
        
        val scoreDiff = Math.abs(homeScore - awayScore)
        
        if (scoreDiff <= 5) excitement += 30
        else if (scoreDiff <= 10) excitement += 15
        
        excitement += (period * 10)
        
        if (excitement > 100) return 100
        return excitement
    }
}
