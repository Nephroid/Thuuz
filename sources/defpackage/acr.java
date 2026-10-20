package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class acr implements Runnable {
    final /* synthetic */ acq a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public acr(acq acqVar) {
        this.a = acqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.b = Thread.currentThread();
        this.a.a();
    }
}
