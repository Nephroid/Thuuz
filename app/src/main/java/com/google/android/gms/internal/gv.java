package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.actionbarsherlock.widget.ActivityChooserView;
import defpackage.acx;
import defpackage.adf;
import defpackage.adk;
import defpackage.adn;
import defpackage.adp;
import defpackage.adq;
import defpackage.adt;
import defpackage.aeb;
import defpackage.aec;
import defpackage.agc;
import defpackage.zy;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class gv extends WebView implements DownloadListener {
    private final adq a;
    private final adp b;
    private final Object c;
    private final agc d;
    private final gt e;
    private dk f;
    private ay g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private final WindowManager l;

    private gv(adp adpVar, ay ayVar, boolean z, boolean z2, agc agcVar, gt gtVar) {
        super(adpVar);
        this.c = new Object();
        this.b = adpVar;
        this.g = ayVar;
        this.h = z;
        this.d = agcVar;
        this.e = gtVar;
        this.l = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        acx.a(adpVar, gtVar.b, settings);
        if (Build.VERSION.SDK_INT >= 17) {
            adk.a(getContext(), settings);
        } else if (Build.VERSION.SDK_INT >= 11) {
            adf.a(getContext(), settings);
        }
        setDownloadListener(this);
        if (Build.VERSION.SDK_INT >= 11) {
            this.a = new aeb(this, z2);
        } else {
            this.a = new adq(this, z2);
        }
        setWebViewClient(this.a);
        if (Build.VERSION.SDK_INT >= 14) {
            setWebChromeClient(new aec(this));
        } else if (Build.VERSION.SDK_INT >= 11) {
            setWebChromeClient(new adt(this));
        }
        m();
    }

    public static gv a(Context context, ay ayVar, boolean z, boolean z2, agc agcVar, gt gtVar) {
        return new gv(new adp(context), ayVar, z, z2, agcVar, gtVar);
    }

    private void m() {
        synchronized (this.c) {
            if (this.h || this.g.e) {
                if (Build.VERSION.SDK_INT < 14) {
                    adn.a("Disabling hardware acceleration on an overlay.");
                    n();
                } else {
                    adn.a("Enabling hardware acceleration on an overlay.");
                    o();
                }
            } else if (Build.VERSION.SDK_INT < 18) {
                adn.a("Disabling hardware acceleration on an AdView.");
                n();
            } else {
                adn.a("Enabling hardware acceleration on an AdView.");
                o();
            }
        }
    }

    private void n() {
        synchronized (this.c) {
            if (!this.i && Build.VERSION.SDK_INT >= 11) {
                adf.a((View) this);
            }
            this.i = true;
        }
    }

    private void o() {
        synchronized (this.c) {
            if (this.i && Build.VERSION.SDK_INT >= 11) {
                adf.b((View) this);
            }
            this.i = false;
        }
    }

    public void a() {
        if (f().b()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = this.l.getDefaultDisplay();
            defaultDisplay.getMetrics(displayMetrics);
            int c = acx.c(getContext());
            float f = 160.0f / displayMetrics.densityDpi;
            try {
                b("onScreenInfoChanged", new JSONObject().put("width", Math.round(displayMetrics.widthPixels * f)).put("height", Math.round((displayMetrics.heightPixels - c) * f)).put("density", displayMetrics.density).put("rotation", defaultDisplay.getRotation()));
            } catch (JSONException e) {
                adn.b("Error occured while obtaining screen information.", e);
            }
        }
    }

    public void a(Context context, ay ayVar) {
        synchronized (this.c) {
            this.b.setBaseContext(context);
            this.f = null;
            this.g = ayVar;
            this.h = false;
            this.k = false;
            acx.b(this);
            loadUrl("about:blank");
            this.a.d();
            setOnTouchListener(null);
            setOnClickListener(null);
        }
    }

    public void a(ay ayVar) {
        synchronized (this.c) {
            this.g = ayVar;
            requestLayout();
        }
    }

    public void a(dk dkVar) {
        synchronized (this.c) {
            this.f = dkVar;
        }
    }

    protected void a(String str) {
        synchronized (this.c) {
            if (l()) {
                adn.e("The webview is destroyed. Ignoring action.");
            } else {
                loadUrl(str);
            }
        }
    }

    public void a(String str, Map<String, ?> map) {
        try {
            b(str, acx.a(map));
        } catch (JSONException e) {
            adn.e("Could not convert parameters to JSON.");
        }
    }

    public void a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:" + str + "(");
        sb.append(jSONObject2);
        sb.append(");");
        a(sb.toString());
    }

    public void a(boolean z) {
        synchronized (this.c) {
            this.h = z;
            m();
        }
    }

    public void b() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.e.b);
        a("onhide", hashMap);
    }

    public void b(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:AFMA_ReceiveMessage('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        adn.d("Dispatching AFMA event: " + ((Object) sb));
        a(sb.toString());
    }

    public void b(boolean z) {
        synchronized (this.c) {
            if (this.f != null) {
                this.f.b(z);
            } else {
                this.k = z;
            }
        }
    }

    public void c() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.e.b);
        a("onshow", hashMap);
    }

    public dk d() {
        dk dkVar;
        synchronized (this.c) {
            dkVar = this.f;
        }
        return dkVar;
    }

    @Override // android.webkit.WebView
    public void destroy() {
        synchronized (this.c) {
            super.destroy();
            this.j = true;
        }
    }

    public ay e() {
        ay ayVar;
        synchronized (this.c) {
            ayVar = this.g;
        }
        return ayVar;
    }

    @Override // android.webkit.WebView
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        synchronized (this.c) {
            if (!l()) {
                super.evaluateJavascript(str, valueCallback);
                return;
            }
            adn.e("The webview is destroyed. Ignoring action.");
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
        }
    }

    public adq f() {
        return this.a;
    }

    public boolean g() {
        return this.k;
    }

    public agc h() {
        return this.d;
    }

    public gt i() {
        return this.e;
    }

    public boolean j() {
        boolean z;
        synchronized (this.c) {
            z = this.h;
        }
        return z;
    }

    public Context k() {
        return this.b.a();
    }

    public boolean l() {
        boolean z;
        synchronized (this.c) {
            z = this.j;
        }
        return z;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            adn.a("Couldn't find an Activity to view url/mimetype: " + str + " / " + str4);
        }
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        synchronized (this.c) {
            if (isInEditMode() || this.h) {
                super.onMeasure(i, i2);
                return;
            }
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            int i4 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i3 = size2;
            }
            if (this.g.g > i4 || this.g.d > i3) {
                float f = this.b.getResources().getDisplayMetrics().density;
                adn.e("Not enough space to show ad. Needs " + ((int) (this.g.g / f)) + "x" + ((int) (this.g.d / f)) + " dp, but only has " + ((int) (size / f)) + "x" + ((int) (size2 / f)) + " dp.");
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
            } else {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                setMeasuredDimension(this.g.g, this.g.d);
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.d != null) {
            this.d.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setContext(Context context) {
        this.b.setBaseContext(context);
    }
}
