## 2023-11-01 - [Avoid Compiling Regex in Kotlin Hot Loops]
**Learning:** Compiling Regex dynamically inside a hot loop (like a list filtering algorithm inside a UI composable or adapter) causes severe performance degradation and memory churn in Kotlin.
**Action:** Extract static regex patterns to a `companion object` to compile them once. Prefer replacing dynamic regular expressions with manual string parsing methods (like `indexOf` and `.isLetterOrDigit()`) for high-frequency operations.
