package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ccb implements Runnable {
    final /* synthetic */ bg a;
    final /* synthetic */ cca b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ccb(cca ccaVar, bg bgVar) {
        this.b = ccaVar;
        this.a = bgVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a(this.a, false);
    }
}
