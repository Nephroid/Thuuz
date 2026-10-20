package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bm implements Runnable {
    final /* synthetic */ bg a;
    final /* synthetic */ bl b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bm(bl blVar, bg bgVar) {
        this.b = blVar;
        this.a = bgVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a(this.a, false);
    }
}
