package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements Runnable {
    final /* synthetic */ k a;
    final /* synthetic */ l b;
    final /* synthetic */ t c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(k kVar, l lVar, t tVar) {
        this.a = kVar;
        this.b = lVar;
        this.c = tVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            l lVar = (l) this.a.then(this.b);
            if (lVar == null) {
                this.c.b((t) null);
            } else {
                lVar.a((k) new o(this));
            }
        } catch (Exception e) {
            this.c.b(e);
        }
    }
}
