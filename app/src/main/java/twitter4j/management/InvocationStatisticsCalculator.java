package twitter4j.management;

/* loaded from: classes.dex */
public class InvocationStatisticsCalculator implements InvocationStatistics {
    private long callCount;
    private long errorCount;
    private int index;
    private String name;
    private long[] times;
    private long totalTime;

    public InvocationStatisticsCalculator(String str, int i) {
        this.name = str;
        this.times = new long[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void increment(long j, boolean z) {
        this.callCount++;
        this.errorCount = (z ? 0L : 1L) + this.errorCount;
        this.totalTime += j;
        this.times[this.index] = j;
        int i = this.index + 1;
        this.index = i;
        if (i >= this.times.length) {
            this.index = 0;
        }
    }

    @Override // twitter4j.management.InvocationStatistics
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.management.InvocationStatistics
    public long getCallCount() {
        return this.callCount;
    }

    @Override // twitter4j.management.InvocationStatistics
    public long getErrorCount() {
        return this.errorCount;
    }

    @Override // twitter4j.management.InvocationStatistics
    public long getTotalTime() {
        return this.totalTime;
    }

    @Override // twitter4j.management.InvocationStatistics
    public synchronized long getAverageTime() {
        long j = 0;
        synchronized (this) {
            int min = Math.min(Math.abs((int) this.callCount), this.times.length);
            if (min != 0) {
                long j2 = 0;
                for (int i = 0; i < min; i++) {
                    j2 += this.times[i];
                }
                j = j2 / min;
            }
        }
        return j;
    }

    @Override // twitter4j.management.InvocationStatistics
    public synchronized void reset() {
        this.callCount = 0L;
        this.errorCount = 0L;
        this.totalTime = 0L;
        this.times = new long[this.times.length];
        this.index = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("calls=").append(getCallCount()).append(",").append("errors=").append(getErrorCount()).append(",").append("totalTime=").append(getTotalTime()).append(",").append("avgTime=").append(getAverageTime());
        return sb.toString();
    }
}
