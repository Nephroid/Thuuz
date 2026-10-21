package defpackage;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gt;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class sd implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long m = TimeUnit.MILLISECONDS.toNanos(100);
    private final Object a;
    private final WeakReference<acg> b;
    private WeakReference<ViewTreeObserver> c;
    private final WeakReference<View> d;
    private final sb e;
    private final Context f;
    private final sm g;
    private boolean h;
    private final WindowManager i;
    private final PowerManager j;
    private final KeyguardManager k;
    private sl l;
    private boolean n;
    private boolean o;
    private final BlockingQueue<Runnable> p;
    private long q;
    private boolean r;
    private boolean s;
    private BroadcastReceiver t;
    private final HashSet<sa> u;

    public sd(Context context, ay ayVar, acg acgVar, View view, gt gtVar) {
        this(ayVar, acgVar, gtVar, view, new sr(context, gtVar));
    }

    public sd(ay ayVar, acg acgVar, gt gtVar, View view, sm smVar) {
        this.a = new Object();
        this.n = false;
        this.o = false;
        this.p = new ArrayBlockingQueue(2);
        this.q = Long.MIN_VALUE;
        this.u = new HashSet<>();
        this.b = new WeakReference<>(acgVar);
        this.d = new WeakReference<>(view);
        this.c = new WeakReference<>(null);
        this.r = true;
        this.e = new sb(UUID.randomUUID().toString(), gtVar, ayVar.b, acgVar.j);
        this.g = smVar;
        this.i = (WindowManager) view.getContext().getSystemService("window");
        this.j = (PowerManager) view.getContext().getApplicationContext().getSystemService("power");
        this.k = (KeyguardManager) view.getContext().getSystemService("keyguard");
        this.f = view.getContext().getApplicationContext();
        a(smVar);
        this.g.a(new se(this, view));
        b(this.g);
        try {
            this.p.add(new sf(this, b(view)));
        } catch (Throwable th) {
        }
        this.p.add(new sg(this));
        adn.a("Tracking ad unit: " + this.e.d());
    }

    protected int a(int i, DisplayMetrics displayMetrics) {
        return (int) (i / displayMetrics.density);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        synchronized (this.a) {
            if (this.t != null) {
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.t = new sh(this);
            this.f.registerReceiver(this.t, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(View view) {
        ArrayList arrayList = new ArrayList();
        this.p.drainTo(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(View view, Map<String, String> map) {
        b(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONArray.put(jSONObject);
            jSONObject2.put("units", jSONArray);
            this.g.a("AFMA_updateActiveView", jSONObject2);
        } catch (Throwable th) {
            adn.b("Skipping active view message.", th);
        }
    }

    public void a(sa saVar) {
        this.u.add(saVar);
    }

    public void a(sl slVar) {
        synchronized (this.a) {
            this.l = slVar;
        }
    }

    protected void a(sm smVar) {
        smVar.a("https://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.html");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(boolean z) {
        Iterator<sa> it = this.u.iterator();
        while (it.hasNext()) {
            it.next().a(this, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.e.d());
    }

    protected JSONObject b(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        view.getLocationInWindow(new int[2]);
        JSONObject i = i();
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.i.getDefaultDisplay().getWidth();
        rect2.bottom = this.i.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, null);
        Rect rect4 = new Rect();
        i.put("viewBox", new JSONObject().put("top", a(rect2.top, displayMetrics)).put("bottom", a(rect2.bottom, displayMetrics)).put("left", a(rect2.left, displayMetrics)).put("right", a(rect2.right, displayMetrics))).put("adBox", new JSONObject().put("top", a(rect.top, displayMetrics)).put("bottom", a(rect.bottom, displayMetrics)).put("left", a(rect.left, displayMetrics)).put("right", a(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put("top", a(rect3.top, displayMetrics)).put("bottom", a(rect3.bottom, displayMetrics)).put("left", a(rect3.left, displayMetrics)).put("right", a(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", a(rect4.top, displayMetrics)).put("bottom", a(rect4.bottom, displayMetrics)).put("left", a(rect4.left, displayMetrics)).put("right", a(rect4.right, displayMetrics))).put("localVisibleBoxVisible", view.getLocalVisibleRect(rect4)).put("screenDensity", displayMetrics.density).put("isVisible", c(view)).put("isStopped", this.o).put("isPaused", this.n);
        return i;
    }

    protected void b() {
        synchronized (this.a) {
            if (this.t != null) {
                this.f.unregisterReceiver(this.t);
                this.t = null;
            }
        }
    }

    protected void b(sm smVar) {
        smVar.a("/updateActiveView", new si(this));
        smVar.a("/untrackActiveViewUnit", new sj(this));
        smVar.a("/visibilityChanged", new sk(this));
        smVar.a("/viewabilityChanged", ux.a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(boolean z) {
        synchronized (this.a) {
            if (this.h && this.r) {
                long nanoTime = System.nanoTime();
                if (!z || this.q + m <= nanoTime) {
                    this.q = nanoTime;
                    acg acgVar = this.b.get();
                    View view = this.d.get();
                    if (view == null || acgVar == null) {
                        d();
                        return;
                    }
                    try {
                        a(b(view));
                    } catch (JSONException e) {
                        adn.a("Active view update failed.", e);
                    }
                    g();
                    e();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c() {
        synchronized (this.a) {
            h();
            b();
            this.r = false;
            try {
                this.g.c();
            } catch (Throwable th) {
            }
            e();
        }
    }

    protected boolean c(View view) {
        return view.getVisibility() == 0 && view.isShown() && this.j.isScreenOn() && !this.k.inKeyguardRestrictedInputMode();
    }

    public void d() {
        synchronized (this.a) {
            if (this.r) {
                this.s = true;
                try {
                    a(j());
                } catch (JSONException e) {
                    adn.b("JSON Failure while processing active view data.", e);
                }
                adn.a("Untracking ad unit: " + this.e.d());
            }
        }
    }

    protected void e() {
        if (this.l != null) {
            this.l.a(this);
        }
    }

    public boolean f() {
        boolean z;
        synchronized (this.a) {
            z = this.r;
        }
        return z;
    }

    protected void g() {
        View view = this.d.get();
        if (view == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = this.c.get();
        ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
        if (viewTreeObserver2 != viewTreeObserver) {
            this.c = new WeakReference<>(viewTreeObserver2);
            viewTreeObserver2.addOnScrollChangedListener(this);
            viewTreeObserver2.addOnGlobalLayoutListener(this);
        }
    }

    protected void h() {
        ViewTreeObserver viewTreeObserver = this.c.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener(this);
        viewTreeObserver.removeGlobalOnLayoutListener(this);
    }

    protected JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.e.b()).put("activeViewJSON", this.e.c()).put("timestamp", TimeUnit.NANOSECONDS.toMillis(System.nanoTime())).put("adFormat", this.e.a()).put("hashCode", this.e.d());
        return jSONObject;
    }

    protected JSONObject j() {
        JSONObject i = i();
        i.put("doneReasonCode", "u");
        return i;
    }

    public void k() {
        synchronized (this.a) {
            this.o = true;
            b(false);
            this.g.a();
        }
    }

    public void l() {
        synchronized (this.a) {
            this.n = true;
            b(false);
            this.g.a();
        }
    }

    public void m() {
        synchronized (this.a) {
            this.g.b();
            this.n = false;
            b(false);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        b(false);
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        b(true);
    }
}
