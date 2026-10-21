package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.internal.gv;
import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class xj {
    DisplayMetrics a;
    private final gv d;
    private final Context e;
    private final WindowManager f;
    private final ui g;
    private float h;
    private int i;
    int b = -1;
    int c = -1;
    private int j = -1;
    private int k = -1;
    private int[] l = new int[2];

    public xj(gv gvVar, Context context, ui uiVar) {
        this.d = gvVar;
        this.e = context;
        this.g = uiVar;
        this.f = (WindowManager) context.getSystemService("window");
        g();
        a();
        h();
    }

    private void g() {
        this.a = new DisplayMetrics();
        Display defaultDisplay = this.f.getDefaultDisplay();
        defaultDisplay.getMetrics(this.a);
        this.h = this.a.density;
        this.i = defaultDisplay.getRotation();
    }

    private void h() {
        this.d.getLocationOnScreen(this.l);
        this.d.measure(0, 0);
        float f = 160.0f / this.a.densityDpi;
        this.j = Math.round(this.d.getMeasuredWidth() * f);
        this.k = Math.round(f * this.d.getMeasuredHeight());
    }

    private xg i() {
        return new xi().b(this.g.a()).a(this.g.b()).c(this.g.f()).d(this.g.c()).e(this.g.d()).a();
    }

    void a() {
        int c = acx.c(this.e);
        float f = 160.0f / this.a.densityDpi;
        this.b = Math.round(this.a.widthPixels * f);
        this.c = Math.round((this.a.heightPixels - c) * f);
    }

    public void b() {
        e();
        f();
        d();
        c();
    }

    public void c() {
        if (adn.a(2)) {
            adn.c("Dispatching Ready Event.");
        }
        this.d.b("onReadyEventReceived", new JSONObject());
    }

    public void d() {
        try {
            this.d.b("onDefaultPositionReceived", new JSONObject().put("x", this.l[0]).put("y", this.l[1]).put("width", this.j).put("height", this.k));
        } catch (JSONException e) {
            adn.b("Error occured while dispatching default position.", e);
        }
    }

    public void e() {
        try {
            this.d.b("onScreenInfoChanged", new JSONObject().put("width", this.b).put("height", this.c).put("density", this.h).put("rotation", this.i));
        } catch (JSONException e) {
            adn.b("Error occured while obtaining screen information.", e);
        }
    }

    public void f() {
        this.d.b("onDeviceFeaturesReceived", i().a());
    }
}
