package defpackage;

@zy
/* loaded from: classes.dex */
public final class wb extends wp {
    private final Object a = new Object();
    private wf b;
    private wa c;

    @Override // defpackage.wo
    public void a() {
        synchronized (this.a) {
            if (this.c != null) {
                this.c.m();
            }
        }
    }

    @Override // defpackage.wo
    public void a(int i) {
        synchronized (this.a) {
            if (this.b != null) {
                this.b.a(i == 3 ? 1 : 2);
                this.b = null;
            }
        }
    }

    public void a(wa waVar) {
        synchronized (this.a) {
            this.c = waVar;
        }
    }

    public void a(wf wfVar) {
        synchronized (this.a) {
            this.b = wfVar;
        }
    }

    @Override // defpackage.wo
    public void b() {
        synchronized (this.a) {
            if (this.c != null) {
                this.c.n();
            }
        }
    }

    @Override // defpackage.wo
    public void c() {
        synchronized (this.a) {
            if (this.c != null) {
                this.c.o();
            }
        }
    }

    @Override // defpackage.wo
    public void d() {
        synchronized (this.a) {
            if (this.c != null) {
                this.c.p();
            }
        }
    }

    @Override // defpackage.wo
    public void e() {
        synchronized (this.a) {
            if (this.b != null) {
                this.b.a(0);
                this.b = null;
            } else {
                if (this.c != null) {
                    this.c.q();
                }
            }
        }
    }
}
