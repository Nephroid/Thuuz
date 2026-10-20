package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class s implements Runnable {
    final /* synthetic */ k a;
    final /* synthetic */ l b;
    final /* synthetic */ t c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(k kVar, l lVar, t tVar) {
        this.a = kVar;
        this.b = lVar;
        this.c = tVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.c.b((t) this.a.then(this.b));
        } catch (Exception e) {
            this.c.b(e);
        }
    }
}
