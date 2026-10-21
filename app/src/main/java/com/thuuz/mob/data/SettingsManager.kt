package com.thuuz.mob.data

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // In-memory set of game IDs where the user has explicitly revealed the score during this session
    private val revealedGameIds = mutableSetOf<String>()

    companion object {
        private const val PREFS_NAME = "thuuz_settings_prefs"
        const val KEY_NO_SPOILERS = "key_no_spoilers"
        const val KEY_EXCITEMENT_ALERT_THRESHOLD = "key_excitement_alert_threshold"
        const val KEY_MY_TEAMS_ONLY_ALERTS = "key_my_teams_only_alerts"

        const val DEFAULT_THRESHOLD = 85
        const val MIN_THRESHOLD = 70
        const val MAX_THRESHOLD = 95

        @Volatile
        private var INSTANCE: SettingsManager? = null

        fun getInstance(context: Context): SettingsManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SettingsManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    fun isNoSpoilersEnabled(): Boolean {
        return prefs.getBoolean(KEY_NO_SPOILERS, false)
    }

    fun setNoSpoilersEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_NO_SPOILERS, enabled).apply()
    }

    fun getExcitementAlertThreshold(): Int {
        val stored = prefs.getInt(KEY_EXCITEMENT_ALERT_THRESHOLD, DEFAULT_THRESHOLD)
        return stored.coerceIn(MIN_THRESHOLD, MAX_THRESHOLD)
    }

    fun setExcitementAlertThreshold(threshold: Int) {
        val clamped = threshold.coerceIn(MIN_THRESHOLD, MAX_THRESHOLD)
        prefs.edit().putInt(KEY_EXCITEMENT_ALERT_THRESHOLD, clamped).apply()
    }

    fun isMyTeamsOnlyAlerts(): Boolean {
        return prefs.getBoolean(KEY_MY_TEAMS_ONLY_ALERTS, false)
    }

    fun setMyTeamsOnlyAlerts(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_MY_TEAMS_ONLY_ALERTS, enabled).apply()
    }

    fun isScoreRevealed(gameId: String): Boolean {
        return revealedGameIds.contains(gameId)
    }

    fun revealScore(gameId: String) {
        revealedGameIds.add(gameId)
    }

    fun hideScore(gameId: String) {
        revealedGameIds.remove(gameId)
    }

    fun getRevealedGameIds(): Set<String> {
        return revealedGameIds.toSet()
    }

    fun resetRevealedScores() {
        revealedGameIds.clear()
    }
}
