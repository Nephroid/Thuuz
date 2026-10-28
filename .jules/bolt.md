## 2025-02-05 - Avoid Dynamic Regex in Hot Paths
**Learning:** Dynamic Regex compilation in hot paths like FavoritesManager.matchesFavorite and matchesAtWordBoundary can cause performance degradation on Android.
**Action:** Extract regular expressions to companion object constants, and use manual character/string manipulation instead for word boundaries.
