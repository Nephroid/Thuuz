package defpackage;

/* loaded from: classes.dex */
class abn implements Runnable {
    final /* synthetic */ acg a;
    final /* synthetic */ abm b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abn(abm abmVar, acg acgVar) {
        this.b = abmVar;
        this.a = acgVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        aav aavVar;
        aavVar = this.b.a;
        aavVar.a(this.a);
    }
}
