## 2024-10-30 - Kotlin Regex Performance in Hot Loops
**Learning:** Compiling `Regex` objects inside frequently called methods (like matching strings or checking bounds) can cause significant performance overhead in Kotlin on this Android codebase.
**Action:** Avoid compiling `Regex` objects dynamically or inside hot loops/methods. Extract static patterns to `companion object` constants, and use manual string operations (like `indexOf` and character checks) for dynamic or high-frequency text processing.
