package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class cas implements Runnable {
    final /* synthetic */ bya a;
    final /* synthetic */ car b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cas(car carVar, bya byaVar) {
        this.b = carVar;
        this.a = byaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.l();
    }
}
