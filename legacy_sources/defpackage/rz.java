package defpackage;

import android.os.Handler;

/* loaded from: classes.dex */
public class rz {
    private final Handler a;

    public rz(Handler handler) {
        this.a = handler;
    }

    public void a(Runnable runnable) {
        this.a.removeCallbacks(runnable);
    }

    public boolean a(Runnable runnable, long j) {
        return this.a.postDelayed(runnable, j);
    }
}
