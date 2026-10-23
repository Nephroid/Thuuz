## 2024-05-24 - ThreadLocal SimpleDateFormat caching in UI sorting
**Learning:** In Android UI (Compose/RecyclerView), instantiating `SimpleDateFormat` inside sorting logic (e.g. `O(N log N)` list sorts) is a massive performance bottleneck due to heavy memory allocation and parsing overhead.
**Action:** Always cache `SimpleDateFormat` instances using `ThreadLocal` when used in list sorting or high-frequency loops. Add a fast path based on string length to select the correct formatter for a ~10x speedup.
