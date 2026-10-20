package defpackage;

import android.util.Log;

/* loaded from: classes.dex */
class cba implements Runnable {
    final /* synthetic */ caz a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cba(caz cazVar) {
        this.a = cazVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.a.au();
            this.a.a.af();
        } catch (Throwable th) {
            Log.e("thuuz", "problem", th);
        }
    }
}
