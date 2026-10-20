package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import defpackage.acx;
import defpackage.adf;
import defpackage.adn;
import defpackage.xk;
import defpackage.xm;
import defpackage.xn;
import defpackage.xo;
import defpackage.xv;
import defpackage.zy;
import org.apache.http.protocol.HTTP;

@zy
/* loaded from: classes.dex */
public class dk extends xv {
    private static final int a = Color.argb(0, 0, 0, 0);
    private final Activity b;
    private dm c;
    private Cdo d;
    private gv e;
    private xo f;
    private dp g;
    private boolean h;
    private FrameLayout j;
    private WebChromeClient.CustomViewCallback k;
    private RelativeLayout o;
    private boolean i = false;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;

    @zy
    /* loaded from: classes.dex */
    public final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    public dk(Activity activity) {
        this.b = activity;
    }

    public static void a(Context context, dm dmVar) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", dmVar.n.e);
        dm.a(intent, dmVar);
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    private static RelativeLayout.LayoutParams c(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        return layoutParams;
    }

    public void a() {
        this.b.finish();
    }

    public void a(int i) {
        this.b.setRequestedOrientation(i);
    }

    public void a(int i, int i2, int i3, int i4) {
        if (this.d != null) {
            this.d.setLayoutParams(c(i, i2, i3, i4));
        }
    }

    @Override // defpackage.xu
    public void a(Bundle bundle) {
        this.l = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            this.c = dm.a(this.b.getIntent());
            if (this.c == null) {
                throw new a("Could not get info for ad overlay.");
            }
            if (this.c.q != null) {
                this.n = this.c.q.b;
            } else {
                this.n = false;
            }
            if (bundle == null) {
                if (this.c.d != null) {
                    this.c.d.s();
                }
                if (this.c.l != 1 && this.c.c != null) {
                    this.c.c.u();
                }
            }
            switch (this.c.l) {
                case 1:
                    c(false);
                    return;
                case 2:
                    this.f = new xo(this.c.e);
                    c(false);
                    return;
                case 3:
                    c(true);
                    return;
                case 4:
                    if (this.l) {
                        this.b.finish();
                        return;
                    } else {
                        if (xk.a(this.b, this.c.b, this.c.j)) {
                            return;
                        }
                        this.b.finish();
                        return;
                    }
                default:
                    throw new a("Could not determine ad overlay type.");
            }
        } catch (a e) {
            adn.e(e.getMessage());
            this.b.finish();
        }
    }

    public void a(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.j = new FrameLayout(this.b);
        this.j.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.j.addView(view, -1, -1);
        this.b.setContentView(this.j);
        j();
        this.k = customViewCallback;
        this.i = true;
    }

    public void a(boolean z) {
        this.g = new dp(this.b, z ? 50 : 32);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.g.a(this.c.h);
        this.o.addView(this.g, layoutParams);
    }

    public Cdo b() {
        return this.d;
    }

    public void b(int i, int i2, int i3, int i4) {
        if (this.d == null) {
            this.d = new Cdo(this.b, this.e);
            this.o.addView(this.d, 0, c(i, i2, i3, i4));
            this.e.f().a(false);
        }
    }

    @Override // defpackage.xu
    public void b(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.l);
    }

    public void b(boolean z) {
        if (this.g != null) {
            this.g.a(z);
        }
    }

    public void c() {
        if (this.c != null && this.i) {
            a(this.c.k);
        }
        if (this.j != null) {
            this.b.setContentView(this.o);
            j();
            this.j.removeAllViews();
            this.j = null;
        }
        if (this.k != null) {
            this.k.onCustomViewHidden();
            this.k = null;
        }
        this.i = false;
    }

    void c(boolean z) {
        if (!this.h) {
            this.b.requestWindowFeature(1);
        }
        Window window = this.b.getWindow();
        if (!this.n || this.c.q.c) {
            window.setFlags(1024, 1024);
        }
        a(this.c.k);
        if (Build.VERSION.SDK_INT >= 11) {
            adn.a("Enabling hardware acceleration on the AdActivity window.");
            adf.a(window);
        }
        this.o = new xn(this.b, this.c.p);
        if (this.n) {
            this.o.setBackgroundColor(a);
        } else {
            this.o.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.b.setContentView(this.o);
        j();
        boolean b = this.c.e.f().b();
        if (z) {
            this.e = gv.a(this.b, this.c.e.e(), true, b, null, this.c.n);
            this.e.f().a(null, null, this.c.f, this.c.j, true, this.c.o, this.c.e.f().a());
            this.e.f().a(new xm(this));
            if (this.c.m != null) {
                this.e.loadUrl(this.c.m);
            } else {
                if (this.c.i == null) {
                    throw new a("No URL or HTML to display in ad overlay.");
                }
                this.e.loadDataWithBaseURL(this.c.g, this.c.i, "text/html", HTTP.UTF_8, null);
            }
        } else {
            this.e = this.c.e;
            this.e.setContext(this.b);
        }
        this.e.a(this);
        ViewParent parent = this.e.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.e);
        }
        if (this.n) {
            this.e.setBackgroundColor(a);
        }
        this.o.addView(this.e, -1, -1);
        if (!z) {
            m();
        }
        a(b);
        if (this.e.g()) {
            b(true);
        }
    }

    @Override // defpackage.xu
    public void d() {
    }

    @Override // defpackage.xu
    public void e() {
    }

    @Override // defpackage.xu
    public void f() {
        if (this.c != null && this.c.l == 4) {
            if (this.l) {
                this.b.finish();
            } else {
                this.l = true;
            }
        }
        if (this.e != null) {
            acx.b(this.e);
        }
    }

    @Override // defpackage.xu
    public void g() {
        if (this.d != null) {
            this.d.c();
        }
        c();
        if (this.e != null && (!this.b.isFinishing() || this.f == null)) {
            acx.a(this.e);
        }
        l();
    }

    @Override // defpackage.xu
    public void h() {
        l();
    }

    @Override // defpackage.xu
    public void i() {
        if (this.d != null) {
            this.d.a();
        }
        if (this.e != null) {
            this.o.removeView(this.e);
        }
        l();
    }

    @Override // defpackage.xu
    public void j() {
        this.h = true;
    }

    public void k() {
        this.o.removeView(this.g);
        a(true);
    }

    void l() {
        if (!this.b.isFinishing() || this.m) {
            return;
        }
        this.m = true;
        if (this.b.isFinishing()) {
            if (this.e != null) {
                n();
                this.o.removeView(this.e);
                if (this.f != null) {
                    this.e.a(false);
                    this.f.c.addView(this.e, this.f.a, this.f.b);
                }
            }
            if (this.c == null || this.c.d == null) {
                return;
            }
            this.c.d.r();
        }
    }

    void m() {
        this.e.c();
    }

    void n() {
        this.e.b();
    }
}
