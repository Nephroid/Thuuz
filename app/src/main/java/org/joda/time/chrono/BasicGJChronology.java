package org.joda.time.chrono;

import org.joda.time.Chronology;

/* loaded from: classes.dex */
abstract class BasicGJChronology extends BasicChronology {
    private static final long FEB_29 = 5097600000L;
    private static final long serialVersionUID = 538276888268L;
    private static final int[] MIN_DAYS_PER_MONTH_ARRAY = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] MAX_DAYS_PER_MONTH_ARRAY = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final long[] MIN_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
    private static final long[] MAX_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];

    static {
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < 11; i++) {
            j2 += MIN_DAYS_PER_MONTH_ARRAY[i] * 86400000;
            MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[i + 1] = j2;
            j += MAX_DAYS_PER_MONTH_ARRAY[i] * 86400000;
            MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[i + 1] = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicGJChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.joda.time.chrono.BasicChronology
    public int getMonthOfYear(long j, int i) {
        int yearMillis = (int) ((j - getYearMillis(i)) >> 10);
        if (isLeapYear(i)) {
            if (yearMillis < 15356250) {
                if (yearMillis < 7678125) {
                    if (yearMillis < 2615625) {
                        return 1;
                    }
                    return yearMillis < 5062500 ? 2 : 3;
                }
                if (yearMillis < 10209375) {
                    return 4;
                }
                return yearMillis < 12825000 ? 5 : 6;
            }
            if (yearMillis < 23118750) {
                if (yearMillis < 17971875) {
                    return 7;
                }
                return yearMillis < 20587500 ? 8 : 9;
            }
            if (yearMillis < 25734375) {
                return 10;
            }
            return yearMillis < 28265625 ? 11 : 12;
        }
        if (yearMillis < 15271875) {
            if (yearMillis < 7593750) {
                if (yearMillis >= 2615625) {
                    return yearMillis < 4978125 ? 2 : 3;
                }
                return 1;
            }
            if (yearMillis < 10125000) {
                return 4;
            }
            return yearMillis < 12740625 ? 5 : 6;
        }
        if (yearMillis < 23034375) {
            if (yearMillis < 17887500) {
                return 7;
            }
            return yearMillis < 20503125 ? 8 : 9;
        }
        if (yearMillis < 25650000) {
            return 10;
        }
        return yearMillis < 28181250 ? 11 : 12;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.joda.time.chrono.BasicChronology
    public int getDaysInYearMonth(int i, int i2) {
        return isLeapYear(i) ? MAX_DAYS_PER_MONTH_ARRAY[i2 - 1] : MIN_DAYS_PER_MONTH_ARRAY[i2 - 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.joda.time.chrono.BasicChronology
    public int getDaysInMonthMax(int i) {
        return MAX_DAYS_PER_MONTH_ARRAY[i - 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.joda.time.chrono.BasicChronology
    public int getDaysInMonthMaxForSet(long j, int i) {
        if (i > 28 || i < 1) {
            return getDaysInMonthMax(j);
        }
        return 28;
    }

    @Override // org.joda.time.chrono.BasicChronology
    long getTotalMillisByYearMonth(int i, int i2) {
        return isLeapYear(i) ? MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[i2 - 1] : MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[i2 - 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0035  */
    @Override // org.joda.time.chrono.BasicChronology
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long getYearDifference(long r10, long r12) {
        /*
            r9 = this;
            int r6 = r9.getYear(r10)
            int r7 = r9.getYear(r12)
            long r0 = r9.getYearMillis(r6)
            long r2 = r10 - r0
            long r0 = r9.getYearMillis(r7)
            long r0 = r12 - r0
            r4 = 5097600000(0x12fd73400, double:2.518549036E-314)
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 < 0) goto L4f
            boolean r4 = r9.isLeapYear(r7)
            if (r4 == 0) goto L39
            boolean r4 = r9.isLeapYear(r6)
            if (r4 != 0) goto L4f
            r4 = 86400000(0x5265c00, double:4.2687272E-316)
            long r0 = r0 - r4
            r4 = r2
            r2 = r0
        L2f:
            int r0 = r6 - r7
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 >= 0) goto L37
            int r0 = r0 + (-1)
        L37:
            long r0 = (long) r0
            return r0
        L39:
            r4 = 5097600000(0x12fd73400, double:2.518549036E-314)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 < 0) goto L4f
            boolean r4 = r9.isLeapYear(r6)
            if (r4 == 0) goto L4f
            r4 = 86400000(0x5265c00, double:4.2687272E-316)
            long r2 = r2 - r4
            r4 = r2
            r2 = r0
            goto L2f
        L4f:
            r4 = r2
            r2 = r0
            goto L2f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.chrono.BasicGJChronology.getYearDifference(long, long):long");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.joda.time.chrono.BasicChronology
    public long setYear(long j, int i) {
        int year = getYear(j);
        int dayOfYear = getDayOfYear(j, year);
        int millisOfDay = getMillisOfDay(j);
        if (dayOfYear > 59) {
            if (isLeapYear(year)) {
                if (!isLeapYear(i)) {
                    dayOfYear--;
                }
            } else if (isLeapYear(i)) {
                dayOfYear++;
            }
        }
        return getYearMonthDayMillis(i, 1, dayOfYear) + millisOfDay;
    }
}
