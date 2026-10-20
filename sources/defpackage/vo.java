package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;

/* loaded from: classes.dex */
public final class vo implements gz, ha {
    private final vp a;
    private final vq b;
    private final Object c;

    public vo(Context context, vp vpVar) {
        this(context, vpVar, false);
    }

    vo(Context context, vp vpVar, boolean z) {
        this.c = new Object();
        this.a = vpVar;
        this.b = new vq(context, this, this, 6111000);
        if (z) {
            return;
        }
        this.b.a();
    }

    @Override // defpackage.gz
    public void C_() {
        adn.a("Disconnected from remote ad request service.");
    }

    @Override // defpackage.gz
    public void a(Bundle bundle) {
        Bundle bundle2;
        Bundle a = uk.a();
        synchronized (this.c) {
            try {
                try {
                    try {
                        vr l = this.b.l();
                        bundle2 = l != null ? l.a() : a;
                    } catch (IllegalStateException e) {
                        adn.d("Error when get Gservice values", e);
                        if (this.b.c() || this.b.h()) {
                            this.b.b();
                            bundle2 = a;
                        }
                        bundle2 = a;
                    }
                } catch (RemoteException e2) {
                    adn.d("Error when get Gservice values", e2);
                    if (this.b.c() || this.b.h()) {
                        this.b.b();
                        bundle2 = a;
                    }
                    bundle2 = a;
                }
            } finally {
                if (this.b.c() || this.b.h()) {
                    this.b.b();
                }
            }
        }
        this.a.a(bundle2);
    }

    @Override // defpackage.ha
    public void a(gw gwVar) {
        this.a.a(uk.a());
    }
}
