package defpackage;

import java.util.logging.Level;

/* loaded from: classes.dex */
class bcj implements Runnable {
    final /* synthetic */ bci a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bcj(bci bciVar) {
        this.a = bciVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.a.p();
        } catch (Throwable th) {
            this.a.a.H().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.a.u = null;
        }
    }
}
