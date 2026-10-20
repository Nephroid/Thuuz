package defpackage;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.internal.gv;
import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class sy extends Thread {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private final Object d = new Object();
    private final sx e;
    private final sw f;
    private final zx g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;
    private final int l;

    public sy(sx sxVar, sw swVar, Bundle bundle, zx zxVar) {
        this.e = sxVar;
        this.f = swVar;
        this.g = zxVar;
        this.i = bundle.getInt(uk.h.a());
        this.j = bundle.getInt(uk.i.a());
        this.k = bundle.getInt(uk.j.a());
        this.l = bundle.getInt(uk.k.a());
        this.h = bundle.getInt(uk.l.a(), 10);
        setName("ContentFetchTask");
    }

    private void a(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = null;
        if (activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            view = activity.getWindow().getDecorView().findViewById(R.id.content);
        }
        if (view != null) {
            a(view);
        }
    }

    private boolean a(WebView webView, sv svVar) {
        if (!agf.d()) {
            return false;
        }
        svVar.e();
        webView.post(new ta(this, svVar, webView));
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
    
        if (r0.importance != 100) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
    
        if (r1.inKeyguardRestrictedInputMode() != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        if (r2.isScreenOn() == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005d, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean f() {
        /*
            r7 = this;
            r3 = 0
            sx r0 = r7.e     // Catch: java.lang.Throwable -> L61
            android.content.Context r2 = r0.b()     // Catch: java.lang.Throwable -> L61
            if (r2 != 0) goto Lb
            r0 = r3
        La:
            return r0
        Lb:
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r2.getSystemService(r0)     // Catch: java.lang.Throwable -> L61
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0     // Catch: java.lang.Throwable -> L61
            java.lang.String r1 = "keyguard"
            java.lang.Object r1 = r2.getSystemService(r1)     // Catch: java.lang.Throwable -> L61
            android.app.KeyguardManager r1 = (android.app.KeyguardManager) r1     // Catch: java.lang.Throwable -> L61
            java.lang.String r4 = "power"
            java.lang.Object r2 = r2.getSystemService(r4)     // Catch: java.lang.Throwable -> L61
            android.os.PowerManager r2 = (android.os.PowerManager) r2     // Catch: java.lang.Throwable -> L61
            if (r0 == 0) goto L29
            if (r1 == 0) goto L29
            if (r2 != 0) goto L2b
        L29:
            r0 = r3
            goto La
        L2b:
            java.util.List r0 = r0.getRunningAppProcesses()     // Catch: java.lang.Throwable -> L61
            if (r0 != 0) goto L33
            r0 = r3
            goto La
        L33:
            java.util.Iterator r4 = r0.iterator()     // Catch: java.lang.Throwable -> L61
        L37:
            boolean r0 = r4.hasNext()     // Catch: java.lang.Throwable -> L61
            if (r0 == 0) goto L5f
            java.lang.Object r0 = r4.next()     // Catch: java.lang.Throwable -> L61
            android.app.ActivityManager$RunningAppProcessInfo r0 = (android.app.ActivityManager.RunningAppProcessInfo) r0     // Catch: java.lang.Throwable -> L61
            int r5 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L61
            int r6 = r0.pid     // Catch: java.lang.Throwable -> L61
            if (r5 != r6) goto L37
            int r0 = r0.importance     // Catch: java.lang.Throwable -> L61
            r4 = 100
            if (r0 != r4) goto L5f
            boolean r0 = r1.inKeyguardRestrictedInputMode()     // Catch: java.lang.Throwable -> L61
            if (r0 != 0) goto L5f
            boolean r0 = r2.isScreenOn()     // Catch: java.lang.Throwable -> L61
            if (r0 == 0) goto L5f
            r0 = 1
            goto La
        L5f:
            r0 = r3
            goto La
        L61:
            r0 = move-exception
            r0 = r3
            goto La
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sy.f():boolean");
    }

    tc a(View view, sv svVar) {
        if (view == null) {
            return new tc(this, 0, 0);
        }
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            svVar.b(((TextView) view).getText().toString());
            return new tc(this, 1, 0);
        }
        if ((view instanceof WebView) && !(view instanceof gv)) {
            svVar.e();
            return a((WebView) view, svVar) ? new tc(this, 0, 1) : new tc(this, 0, 0);
        }
        if (!(view instanceof ViewGroup)) {
            return new tc(this, 0, 0);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
            tc a = a(viewGroup.getChildAt(i3), svVar);
            i2 += a.a;
            i += a.b;
        }
        return new tc(this, i2, i);
    }

    public void a() {
        synchronized (this.d) {
            if (this.a) {
                adn.a("Content hash thread already started, quiting...");
            } else {
                this.a = true;
                start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(sv svVar, WebView webView, String str) {
        svVar.d();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (TextUtils.isEmpty(webView.getTitle())) {
                    svVar.a(optString);
                } else {
                    svVar.a(webView.getTitle() + "\n" + optString);
                }
            }
            if (svVar.a()) {
                this.f.b(svVar);
            }
        } catch (JSONException e) {
            adn.a("Json string may be malformed.");
        } catch (Throwable th) {
            adn.a("Failed to get webview content.", th);
            this.g.b(th);
        }
    }

    boolean a(View view) {
        if (view == null) {
            return false;
        }
        view.post(new sz(this, view));
        return true;
    }

    public sv b() {
        return this.f.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(View view) {
        try {
            sv svVar = new sv(this.i, this.j, this.k, this.l);
            tc a = a(view, svVar);
            svVar.f();
            if (a.a == 0 && a.b == 0) {
                return;
            }
            if (a.b == 0 && svVar.h() == 0) {
                return;
            }
            if (a.b == 0 && this.f.a(svVar)) {
                return;
            }
            this.f.c(svVar);
        } catch (Exception e) {
            adn.b("Exception in fetchContentOnUIThread", e);
            this.g.b(e);
        }
    }

    public void c() {
        synchronized (this.d) {
            this.b = false;
            this.d.notifyAll();
            adn.a("ContentFetchThread: wakeup");
        }
    }

    public void d() {
        synchronized (this.d) {
            this.b = true;
            adn.a("ContentFetchThread: paused, mPause = " + this.b);
        }
    }

    public boolean e() {
        return this.b;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!this.c) {
            try {
            } catch (Throwable th) {
                adn.b("Error in ContentFetchTask", th);
                this.g.b(th);
            }
            if (f()) {
                Activity a = this.e.a();
                if (a == null) {
                    adn.a("ContentFetchThread: no activity");
                } else {
                    a(a);
                }
            } else {
                adn.a("ContentFetchTask: sleeping");
                d();
            }
            Thread.sleep(this.h * 1000);
            synchronized (this.d) {
                while (this.b) {
                    try {
                        adn.a("ContentFetchTask: waiting");
                        this.d.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }
}
