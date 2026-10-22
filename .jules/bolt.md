## 2026-10-22 - Sequential Network Calls in Background Worker
**Learning:** The background worker (`ThrillerNotificationWorker`) was using `fetchLiveGamesSync` which performed 7 sequential HTTP requests (one for each league). This blocked the worker's execution significantly, delaying time-critical push notifications.
**Action:** Replaced the loop with parallel asynchronous requests using `runBlocking` and `awaitAll()` to fetch all leagues concurrently, reducing total network time from O(N) to O(1) latency.
