package defpackage;

import android.app.Activity;
import com.google.android.gms.analytics.t;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ek implements ea {
    final /* synthetic */ ej a;
    private long f;
    private boolean b = false;
    private int c = 0;
    private long d = -1;
    private boolean e = false;
    private afx g = afy.c();

    public ek(ej ejVar) {
        this.a = ejVar;
    }

    private void c() {
        ek ekVar;
        ek ekVar2;
        dz a = dz.a();
        if (a == null) {
            gn.a("GoogleAnalytics isn't initialized for the Tracker!");
            return;
        }
        if (this.d >= 0 || this.b) {
            ekVar = this.a.i;
            a.a(ekVar);
        } else {
            ekVar2 = this.a.i;
            a.b(ekVar2);
        }
    }

    public void a(long j) {
        this.d = j;
        c();
    }

    @Override // defpackage.ea
    public void a(Activity activity) {
        ev evVar;
        String canonicalName;
        ev evVar2;
        t.a().a(t.a.EASY_TRACKER_ACTIVITY_START);
        if (this.c == 0 && b()) {
            this.e = true;
        }
        this.c++;
        if (this.b) {
            HashMap hashMap = new HashMap();
            hashMap.put("&t", "screenview");
            t.a().a(true);
            ej ejVar = this.a;
            evVar = this.a.j;
            if (evVar != null) {
                evVar2 = this.a.j;
                canonicalName = evVar2.a(activity);
            } else {
                canonicalName = activity.getClass().getCanonicalName();
            }
            ejVar.a("&cd", canonicalName);
            this.a.a(hashMap);
            t.a().a(false);
        }
    }

    public void a(boolean z) {
        this.b = z;
        c();
    }

    public boolean a() {
        boolean z = this.e;
        this.e = false;
        return z;
    }

    @Override // defpackage.ea
    public void b(Activity activity) {
        t.a().a(t.a.EASY_TRACKER_ACTIVITY_STOP);
        this.c--;
        this.c = Math.max(0, this.c);
        if (this.c == 0) {
            this.f = this.g.b();
        }
    }

    boolean b() {
        return this.g.b() >= this.f + Math.max(1000L, this.d);
    }
}
