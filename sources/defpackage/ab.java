package defpackage;

import com.android.volley.Request;

/* loaded from: classes.dex */
class ab implements Runnable {
    final /* synthetic */ z a;
    private final Request b;
    private final ah c;
    private final Runnable d;

    public ab(z zVar, Request request, ah ahVar, Runnable runnable) {
        this.a = zVar;
        this.b = request;
        this.c = ahVar;
        this.d = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.g()) {
            this.b.b("canceled-at-delivery");
            return;
        }
        if (this.c.a()) {
            this.b.a((Request) this.c.a);
        } else {
            this.b.b(this.c.c);
        }
        if (this.c.d) {
            this.b.a("intermediate-response");
        } else {
            this.b.b("done");
        }
        if (this.d != null) {
            this.d.run();
        }
    }
}
