package com.thuuz.mob.data

import android.content.Context
import android.content.SharedPreferences

class FavoritesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "thuuz_favorites_prefs"
        private const val KEY_FAVORITE_TEAMS = "key_favorite_teams"

        @Volatile
        private var INSTANCE: FavoritesManager? = null

        fun getInstance(context: Context): FavoritesManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FavoritesManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    fun getFavoriteTeams(): Set<String> {
        return prefs.getStringSet(KEY_FAVORITE_TEAMS, emptySet())?.toSet() ?: emptySet()
    }

    fun isFavorite(teamName: String, shortName: String? = null, abbreviation: String? = null): Boolean {
        if (teamName.isBlank() && shortName.isNullOrBlank() && abbreviation.isNullOrBlank()) return false
        val favorites = getFavoriteTeams()
        if (favorites.isEmpty()) return false
        return favorites.any { fav ->
            matchesFavorite(fav, teamName, shortName, abbreviation)
        }
    }

    fun matchesFavorite(favorite: String, teamName: String, shortName: String? = null, abbreviation: String? = null): Boolean {
        val stripApostrophes = { s: String -> s.replace("'", "").replace("’", "") }
        val favClean = stripApostrophes(favorite.trim().lowercase())
        if (favClean.isEmpty()) return false

        val nameClean = stripApostrophes(teamName.trim().lowercase())
        val shortClean = stripApostrophes((shortName ?: "").trim().lowercase())
        val abbrClean = stripApostrophes((abbreviation ?: "").trim().lowercase())

        // 1. Exact match on any of the variations
        if (favClean == nameClean ||
            (shortClean.isNotEmpty() && favClean == shortClean) ||
            (abbrClean.isNotEmpty() && favClean == abbrClean)
        ) {
            return true
        }

        // 2. Word token match (e.g. "Celtics" matches "Boston Celtics", "LA" matches "LA Clippers")
        val delimiter = Regex("[\\s\\-./]+")
        val nameWords = nameClean.split(delimiter).filter { it.isNotEmpty() }
        val shortWords = shortClean.split(delimiter).filter { it.isNotEmpty() }
        val favWords = favClean.split(delimiter).filter { it.isNotEmpty() }

        if (nameWords.any { it == favClean } ||
            shortWords.any { it == favClean } ||
            favWords.any { it == nameClean } ||
            (shortClean.isNotEmpty() && favWords.any { it == shortClean }) ||
            (abbrClean.isNotEmpty() && favWords.any { it == abbrClean })
        ) {
            return true
        }

        // 3. Substring match at word boundaries (only permitted when token length >= 4)
        // Short tokens (length <= 3) strictly require exact or word-boundary token matches above.
        if (favClean.length >= 4) {
            if (matchesAtWordBoundary(favClean, nameClean) ||
                (shortClean.isNotEmpty() && matchesAtWordBoundary(favClean, shortClean))
            ) {
                return true
            }
        }
        if (nameClean.length >= 4 && matchesAtWordBoundary(nameClean, favClean)) {
            return true
        }
        if (shortClean.length >= 4 && matchesAtWordBoundary(shortClean, favClean)) {
            return true
        }

        return false
    }

    private fun matchesAtWordBoundary(query: String, target: String): Boolean {
        if (query.length < 4 || target.length < 4) return false
        return Regex("\\b${Regex.escape(query)}\\b").containsMatchIn(target)
    }

    fun toggleFavoriteTeam(teamName: String, shortName: String? = null, abbreviation: String? = null): Boolean {
        if (teamName.isBlank() && shortName.isNullOrBlank() && abbreviation.isNullOrBlank()) return false
        val current = getFavoriteTeams().toMutableSet()
        val canonicalName = teamName.ifBlank { shortName ?: abbreviation ?: "" }.trim()
        val existing = current.find { fav ->
            matchesFavorite(fav, canonicalName, shortName, abbreviation)
        }
        val nowFavorite: Boolean
        if (existing != null) {
            current.remove(existing)
            nowFavorite = false
        } else {
            current.add(canonicalName)
            nowFavorite = true
        }
        prefs.edit().putStringSet(KEY_FAVORITE_TEAMS, current).apply()
        return nowFavorite
    }

    fun addFavorite(teamName: String) {
        if (teamName.isBlank()) return
        val current = getFavoriteTeams().toMutableSet()
        current.add(teamName.trim())
        prefs.edit().putStringSet(KEY_FAVORITE_TEAMS, current).apply()
    }

    fun removeFavorite(teamName: String) {
        if (teamName.isBlank()) return
        val current = getFavoriteTeams().toMutableSet()
        val existing = current.find { matchesFavorite(it, teamName) }
        if (existing != null) {
            current.remove(existing)
            prefs.edit().putStringSet(KEY_FAVORITE_TEAMS, current).apply()
        }
    }
}
