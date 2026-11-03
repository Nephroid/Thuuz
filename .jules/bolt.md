## 2024-05-24 - Kotlin Regex Compilation in Hot Loops
**Learning:** Compiling dynamic `Regex` objects inside frequently called methods (like checking team name word boundaries in `FavoritesManager`) causes massive CPU overhead and garbage collection pauses compared to simple string scanning.
**Action:** Always extract static regular expressions to `companion object` properties. For dynamic patterns, prefer manual `String.indexOf` coupled with boundary checks over constructing and compiling new `Regex` instances at runtime.
