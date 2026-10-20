package defpackage;

import android.content.Context;
import com.google.android.gms.internal.fi;

@zy
/* loaded from: classes.dex */
public final class vw {
    private final fi a;
    private final wi b;
    private final Context c;
    private final vz e;
    private wc g;
    private final Object d = new Object();
    private boolean f = false;

    public vw(Context context, fi fiVar, wi wiVar, vz vzVar) {
        this.c = context;
        this.a = fiVar;
        this.b = wiVar;
        this.e = vzVar;
    }

    public we a(long j, long j2) {
        adn.a("Starting mediation.");
        for (vy vyVar : this.e.a) {
            adn.c("Trying mediation network: " + vyVar.b);
            for (String str : vyVar.c) {
                synchronized (this.d) {
                    if (this.f) {
                        return new we(-1);
                    }
                    this.g = new wc(this.c, str, this.b, this.e, vyVar, this.a.c, this.a.d, this.a.k);
                    we a = this.g.a(j, j2);
                    if (a.a == 0) {
                        adn.a("Adapter succeeded.");
                        return a;
                    }
                    if (a.c != null) {
                        adm.a.post(new vx(this, a));
                    }
                }
            }
        }
        return new we(1);
    }

    public void a() {
        synchronized (this.d) {
            this.f = true;
            if (this.g != null) {
                this.g.a();
            }
        }
    }
}
