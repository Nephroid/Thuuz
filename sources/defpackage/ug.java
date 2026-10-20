package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.internal.ay;

/* loaded from: classes.dex */
public class ug {
    private final wh a;
    private final Context b;
    private final to c;
    private cx d;
    private tu e;
    private String f;
    private String g;
    private de h;
    private dv i;
    private dt j;
    private df k;
    private di l;

    public ug(Context context) {
        this(context, to.a(), null);
    }

    public ug(Context context, to toVar, df dfVar) {
        this.a = new wh();
        this.b = context;
        this.c = toVar;
        this.k = dfVar;
    }

    private void b(String str) {
        if (this.f == null) {
            c(str);
        }
        this.e = tm.a(this.b, new ay(), this.f, this.a);
        if (this.d != null) {
            this.e.a(new tl(this.d));
        }
        if (this.h != null) {
            this.e.a(new tq(this.h));
        }
        if (this.j != null) {
            this.e.a(new zg(this.j));
        }
        if (this.i != null) {
            this.e.a(new zj(this.i), this.g);
        }
        if (this.l != null) {
            this.e.a(new zw(this.l, this.k));
        }
    }

    private void c(String str) {
        if (this.e == null) {
            throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + str + " is called.");
        }
    }

    public void a() {
        try {
            c("show");
            this.e.h();
        } catch (RemoteException e) {
            adn.d("Failed to show interstitial.", e);
        }
    }

    public void a(cx cxVar) {
        try {
            this.d = cxVar;
            if (this.e != null) {
                this.e.a(cxVar != null ? new tl(cxVar) : null);
            }
        } catch (RemoteException e) {
            adn.d("Failed to set the AdListener.", e);
        }
    }

    public void a(String str) {
        if (this.f != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.f = str;
    }

    public void a(ud udVar) {
        try {
            if (this.e == null) {
                b("loadAd");
            }
            if (this.e.a(this.c.a(this.b, udVar))) {
                this.a.a(udVar.i());
            }
        } catch (RemoteException e) {
            adn.d("Failed to load ad.", e);
        }
    }
}
