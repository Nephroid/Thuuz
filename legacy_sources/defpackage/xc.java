package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gv;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class xc {
    static final Set<String> a = new HashSet(Arrays.asList("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"));
    private int b = -1;
    private int c = -1;
    private int d = 0;
    private int e = 0;
    private boolean f = true;
    private String g = "top-right";
    private final gv h;
    private final Map<String, String> i;
    private final Context j;

    public xc(gv gvVar, Map<String, String> map) {
        this.h = gvVar;
        this.i = map;
        this.j = gvVar.k();
    }

    private void e() {
        int[] d = acx.d(this.j);
        if (!TextUtils.isEmpty(this.i.get("width"))) {
            int b = acx.b(this.i.get("width"));
            if (a(b, d[0])) {
                this.b = b;
            }
        }
        if (!TextUtils.isEmpty(this.i.get("height"))) {
            int b2 = acx.b(this.i.get("height"));
            if (b(b2, d[1])) {
                this.c = b2;
            }
        }
        if (!TextUtils.isEmpty(this.i.get("offsetX"))) {
            this.d = acx.b(this.i.get("offsetX"));
        }
        if (!TextUtils.isEmpty(this.i.get("offsetY"))) {
            this.e = acx.b(this.i.get("offsetY"));
        }
        if (!TextUtils.isEmpty(this.i.get("allowOffscreen"))) {
            this.f = Boolean.parseBoolean(this.i.get("allowOffscreen"));
        }
        String str = this.i.get("customClosePosition");
        if (TextUtils.isEmpty(str) || !a.contains(str)) {
            return;
        }
        this.g = str;
    }

    boolean a() {
        return this.b > -1 && this.c > -1;
    }

    boolean a(int i, int i2) {
        return i >= 50 && i < i2;
    }

    public void b() {
        adn.c("PLEASE IMPLEMENT mraid.resize()");
        if (this.j == null) {
            adn.e("Not an activity context. Cannot resize.");
            return;
        }
        if (this.h.e().e) {
            adn.e("Is interstitial. Cannot resize an interstitial.");
            return;
        }
        if (this.h.j()) {
            adn.e("Is expanded. Cannot resize an expanded banner.");
            return;
        }
        e();
        if (!a()) {
            adn.e("Invalid width and height options. Cannot resize.");
            return;
        }
        WindowManager windowManager = (WindowManager) this.j.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int a2 = adm.a(displayMetrics, this.b) + 16;
        int a3 = adm.a(displayMetrics, this.c) + 16;
        ViewParent parent = this.h.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.h);
        }
        LinearLayout linearLayout = new LinearLayout(this.j);
        linearLayout.setBackgroundColor(0);
        PopupWindow popupWindow = new PopupWindow(this.j);
        popupWindow.setHeight(a3);
        popupWindow.setWidth(a2);
        popupWindow.setClippingEnabled(!this.f);
        popupWindow.setContentView(linearLayout);
        linearLayout.addView(this.h, -1, -1);
        popupWindow.showAtLocation(((Activity) this.j).getWindow().getDecorView(), 0, this.d, this.e);
        this.h.a(new ay(this.j, new db(this.b, this.c)));
        c();
        d();
    }

    boolean b(int i, int i2) {
        return i >= 50 && i < i2;
    }

    void c() {
        try {
            this.h.b("onSizeChanged", new JSONObject().put("x", this.d).put("y", this.e).put("width", this.b).put("height", this.c));
        } catch (JSONException e) {
            adn.b("Error occured while dispatching size change.", e);
        }
    }

    void d() {
        try {
            this.h.b("onStateChanged", new JSONObject().put("state", "resized"));
        } catch (JSONException e) {
            adn.b("Error occured while dispatching state change.", e);
        }
    }
}
