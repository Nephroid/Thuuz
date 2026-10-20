package defpackage;

/* loaded from: classes.dex */
public class t {
    final /* synthetic */ l a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ t(l lVar, m mVar) {
        this(lVar);
    }

    private t(l lVar) {
        this.a = lVar;
    }

    public l<TResult> a() {
        return this.a;
    }

    public boolean b() {
        Object obj;
        boolean z;
        Object obj2;
        boolean z2 = true;
        obj = this.a.d;
        synchronized (obj) {
            z = this.a.e;
            if (!z) {
                this.a.e = true;
                this.a.f = true;
                obj2 = this.a.d;
                obj2.notifyAll();
                this.a.h();
            } else {
                z2 = false;
            }
        }
        return z2;
    }

    public boolean a(TResult tresult) {
        Object obj;
        boolean z;
        Object obj2;
        boolean z2 = true;
        obj = this.a.d;
        synchronized (obj) {
            z = this.a.e;
            if (!z) {
                this.a.e = true;
                this.a.g = tresult;
                obj2 = this.a.d;
                obj2.notifyAll();
                this.a.h();
            } else {
                z2 = false;
            }
        }
        return z2;
    }

    public boolean a(Exception exc) {
        Object obj;
        boolean z;
        Object obj2;
        boolean z2 = true;
        obj = this.a.d;
        synchronized (obj) {
            z = this.a.e;
            if (!z) {
                this.a.e = true;
                this.a.h = exc;
                obj2 = this.a.d;
                obj2.notifyAll();
                this.a.h();
            } else {
                z2 = false;
            }
        }
        return z2;
    }

    public void c() {
        if (!b()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void b(TResult tresult) {
        if (!a((t) tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public void b(Exception exc) {
        if (!a(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
