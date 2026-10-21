package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.bb;

/* loaded from: classes.dex */
public final class uf {
    private final wh a;
    private final to b;
    private cx c;
    private tu d;
    private db[] e;
    private String f;
    private String g;
    private ViewGroup h;
    private de i;
    private dt j;
    private dv k;
    private dh l;

    public uf(ViewGroup viewGroup) {
        this(viewGroup, null, false, to.a());
    }

    public uf(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, to.a());
    }

    uf(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, to toVar) {
        this(viewGroup, attributeSet, z, toVar, null);
    }

    uf(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, to toVar, tu tuVar) {
        this.a = new wh();
        this.h = viewGroup;
        this.b = toVar;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                bb bbVar = new bb(context, attributeSet);
                this.e = bbVar.a(z);
                this.f = bbVar.a();
                if (viewGroup.isInEditMode()) {
                    adm.a(viewGroup, new ay(context, this.e[0]), "Ads by Google");
                    return;
                }
            } catch (IllegalArgumentException e) {
                adm.a(viewGroup, new ay(context, db.a), e.getMessage(), e.getMessage());
                return;
            }
        }
        this.d = tuVar;
    }

    private void k() {
        try {
            od c = this.d.c();
            if (c == null) {
                return;
            }
            this.h.addView((View) og.a(c));
        } catch (RemoteException e) {
            adn.d("Failed to get an ad frame.", e);
        }
    }

    private void l() {
        if ((this.e == null || this.f == null) && this.d == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        Context context = this.h.getContext();
        this.d = tm.a(context, new ay(context, this.e), this.f, this.a);
        if (this.c != null) {
            this.d.a(new tl(this.c));
        }
        if (this.i != null) {
            this.d.a(new tq(this.i));
        }
        if (this.j != null) {
            this.d.a(new zg(this.j));
        }
        if (this.k != null) {
            this.d.a(new zj(this.k), this.g);
        }
        if (this.l != null) {
            this.d.a(new zv(this.l, (PublisherAdView) this.h));
        }
        k();
    }

    public void a() {
        try {
            if (this.d != null) {
                this.d.d();
            }
        } catch (RemoteException e) {
            adn.d("Failed to destroy AdView.", e);
        }
    }

    public void a(cx cxVar) {
        try {
            this.c = cxVar;
            if (this.d != null) {
                this.d.a(cxVar != null ? new tl(cxVar) : null);
            }
        } catch (RemoteException e) {
            adn.d("Failed to set the AdListener.", e);
        }
    }

    public void a(de deVar) {
        try {
            this.i = deVar;
            if (this.d != null) {
                this.d.a(deVar != null ? new tq(deVar) : null);
            }
        } catch (RemoteException e) {
            adn.d("Failed to set the AppEventListener.", e);
        }
    }

    public void a(dt dtVar) {
        if (this.k != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.j = dtVar;
            if (this.d != null) {
                this.d.a(dtVar != null ? new zg(dtVar) : null);
            }
        } catch (RemoteException e) {
            adn.d("Failed to set the InAppPurchaseListener.", e);
        }
    }

    public void a(dv dvVar, String str) {
        if (this.j != null) {
            throw new IllegalStateException("InAppPurchaseListener has already been set.");
        }
        try {
            this.k = dvVar;
            this.g = str;
            if (this.d != null) {
                this.d.a(dvVar != null ? new zj(dvVar) : null, str);
            }
        } catch (RemoteException e) {
            adn.d("Failed to set the play store purchase parameter.", e);
        }
    }

    public void a(String str) {
        if (this.f != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.f = str;
    }

    public void a(ud udVar) {
        try {
            if (this.d == null) {
                l();
            }
            if (this.d.a(this.b.a(this.h.getContext(), udVar))) {
                this.a.a(udVar.i());
            }
        } catch (RemoteException e) {
            adn.d("Failed to load ad.", e);
        }
    }

    public void a(db... dbVarArr) {
        if (this.e != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        b(dbVarArr);
    }

    public cx b() {
        return this.c;
    }

    public void b(db... dbVarArr) {
        this.e = dbVarArr;
        try {
            if (this.d != null) {
                this.d.a(new ay(this.h.getContext(), this.e));
            }
        } catch (RemoteException e) {
            adn.d("Failed to set the ad size.", e);
        }
        this.h.requestLayout();
    }

    public db c() {
        try {
            if (this.d != null) {
                return this.d.k().a();
            }
        } catch (RemoteException e) {
            adn.d("Failed to get the current AdSize.", e);
        }
        if (this.e != null) {
            return this.e[0];
        }
        return null;
    }

    public db[] d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public de f() {
        return this.i;
    }

    public dt g() {
        return this.j;
    }

    public void h() {
        try {
            if (this.d != null) {
                this.d.f();
            }
        } catch (RemoteException e) {
            adn.d("Failed to call pause.", e);
        }
    }

    public void i() {
        try {
            if (this.d != null) {
                this.d.g();
            }
        } catch (RemoteException e) {
            adn.d("Failed to call resume.", e);
        }
    }

    public String j() {
        try {
            if (this.d != null) {
                return this.d.l();
            }
        } catch (RemoteException e) {
            adn.d("Failed to get the mediation adapter class name.", e);
        }
        return null;
    }
}
