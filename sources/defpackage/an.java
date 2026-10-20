package defpackage;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class an {
    public static final boolean a = am.b;
    private final List<ao> b = new ArrayList();
    private boolean c = false;

    public synchronized void a(String str, long j) {
        if (this.c) {
            throw new IllegalStateException("Marker added to finished log");
        }
        this.b.add(new ao(str, j, SystemClock.elapsedRealtime()));
    }

    public synchronized void a(String str) {
        this.c = true;
        long a2 = a();
        if (a2 > 0) {
            long j = this.b.get(0).c;
            am.b("(%-4d ms) %s", Long.valueOf(a2), str);
            long j2 = j;
            for (ao aoVar : this.b) {
                long j3 = aoVar.c;
                am.b("(+%-4d) [%2d] %s", Long.valueOf(j3 - j2), Long.valueOf(aoVar.b), aoVar.a);
                j2 = j3;
            }
        }
    }

    protected void finalize() {
        if (!this.c) {
            a("Request on the loose");
            am.c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }

    private long a() {
        if (this.b.size() == 0) {
            return 0L;
        }
        return this.b.get(this.b.size() - 1).c - this.b.get(0).c;
    }
}
