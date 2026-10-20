package defpackage;

import com.google.android.gms.internal.Cdo;

/* loaded from: classes.dex */
public final class xr {
    private final Runnable a;
    private volatile boolean b = false;

    public xr(Cdo cdo) {
        this.a = new xs(this, cdo);
    }

    public void a() {
        this.b = true;
        adm.a.removeCallbacks(this.a);
    }

    public void b() {
        adm.a.postDelayed(this.a, 250L);
    }
}
