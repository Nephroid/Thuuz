package defpackage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.analytics.t;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class dz extends el {
    private static boolean a;
    private static dz l;
    private boolean b;
    private fh c;
    private es d;
    private Context e;
    private volatile Boolean f;
    private eh g;
    private String h;
    private String i;
    private Set<ea> j;
    private boolean k;

    protected dz(Context context) {
        this(context, gd.a(context), fu.c());
    }

    private dz(Context context, fh fhVar, es esVar) {
        this.f = false;
        this.k = false;
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        this.e = context.getApplicationContext();
        this.c = fhVar;
        this.d = esVar;
        fi.a(this.e);
        er.a(this.e);
        fj.a(this.e);
        this.g = new fo();
        this.j = new HashSet();
        f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static dz a() {
        dz dzVar;
        synchronized (dz.class) {
            dzVar = l;
        }
        return dzVar;
    }

    public static dz a(Context context) {
        dz dzVar;
        synchronized (dz.class) {
            if (l == null) {
                l = new dz(context);
            }
            dzVar = l;
        }
        return dzVar;
    }

    private ej a(ej ejVar) {
        if (this.i != null) {
            ejVar.a("&an", this.i);
        }
        if (this.h != null) {
            ejVar.a("&av", this.h);
        }
        return ejVar;
    }

    private int b(String str) {
        String lowerCase = str.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        return "error".equals(lowerCase) ? 3 : -1;
    }

    private void f() {
        ApplicationInfo applicationInfo;
        int i;
        gj a2;
        if (a) {
            return;
        }
        try {
            applicationInfo = this.e.getPackageManager().getApplicationInfo(this.e.getPackageName(), 129);
        } catch (PackageManager.NameNotFoundException e) {
            gn.c("PackageManager doesn't know about package: " + e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            gn.d("Couldn't get ApplicationInfo to load gloabl config.");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null || (i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) <= 0 || (a2 = new gh(this.e).a(i)) == null) {
            return;
        }
        a(a2);
    }

    public ej a(String str) {
        ej a2;
        synchronized (this) {
            t.a().a(t.a.GET_TRACKER);
            a2 = a(new ej(str, this, this.e));
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Activity activity) {
        Iterator<ea> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().a(activity);
        }
    }

    public void a(Application application) {
        if (Build.VERSION.SDK_INT < 14 || this.k) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new eb(this));
        this.k = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ea eaVar) {
        this.j.add(eaVar);
        if (this.e instanceof Application) {
            a((Application) this.e);
        }
    }

    void a(gj gjVar) {
        int b;
        gn.c("Loading global config values.");
        if (gjVar.a()) {
            this.i = gjVar.b();
            gn.c("app name loaded: " + this.i);
        }
        if (gjVar.c()) {
            this.h = gjVar.d();
            gn.c("app version loaded: " + this.h);
        }
        if (gjVar.e() && (b = b(gjVar.f())) >= 0) {
            gn.c("log level loaded: " + b);
            d().a(b);
        }
        if (gjVar.g()) {
            this.d.a(gjVar.h());
        }
        if (gjVar.i()) {
            a(gjVar.j());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // defpackage.el
    public void a(Map<String, String> map) {
        synchronized (this) {
            if (map == null) {
                throw new IllegalArgumentException("hit cannot be null");
            }
            ew.a(map, "&ul", ew.a(Locale.getDefault()));
            ew.a(map, "&sr", er.a());
            map.put("&_u", t.a().c());
            t.a().b();
            this.c.a(map);
        }
    }

    public void a(boolean z) {
        t.a().a(t.a.SET_DRY_RUN);
        this.b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Activity activity) {
        Iterator<ea> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().b(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(ea eaVar) {
        this.j.remove(eaVar);
    }

    public boolean b() {
        t.a().a(t.a.GET_DRY_RUN);
        return this.b;
    }

    public boolean c() {
        t.a().a(t.a.GET_APP_OPT_OUT);
        return this.f.booleanValue();
    }

    public eh d() {
        return this.g;
    }

    @Deprecated
    public void e() {
        this.d.a();
    }
}
