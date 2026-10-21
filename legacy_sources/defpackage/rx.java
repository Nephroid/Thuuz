package defpackage;

import com.google.android.gms.internal.av;

@zy
/* loaded from: classes.dex */
public class rx {
    private final rz a;
    private final Runnable b;
    private av c;
    private boolean d;
    private boolean e;
    private long f;

    public rx(ajr ajrVar) {
        this(ajrVar, new rz(adm.a));
    }

    rx(ajr ajrVar, rz rzVar) {
        this.d = false;
        this.e = false;
        this.f = 0L;
        this.a = rzVar;
        this.b = new ry(this, ajrVar);
    }

    public void a() {
        this.d = false;
        this.a.a(this.b);
    }

    public void a(av avVar) {
        a(avVar, 60000L);
    }

    public void a(av avVar, long j) {
        if (this.d) {
            adn.e("An ad refresh is already scheduled.");
            return;
        }
        this.c = avVar;
        this.d = true;
        this.f = j;
        if (this.e) {
            return;
        }
        adn.c("Scheduling ad refresh " + j + " milliseconds from now.");
        this.a.a(this.b, j);
    }

    public void b() {
        this.e = true;
        if (this.d) {
            this.a.a(this.b);
        }
    }

    public void c() {
        this.e = false;
        if (this.d) {
            this.d = false;
            a(this.c, this.f);
        }
    }

    public boolean d() {
        return this.d;
    }
}
