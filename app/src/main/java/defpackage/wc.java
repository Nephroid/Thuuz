package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gt;

@zy
/* loaded from: classes.dex */
public final class wc implements wf {
    private final String a;
    private final wi b;
    private final long c;
    private final vy d;
    private final av e;
    private final ay f;
    private final Context g;
    private final gt i;
    private wl j;
    private final Object h = new Object();
    private int k = -2;

    public wc(Context context, String str, wi wiVar, vz vzVar, vy vyVar, av avVar, ay ayVar, gt gtVar) {
        this.g = context;
        this.b = wiVar;
        this.d = vyVar;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.a = b();
        } else {
            this.a = str;
        }
        this.c = vzVar.b != -1 ? vzVar.b : 10000L;
        this.e = avVar;
        this.f = ayVar;
        this.i = gtVar;
    }

    private void a(long j, long j2, long j3, long j4) {
        while (this.k == -2) {
            b(j, j2, j3, j4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(wb wbVar) {
        try {
            if (this.i.d < 4100000) {
                if (this.f.e) {
                    this.j.a(og.a(this.g), this.e, this.d.g, wbVar);
                } else {
                    this.j.a(og.a(this.g), this.f, this.e, this.d.g, wbVar);
                }
            } else if (this.f.e) {
                this.j.a(og.a(this.g), this.e, this.d.g, this.d.a, wbVar);
            } else {
                this.j.a(og.a(this.g), this.f, this.e, this.d.g, this.d.a, wbVar);
            }
        } catch (RemoteException e) {
            adn.d("Could not request ad from mediation adapter.", e);
            a(5);
        }
    }

    private String b() {
        try {
            if (!TextUtils.isEmpty(this.d.e)) {
                return this.b.b(this.d.e) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException e) {
            adn.e("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private void b(long j, long j2, long j3, long j4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (elapsedRealtime - j);
        long j6 = j4 - (elapsedRealtime - j3);
        if (j5 <= 0 || j6 <= 0) {
            adn.c("Timed out waiting for adapter.");
            this.k = 3;
        } else {
            try {
                this.h.wait(Math.min(j5, j6));
            } catch (InterruptedException e) {
                this.k = -1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public wl c() {
        adn.c("Instantiating mediation adapter: " + this.a);
        try {
            return this.b.a(this.a);
        } catch (RemoteException e) {
            adn.a("Could not instantiate mediation adapter: " + this.a, e);
            return null;
        }
    }

    public we a(long j, long j2) {
        we weVar;
        synchronized (this.h) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            wb wbVar = new wb();
            adm.a.post(new wd(this, wbVar));
            a(elapsedRealtime, this.c, j, j2);
            weVar = new we(this.d, this.j, this.a, wbVar, this.k);
        }
        return weVar;
    }

    public void a() {
        synchronized (this.h) {
            try {
                if (this.j != null) {
                    this.j.c();
                }
            } catch (RemoteException e) {
                adn.d("Could not destroy mediation adapter.", e);
            }
            this.k = -1;
            this.h.notify();
        }
    }

    @Override // defpackage.wf
    public void a(int i) {
        synchronized (this.h) {
            this.k = i;
            this.h.notify();
        }
    }
}
