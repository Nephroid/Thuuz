package defpackage;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.dk;
import com.google.android.gms.internal.dm;
import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.gt;
import com.google.android.gms.internal.gv;
import com.google.android.gms.internal.x;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@zy
/* loaded from: classes.dex */
public class ajr extends tv implements aap, aav, acn, ajq, rw, uw, vh, vj, wa, xq, xt {
    private av a;
    private final wi b;
    private final ajw c;
    private final rx d;
    private final sc e;
    private boolean f;
    private final ComponentCallbacks g;

    ajr(ajw ajwVar, wi wiVar, rx rxVar) {
        this.g = new ajs(this);
        this.c = ajwVar;
        this.b = wiVar;
        this.d = rxVar == null ? new rx(this) : rxVar;
        this.e = new sc();
        acx.b(this.c.c);
        acl.a(this.c.c, this.c.e);
        w();
    }

    public ajr(Context context, ay ayVar, String str, wi wiVar, gt gtVar) {
        this(new ajw(context, ayVar, str, gtVar), wiVar, null);
    }

    private void A() {
        adn.c("Ad opening.");
        if (this.c.f != null) {
            try {
                this.c.f.d();
            } catch (RemoteException e) {
                adn.d("Could not call AdListener.onAdOpened().", e);
            }
        }
    }

    private void B() {
        adn.c("Ad finished loading.");
        if (this.c.f != null) {
            try {
                this.c.f.c();
            } catch (RemoteException e) {
                adn.d("Could not call AdListener.onAdLoaded().", e);
            }
        }
    }

    private void C() {
        try {
            if (!(this.c.j.w instanceof ul) || this.c.r == null) {
                return;
            }
            this.c.r.a((ul) this.c.j.w);
        } catch (RemoteException e) {
            adn.d("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", e);
        }
    }

