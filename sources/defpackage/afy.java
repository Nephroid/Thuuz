package defpackage;

import android.os.SystemClock;

/* loaded from: classes.dex */
public final class afy implements afx {
    private static afy a;

    public static synchronized afx c() {
        afy afyVar;
        synchronized (afy.class) {
            if (a == null) {
                a = new afy();
            }
            afyVar = a;
        }
        return afyVar;
    }

    @Override // defpackage.afx
    public long a() {
        return System.currentTimeMillis();
    }

    @Override // defpackage.afx
    public long b() {
        return SystemClock.elapsedRealtime();
    }
}
