package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class fk extends Thread {
    final /* synthetic */ fj a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fk(fj fjVar, String str) {
        super(str);
        this.a = fjVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        obj = this.a.f;
        synchronized (obj) {
            this.a.d = this.a.d();
            this.a.e = true;
            obj2 = this.a.f;
            obj2.notifyAll();
        }
    }
}
