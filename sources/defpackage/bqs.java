package defpackage;

import java.util.logging.Level;

/* loaded from: classes.dex */
class bqs implements Runnable {
    final /* synthetic */ bqr a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bqs(bqr bqrVar) {
        this.a = bqrVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.a();
        } catch (Throwable th) {
            this.a.b().log(Level.SEVERE, "problem reading provider: ", th);
        }
    }
}
