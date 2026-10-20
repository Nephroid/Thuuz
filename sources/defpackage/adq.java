package defpackage;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.dj;
import com.google.android.gms.internal.dk;
import com.google.android.gms.internal.dm;
import com.google.android.gms.internal.gv;
import com.google.android.gms.internal.l;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;

@zy
/* loaded from: classes.dex */
public class adq extends WebViewClient {
    protected final gv a;
    private final HashMap<String, vg> b;
    private final Object c;
    private ajq d;
    private xq e;
    private ads f;
    private uw g;
    private boolean h;
    private vh i;
    private vj j;
    private boolean k;
    private xt l;
    private final xj m;
    private ajx n;

    public adq(gv gvVar, boolean z) {
        this(gvVar, z, new xj(gvVar, gvVar.getContext(), new ui(gvVar.getContext())));
    }

    adq(gv gvVar, boolean z, xj xjVar) {
        this.b = new HashMap<>();
        this.c = new Object();
        this.h = false;
        this.a = gvVar;
        this.k = z;
        this.m = xjVar;
    }

    private static boolean a(Uri uri) {
        String scheme = uri.getScheme();
        return HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void b(Uri uri) {
        String path = uri.getPath();
        vg vgVar = this.b.get(path);
        if (vgVar == null) {
            adn.d("No GMSG handler found for GMSG: " + uri);
            return;
        }
        Map<String, String> a = acx.a(uri);
        if (adn.a(2)) {
            adn.d("Received GMSG: " + path);
            for (String str : a.keySet()) {
                adn.d("  " + str + ": " + a.get(str));
            }
        }
        vgVar.a(this.a, a);
    }

    public ajx a() {
        return this.n;
    }

    public final void a(ads adsVar) {
        this.f = adsVar;
    }

    public void a(ajq ajqVar, xq xqVar, uw uwVar, xt xtVar, boolean z, vh vhVar, ajx ajxVar) {
        if (ajxVar == null) {
            ajxVar = new ajx(false);
        }
        a("/appEvent", new uv(uwVar));
        a("/canOpenURLs", ux.b);
        a("/click", ux.c);
        a("/close", ux.d);
        a("/customClose", ux.e);
        a("/httpTrack", ux.f);
        a("/log", ux.g);
        a("/open", new vl(vhVar, ajxVar));
        a("/touch", ux.h);
        a("/video", ux.i);
        a("/mraid", new vk());
        this.d = ajqVar;
        this.e = xqVar;
        this.g = uwVar;
        this.i = vhVar;
        this.l = xtVar;
        this.n = ajxVar;
        a(z);
    }

    public void a(ajq ajqVar, xq xqVar, uw uwVar, xt xtVar, boolean z, vh vhVar, vj vjVar, ajx ajxVar) {
        a(ajqVar, xqVar, uwVar, xtVar, z, vhVar, ajxVar);
        a("/setInterstitialProperties", new vi(vjVar));
        this.j = vjVar;
    }

    public final void a(dj djVar) {
        boolean j = this.a.j();
        a(new dm(djVar, (!j || this.a.e().e) ? this.d : null, j ? null : this.e, this.l, this.a.i()));
    }

    protected void a(dm dmVar) {
        dk.a(this.a.getContext(), dmVar);
    }

    public final void a(String str, vg vgVar) {
        this.b.put(str, vgVar);
    }

    public final void a(boolean z) {
        this.h = z;
    }

    public final void a(boolean z, int i) {
        a(new dm((!this.a.j() || this.a.e().e) ? this.d : null, this.e, this.l, this.a, z, i, this.a.i()));
    }

    public final void a(boolean z, int i, String str) {
        boolean j = this.a.j();
        a(new dm((!j || this.a.e().e) ? this.d : null, j ? null : this.e, this.g, this.l, this.a, z, i, str, this.a.i(), this.i));
    }

    public final void a(boolean z, int i, String str, String str2) {
        boolean j = this.a.j();
        a(new dm((!j || this.a.e().e) ? this.d : null, j ? null : this.e, this.g, this.l, this.a, z, i, str, str2, this.a.i(), this.i));
    }

    public boolean b() {
        boolean z;
        synchronized (this.c) {
            z = this.k;
        }
        return z;
    }

    public void c() {
        if (b()) {
            this.m.b();
        }
    }

    public final void d() {
        synchronized (this.c) {
            this.b.clear();
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = false;
            this.k = false;
            this.i = null;
            this.l = null;
        }
    }

    public final void e() {
        synchronized (this.c) {
            this.h = false;
            this.k = true;
            dk d = this.a.d();
            if (d != null) {
                if (adm.b()) {
                    d.k();
                } else {
                    adm.a.post(new adr(this, d));
                }
            }
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        adn.d("Loading resource: " + str);
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            b(parse);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        if (this.f != null) {
            this.f.a(this.a);
            this.f = null;
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Uri uri;
        adn.d("AdWebView shouldOverrideUrlLoading: " + str);
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            b(parse);
        } else {
            if (this.h && webView == this.a && a(parse)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            if (this.a.willNotDraw()) {
                adn.e("AdWebView unable to handle URL: " + str);
            } else {
                try {
                    agc h = this.a.h();
                    if (h != null && h.b(parse)) {
                        parse = h.a(parse, this.a.getContext());
                    }
                    uri = parse;
                } catch (l e) {
                    adn.e("Unable to append parameter to URL: " + str);
                    uri = parse;
                }
                if (this.n == null || this.n.b()) {
                    a(new dj("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
                } else {
                    this.n.a(str);
                }
            }
        }
        return true;
    }
}
