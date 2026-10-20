package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class cbi implements Runnable {
    final /* synthetic */ cbf a;
    final /* synthetic */ cbh b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cbi(cbh cbhVar, cbf cbfVar) {
        this.b = cbhVar;
        this.a = cbfVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a.a(this.a);
    }
}
