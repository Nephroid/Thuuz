package defpackage;

/* loaded from: classes.dex */
class gf implements Runnable {
    final /* synthetic */ gd a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public gf(gd gdVar) {
        this.a = gdVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        et etVar;
        etVar = this.a.g;
        etVar.b();
    }
}
