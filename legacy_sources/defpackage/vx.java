package defpackage;

import android.os.RemoteException;

/* loaded from: classes.dex */
class vx implements Runnable {
    final /* synthetic */ we a;
    final /* synthetic */ vw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public vx(vw vwVar, we weVar) {
        this.b = vwVar;
        this.a = weVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.c.c();
        } catch (RemoteException e) {
            adn.d("Could not destroy mediation adapter.", e);
        }
    }
}
