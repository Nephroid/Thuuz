## 2024-10-25 - Extracted Regex compilation in matchesFavorite and memoized Favorite checks in UI

**Learning:** Recompiling a heavy Regex continuously inside a loop or UI rendering path can severely hurt performance in Kotlin/Android. Re-running intensive string-matching algorithms during every Jetpack Compose frame recomposition is a significant anti-pattern.

**Action:** Always extract Regex object instantiations to `companion object` constants when they don't change. When using Jetpack Compose, always wrap potentially expensive calculations inside `LazyColumn` iterations in a `remember` block so they only evaluate when keys change.
