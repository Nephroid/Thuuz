package defpackage;

@zy
/* loaded from: classes.dex */
public abstract class acq {
    private final Runnable a = new acr(this);
    private volatile Thread b;

    public abstract void a();

    public abstract void b();

    public final void e() {
        act.a(this.a);
    }

    public final void f() {
        b();
        if (this.b != null) {
            this.b.interrupt();
        }
    }
}