    private void D() {
        try {
            if (!(this.c.j.w instanceof um) || this.c.s == null) {
                return;
            }
            this.c.s.a((um) this.c.j.w);
        } catch (RemoteException e) {
            adn.d("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
        }
    }

    private void E() {
        if (this.c.j != null) {
            if (this.c.x == 0) {
                this.c.j.b.destroy();
            }
            this.c.j = null;
            this.c.y = false;
        }
    }

    private abg a(av avVar, Bundle bundle) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo = this.c.c.getApplicationInfo();
        try {
            packageInfo = this.c.c.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        Bundle bundle2 = null;
        if (!this.c.i.e && this.c.a.getParent() != null) {
            int[] iArr = new int[2];
            this.c.a.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            DisplayMetrics displayMetrics = this.c.c.getResources().getDisplayMetrics();
            int width = this.c.a.getWidth();
            int height = this.c.a.getHeight();
            int i3 = 0;
            if (this.c.a.isShown() && i + width > 0 && i2 + height > 0 && i <= displayMetrics.widthPixels && i2 <= displayMetrics.heightPixels) {
                i3 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt("x", i);
            bundle2.putInt("y", i2);
            bundle2.putInt("width", width);
            bundle2.putInt("height", height);
            bundle2.putInt("visible", i3);
        }
        String c = acl.c();
        this.c.l = new acj(c, this.c.b);
        this.c.l.a(avVar);
        return new abg(bundle2, avVar, this.c.i, this.c.b, applicationInfo, packageInfo, c, acl.a, this.c.e, acl.a(this.c.c, this, c), this.c.t, bundle, acl.i());
    }

    private gv a(ajx ajxVar) {
        gv a;
        if (this.c.i.e) {
            gv a2 = gv.a(this.c.c, this.c.i, false, false, this.c.d, this.c.e);
            a2.f().a(this, null, this, this, true, this, this, ajxVar);
            return a2;
        }
        View nextView = this.c.a.getNextView();
        if (nextView instanceof gv) {
            a = (gv) nextView;
            a.a(this.c.c, this.c.i);
        } else {
            if (nextView != null) {
                this.c.a.removeView(nextView);
            }
            a = gv.a(this.c.c, this.c.i, false, false, this.c.d, this.c.e);
            if (this.c.i.h == null) {
                b(a);
            }
        }
        a.f().a(this, this, this, this, false, this, ajxVar);
        return a;
    }

    private void a(int i) {
        adn.e("Failed to load ad: " + i);
        if (this.c.f != null) {
            try {
                this.c.f.a(i);
            } catch (RemoteException e) {
                adn.d("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
    }

    private void b(View view) {
        this.c.a.addView(view, new ViewGroup.LayoutParams(-2, -2));
    }

    private void b(boolean z) {
        if (this.c.j == null) {
            adn.e("Ad state was null when trying to ping impression URLs.");
            return;
        }
        adn.a("Pinging Impression URLs.");
        this.c.l.a();
        if (this.c.j.e != null) {
            acx.a(this.c.c, this.c.e.b, this.c.j.e);
        }
        if (this.c.j.o != null && this.c.j.o.d != null) {
            wg.a(this.c.c, this.c.e.b, this.c.j, this.c.b, z, this.c.j.o.d);
        }
        if (this.c.j.l == null || this.c.j.l.f == null) {
            return;
        }
        wg.a(this.c.c, this.c.e.b, this.c.j, this.c.b, z, this.c.j.l.f);
    }

    private boolean b(acg acgVar) {
        if (acgVar.k) {
            try {
                View view = (View) og.a(acgVar.m.a());
                View nextView = this.c.a.getNextView();
                if (nextView != null) {
                    this.c.a.removeView(nextView);
                }
                try {
                    b(view);
                } catch (Throwable th) {
                    adn.d("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            } catch (RemoteException e) {
                adn.d("Could not get View from mediation adapter.", e);
                return false;
            }
        } else if (acgVar.r != null) {
            acgVar.b.a(acgVar.r);
            this.c.a.removeAllViews();
            this.c.a.setMinimumWidth(acgVar.r.g);
            this.c.a.setMinimumHeight(acgVar.r.d);
            b(acgVar.b);
        }
        if (this.c.a.getChildCount() > 1) {
            this.c.a.showNext();
        }
        if (this.c.j != null) {
            View nextView2 = this.c.a.getNextView();
            if (nextView2 instanceof gv) {
                ((gv) nextView2).a(this.c.c, this.c.i);
            } else if (nextView2 != null) {
                this.c.a.removeView(nextView2);
            }
            if (this.c.j.m != null) {
                try {
                    this.c.j.m.c();
                } catch (RemoteException e2) {
                    adn.e("Could not destroy previous mediation adapter.");
                }
            }
        }
        this.c.a.setVisibility(0);
        return true;
    }

    private void w() {
        if (Build.VERSION.SDK_INT < 14 || this.c == null || this.c.c == null) {
            return;
        }
        this.c.c.registerComponentCallbacks(this.g);
    }

    private void x() {
        if (Build.VERSION.SDK_INT < 14 || this.c == null || this.c.c == null) {
            return;
        }
        this.c.c.unregisterComponentCallbacks(this.g);
    }

    private void y() {
        adn.c("Ad closing.");
        if (this.c.f != null) {
            try {
                this.c.f.a();
            } catch (RemoteException e) {
                adn.d("Could not call AdListener.onAdClosed().", e);
            }
        }
    }

    private void z() {
        adn.c("Ad leaving application.");
        if (this.c.f != null) {
            try {
                this.c.f.b();
            } catch (RemoteException e) {
                adn.d("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
    }

    Bundle a(sy syVar) {
        String str;
        if (syVar == null) {
            return null;
        }
        if (syVar.e()) {
            syVar.c();
        }
        sv b = syVar.b();
        if (b != null) {
            str = b.b();
            adn.a("In AdManger: loadAd, " + b.toString());
        } else {
            str = null;
        }
        if (str == null) {
            return null;
        }
        Bundle bundle = new Bundle(1);
        bundle.putString("fingerprint", str);
        return bundle;
    }

    @Override // defpackage.rw
    public void a() {
        if (this.c.j == null) {
            adn.e("Ad state was null when trying to ping click URLs.");
            return;
        }
        adn.a("Pinging click URLs.");
        this.c.l.b();
        if (this.c.j.c != null) {
            acx.a(this.c.c, this.c.e.b, this.c.j.c);
        }
        if (this.c.j.o == null || this.c.j.o.c == null) {
            return;
        }
        wg.a(this.c.c, this.c.e.b, this.c.j, this.c.b, false, this.c.j.o.c);
    }

    @Override // defpackage.aav
    public void a(acg acgVar) {
        int i;
        int i2;
        adc adcVar;
        this.c.h = null;
        boolean z = acgVar.w != null;
        if (acgVar.d != -2 && acgVar.d != 3) {
            acl.a(this.c.a());
        }
        if (acgVar.d == -1) {
            return;
        }
        if (a(acgVar, z)) {
            adn.a("Ad refresh scheduled.");
        }
        if (acgVar.d == 3 && acgVar.o != null && acgVar.o.e != null) {
            adn.a("Pinging no fill URLs.");
            wg.a(this.c.c, this.c.e.b, acgVar, this.c.b, false, acgVar.o.e);
        }
        if (acgVar.d != -2) {
            a(acgVar.d);
            return;
        }
        if (!this.c.i.e && !z && this.c.x == 0) {
            if (!b(acgVar)) {
                a(0);
                return;
            } else if (this.c.a != null) {
                adcVar = this.c.a.a;
                adcVar.a(acgVar.v);
            }
        }
        if (this.c.j != null && this.c.j.p != null) {
            this.c.j.p.a((wa) null);
        }
        if (acgVar.p != null) {
            acgVar.p.a((wa) this);
        }
        this.e.b(this.c.j);
        this.c.j = acgVar;
        this.c.l.a(acgVar.t);
        this.c.l.b(acgVar.u);
        this.c.l.a(this.c.i.e);
        this.c.l.b(acgVar.k);
        if (!this.c.i.e && !z && this.c.x == 0) {
            b(false);
        }
        if (this.c.v == null) {
            this.c.v = new aco(this.c.b);
        }
        if (acgVar.o != null) {
            i2 = acgVar.o.h;
            i = acgVar.o.i;
        } else {
            i = 0;
            i2 = 0;
        }
        this.c.v.a(i2, i);
        if (this.c.x != 0) {
            if (this.c.w == null || acgVar.j == null) {
                return;
            }
            this.e.a(this.c.c, this.c.i, this.c.j, this.c.w, this.c.e);
            return;
        }
        if (!this.c.i.e && acgVar.b != null && (acgVar.b.f().b() || acgVar.j != null)) {
            sd a = this.e.a(this.c.i, this.c.j);
            if (acgVar.b.f().b() && a != null) {
                a.a(new akc(acgVar.b));
            }
        }
        if (this.c.j.b != null) {
            this.c.j.b.a();
            this.c.j.b.f().c();
        }
        if (z) {
            uo uoVar = acgVar.w;
            if ((uoVar instanceof um) && this.c.s != null) {
                D();
            } else {
                if (!(uoVar instanceof ul) || this.c.r == null) {
                    adn.e("No matching listener for retrieved native ad template.");
                    a(0);
                    return;
                }
                C();
            }
        }
        B();
    }

    @Override // defpackage.aap
    public void a(ach achVar) {
        gv gvVar;
        this.c.g = null;
        this.c.k = achVar;
        a((List<String>) null);
        if (achVar.b.t) {
            gvVar = null;
        } else {
            ajx ajxVar = new ajx();
            gvVar = a(ajxVar);
            ajxVar.a(new ajz(achVar, gvVar));
            gvVar.setOnTouchListener(new ajt(this, ajxVar));
            gvVar.setOnClickListener(new aju(this, ajxVar));
        }
        if (achVar.d != null) {
            this.c.i = achVar.d;
        }
        if (achVar.e != -2) {
            a(new acg(achVar, gvVar, null, null, null, null, null));
            return;
        }
        if (!achVar.b.h && achVar.b.s) {
            String uri = achVar.b.b != null ? Uri.parse(achVar.b.b).buildUpon().query(null).build().toString() : null;
            zk zkVar = new zk(this, uri, achVar.b.c);
            try {
                if (this.c.p != null && !this.c.i.e && this.c.p.a(uri, achVar.b.c)) {
                    this.c.x = 1;
                    this.c.p.a(zkVar);
                    return;
                }
            } catch (RemoteException e) {
                adn.d("Could not call the rawHtmlPublisherAdViewListener.", e);
            }
            try {
                if (this.c.q != null && this.c.i.e && this.c.q.a(uri, achVar.b.c)) {
                    this.c.x = 1;
                    this.c.q.a(zkVar);
                    return;
                }
            } catch (RemoteException e2) {
                adn.d("Could not call the RawHtmlPublisherInterstitialAdListener.", e2);
            }
        }
        this.c.x = 0;
        this.c.h = aau.a(this.c.c, this, achVar, gvVar, this.b, this);
    }

    @Override // defpackage.rw
    public void a(View view) {
        this.c.w = view;
        a(new acg(this.c.k, null, null, null, null, null, null));
    }

    @Override // defpackage.tu
    public void a(ay ayVar) {
        kb.b("setAdSize must be called on the main UI thread.");
        this.c.i = ayVar;
        if (this.c.j != null && this.c.x == 0) {
            this.c.j.b.a(ayVar);
        }
        if (this.c.a.getChildCount() > 1) {
            this.c.a.removeView(this.c.a.getNextView());
        }
        this.c.a.setMinimumWidth(ayVar.g);
        this.c.a.setMinimumHeight(ayVar.d);
        this.c.a.requestLayout();
    }

    @Override // defpackage.uw
    public void a(String str, String str2) {
        if (this.c.m != null) {
            try {
                this.c.m.a(str, str2);
            } catch (RemoteException e) {
                adn.d("Could not call the AppEventListener.", e);
            }
        }
    }

    @Override // defpackage.vh
    public void a(String str, ArrayList<String> arrayList) {
        ye yeVar = new ye(str, arrayList, this.c.c, this.c.e.b);
        if (this.c.o != null) {
            try {
                this.c.o.a(yeVar);
                return;
            } catch (RemoteException e) {
                adn.e("Could not start In-App purchase.");
                return;
            }
        }
        adn.e("InAppPurchaseListener is not set. Try to launch default purchase flow.");
        if (hb.a(this.c.c) != 0) {
            adn.e("Google Play Service unavailable, cannot launch default purchase flow.");
            return;
        }
        if (this.c.n == null) {
            adn.e("PlayStorePurchaseListener is not set.");
            return;
        }
        if (this.c.u == null) {
            adn.e("PlayStorePurchaseVerifier is not initialized.");
            return;
        }
        try {
            if (!this.c.n.a(str)) {
                return;
            }
        } catch (RemoteException e2) {
            adn.e("Could not start In-App purchase.");
        }
        yf.a(this.c.c, this.c.e.e, new dv(yeVar, this.c.n, this.c.u, this.c.c));
    }

    @Override // defpackage.acn
    public void a(HashSet<acj> hashSet) {
        this.c.a(hashSet);
    }

    public void a(List<String> list) {
        kb.b("setNativeTemplates must be called on the main UI thread.");
        this.c.t = list;
    }

    @Override // defpackage.tu
    public void a(tr trVar) {
        kb.b("setAdListener must be called on the main UI thread.");
        this.c.f = trVar;
    }

    @Override // defpackage.tu
    public void a(ua uaVar) {
        kb.b("setAppEventListener must be called on the main UI thread.");
        this.c.m = uaVar;
    }

    @Override // defpackage.tu
    public void a(yr yrVar) {
        kb.b("setInAppPurchaseListener must be called on the main UI thread.");
        this.c.o = yrVar;
    }

    @Override // defpackage.tu
    public void a(zd zdVar, String str) {
        kb.b("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.c.u = new ym(str);
        this.c.n = zdVar;
        if (acl.g() || zdVar == null) {
            return;
        }
        new yc(this.c.c, this.c.n, this.c.u).e();
    }

    @Override // defpackage.tu
    public void a(zo zoVar) {
        kb.b("setRawHtmlPublisherAdViewListener must be called on the main UI thread.");
        this.c.p = zoVar;
    }

    @Override // defpackage.tu
    public void a(zr zrVar) {
        kb.b("setRawHtmlPublisherInterstitialAdListener must be called on the main UI thread.");
        this.c.q = zrVar;
    }

    @Override // defpackage.vj
    public void a(boolean z) {
        this.c.y = z;
    }

    boolean a(acg acgVar, boolean z) {
        av avVar;
        boolean z2 = false;
        if (this.a != null) {
            avVar = this.a;
            this.a = null;
        } else {
            avVar = acgVar.a;
            if (avVar.c != null) {
                z2 = avVar.c.getBoolean("_noRefresh", false);
            }
        }
        boolean z3 = z2 | z;
        if (this.c.i.e) {
            if (this.c.x == 0) {
                acx.a(acgVar.b);
            }
        } else if (!z3 && this.c.x == 0) {
            if (acgVar.h > 0) {
                this.d.a(avVar, acgVar.h);
            } else if (acgVar.o != null && acgVar.o.g > 0) {
                this.d.a(avVar, acgVar.o.g);
            } else if (!acgVar.k && acgVar.d == 2) {
                this.d.a(avVar);
            }
        }
        return this.d.d();
    }

    @Override // defpackage.tu
    public boolean a(av avVar) {
        kb.b("loadAd must be called on the main UI thread.");
        if (this.c.g != null || this.c.h != null) {
            if (this.a != null) {
                adn.e("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
            }
            this.a = avVar;
            return false;
        }
        if (this.c.i.e && this.c.j != null) {
            adn.e("An interstitial is already loading. Aborting.");
            return false;
        }
        if (!v()) {
            return false;
        }
        adn.c("Starting ad request.");
        if (!avVar.f) {
            adn.c("Use AdRequest.Builder.addTestDevice(\"" + adm.a(this.c.c) + "\") to get test ads on this device.");
        }
        Bundle a = a(acl.a().a(this.c.c));
        this.d.a();
        this.c.x = 0;
        this.c.g = aao.a(this.c.c, a(avVar, a), this.c.d, this);
        return true;
    }

    @Override // defpackage.rw
    public void b() {
        b(false);
    }

    public void b(av avVar) {
        Object parent = this.c.a.getParent();
        if ((parent instanceof View) && ((View) parent).isShown() && acx.a() && !this.f) {
            a(avVar);
        } else {
            adn.c("Ad is not visible. Not refreshing ad.");
            this.d.a(avVar);
        }
    }

    @Override // defpackage.tu
    public od c() {
        kb.b("getAdFrame must be called on the main UI thread.");
        return og.a(this.c.a);
    }

    @Override // defpackage.tu
    public void d() {
        kb.b("destroy must be called on the main UI thread.");
        x();
        this.c.f = null;
        this.c.m = null;
        this.c.n = null;
        this.c.o = null;
        this.c.p = null;
        this.c.q = null;
        this.d.a();
        this.e.a();
        i();
        if (this.c.a != null) {
            this.c.a.removeAllViews();
        }
        if (this.c.j != null && this.c.j.b != null) {
            this.c.j.b.destroy();
        }
        if (this.c.j == null || this.c.j.m == null) {
            return;
        }
        try {
            this.c.j.m.c();
        } catch (RemoteException e) {
            adn.e("Could not destroy mediation adapter.");
        }
    }

    @Override // defpackage.tu
    public boolean e() {
        kb.b("isLoaded must be called on the main UI thread.");
        return this.c.g == null && this.c.h == null && this.c.j != null;
    }

    @Override // defpackage.tu
    public void f() {
        kb.b("pause must be called on the main UI thread.");
        if (this.c.j != null && this.c.x == 0) {
            acx.a(this.c.j.b);
        }
        if (this.c.j != null && this.c.j.m != null) {
            try {
                this.c.j.m.d();
            } catch (RemoteException e) {
                adn.e("Could not pause mediation adapter.");
            }
        }
        this.e.b();
        this.d.b();
    }

    @Override // defpackage.tu
    public void g() {
        kb.b("resume must be called on the main UI thread.");
        if (this.c.j != null && this.c.x == 0) {
            acx.b(this.c.j.b);
        }
        if (this.c.j != null && this.c.j.m != null) {
            try {
                this.c.j.m.e();
            } catch (RemoteException e) {
                adn.e("Could not resume mediation adapter.");
            }
        }
        this.d.c();
        this.e.c();
    }

    @Override // defpackage.tu
    public void h() {
        kb.b("showInterstitial must be called on the main UI thread.");
        if (!this.c.i.e) {
            adn.e("Cannot call showInterstitial on a banner ad.");
            return;
        }
        if (this.c.j == null) {
            adn.e("The interstitial has not loaded.");
            return;
        }
        if (this.c.x != 1) {
            if (this.c.j.b.j()) {
                adn.e("The interstitial is already showing.");
                return;
            }
            this.c.j.b.a(true);
            if (this.c.j.b.f().b() || this.c.j.j != null) {
                sd a = this.e.a(this.c.i, this.c.j);
                if (this.c.j.b.f().b() && a != null) {
                    a.a(new akc(this.c.j.b));
                }
            }
            if (this.c.j.k) {
                try {
                    this.c.j.m.b();
                    return;
                } catch (RemoteException e) {
                    adn.d("Could not show interstitial.", e);
                    E();
                    return;
                }
            }
            x xVar = new x(this.c.y, false);
            if (this.c.c instanceof Activity) {
                Window window = ((Activity) this.c.c).getWindow();
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                window.getDecorView().getGlobalVisibleRect(rect);
                window.getDecorView().getWindowVisibleDisplayFrame(rect2);
                if (rect.bottom != 0 && rect2.bottom != 0) {
                    xVar = new x(this.c.y, rect.top == rect2.top);
                }
            }
            dk.a(this.c.c, new dm(this, this, this, this.c.j.b, this.c.j.g, this.c.e, this.c.j.v, xVar));
        }
    }

    @Override // defpackage.tu
    public void i() {
        kb.b("stopLoading must be called on the main UI thread.");
        if (this.c.j != null && this.c.x == 0) {
            this.c.j.b.stopLoading();
            this.c.j = null;
        }
        if (this.c.g != null) {
            this.c.g.f();
        }
        if (this.c.h != null) {
            this.c.h.f();
        }
    }

    @Override // defpackage.tu
    public void j() {
        kb.b("recordManualImpression must be called on the main UI thread.");
        if (this.c.j == null) {
            adn.e("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        adn.a("Pinging manual tracking URLs.");
        if (this.c.j.f != null) {
            acx.a(this.c.c, this.c.e.b, this.c.j.f);
        }
    }

    @Override // defpackage.tu
    public ay k() {
        kb.b("getAdSize must be called on the main UI thread.");
        return this.c.i;
    }

    @Override // defpackage.tu
    public String l() {
        if (this.c.j != null) {
            return this.c.j.n;
        }
        return null;
    }

    @Override // defpackage.wa
    public void m() {
        u();
    }

    @Override // defpackage.wa
    public void n() {
        r();
    }

    @Override // defpackage.wa
    public void o() {
        t();
    }

    @Override // defpackage.wa
    public void p() {
        s();
    }

    @Override // defpackage.wa
    public void q() {
        if (this.c.j != null) {
            adn.e("Mediation adapter " + this.c.j.n + " refreshed, but mediation adapters should never refresh.");
        }
        b(true);
        B();
    }

    @Override // defpackage.xq
    public void r() {
        this.e.b(this.c.j);
        if (this.c.i.e) {
            E();
        }
        this.f = false;
        y();
        this.c.l.c();
    }

    @Override // defpackage.xq
    public void s() {
        if (this.c.i.e) {
            b(false);
        }
        this.f = true;
        A();
    }

    @Override // defpackage.xt
    public void t() {
        z();
    }

    @Override // defpackage.ajq
    public void u() {
        a();
    }

    public boolean v() {
        boolean z = true;
        if (!acx.a(this.c.c.getPackageManager(), this.c.c.getPackageName(), "android.permission.INTERNET")) {
            if (!this.c.i.e) {
                adm.a(this.c.a, this.c.i, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            }
            z = false;
        }
        if (!acx.a(this.c.c)) {
            if (!this.c.i.e) {
                adm.a(this.c.a, this.c.i, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            }
            z = false;
        }
        if (!z && !this.c.i.e) {
            this.c.a.setVisibility(0);
        }
        return z;
    }
}
