package defpackage;

import android.os.RemoteException;

@zy
/* loaded from: classes.dex */
public final class wt implements InterfaceC0005do, dq {
    private final wo a;

    public wt(wo woVar) {
        this.a = woVar;
    }

    @Override // defpackage.InterfaceC0005do
    public void a(dn dnVar) {
        kb.b("onAdLoaded must be called on the main UI thread.");
        adn.a("Adapter called onAdLoaded.");
        try {
            this.a.e();
        } catch (RemoteException e) {
            adn.d("Could not call onAdLoaded.", e);
        }
    }

    @Override // defpackage.InterfaceC0005do
    public void a(dn dnVar, int i) {
        kb.b("onAdFailedToLoad must be called on the main UI thread.");
        adn.a("Adapter called onAdFailedToLoad with error. " + i);
        try {
            this.a.a(i);
        } catch (RemoteException e) {
            adn.d("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // defpackage.dq
    public void a(dp dpVar) {
        kb.b("onAdLoaded must be called on the main UI thread.");
        adn.a("Adapter called onAdLoaded.");
        try {
            this.a.e();
        } catch (RemoteException e) {
            adn.d("Could not call onAdLoaded.", e);
        }
    }

    @Override // defpackage.dq
    public void a(dp dpVar, int i) {
        kb.b("onAdFailedToLoad must be called on the main UI thread.");
        adn.a("Adapter called onAdFailedToLoad with error " + i + ".");
        try {
            this.a.a(i);
        } catch (RemoteException e) {
            adn.d("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // defpackage.InterfaceC0005do
    public void b(dn dnVar) {
        kb.b("onAdOpened must be called on the main UI thread.");
        adn.a("Adapter called onAdOpened.");
        try {
            this.a.d();
        } catch (RemoteException e) {
            adn.d("Could not call onAdOpened.", e);
        }
    }

    @Override // defpackage.dq
    public void b(dp dpVar) {
        kb.b("onAdOpened must be called on the main UI thread.");
        adn.a("Adapter called onAdOpened.");
        try {
            this.a.d();
        } catch (RemoteException e) {
            adn.d("Could not call onAdOpened.", e);
        }
    }

    @Override // defpackage.InterfaceC0005do
    public void c(dn dnVar) {
        kb.b("onAdClosed must be called on the main UI thread.");
        adn.a("Adapter called onAdClosed.");
        try {
            this.a.b();
        } catch (RemoteException e) {
            adn.d("Could not call onAdClosed.", e);
        }
    }

    @Override // defpackage.dq
    public void c(dp dpVar) {
        kb.b("onAdClosed must be called on the main UI thread.");
        adn.a("Adapter called onAdClosed.");
        try {
            this.a.b();
        } catch (RemoteException e) {
            adn.d("Could not call onAdClosed.", e);
        }
    }

    @Override // defpackage.InterfaceC0005do
    public void d(dn dnVar) {
        kb.b("onAdLeftApplication must be called on the main UI thread.");
        adn.a("Adapter called onAdLeftApplication.");
        try {
            this.a.c();
        } catch (RemoteException e) {
            adn.d("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // defpackage.dq
    public void d(dp dpVar) {
        kb.b("onAdLeftApplication must be called on the main UI thread.");
        adn.a("Adapter called onAdLeftApplication.");
        try {
            this.a.c();
        } catch (RemoteException e) {
            adn.d("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // defpackage.InterfaceC0005do
    public void e(dn dnVar) {
        kb.b("onAdClicked must be called on the main UI thread.");
        adn.a("Adapter called onAdClicked.");
        try {
            this.a.a();
        } catch (RemoteException e) {
            adn.d("Could not call onAdClicked.", e);
        }
    }
}
