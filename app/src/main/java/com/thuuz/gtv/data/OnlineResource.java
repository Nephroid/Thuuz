package com.thuuz.gtv.data;

import android.net.Uri;
import com.facebook.internal.NativeProtocol;
import com.samsung.android.sdk.richnotification.Utilities;
import com.thuuz.gtv.App;
import defpackage.bpq;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OnlineResource extends bpq {

    /* loaded from: classes.dex */
    public enum LAUNCH_TYPE {
        LAUNCH_TYPE_WEB(0),
        LAUNCH_TYPE_APP(1);

        private int id;

        LAUNCH_TYPE(int i) {
            this.id = i;
        }

        public static LAUNCH_TYPE a(int i) {
            return i == LAUNCH_TYPE_APP.id ? LAUNCH_TYPE_APP : LAUNCH_TYPE_WEB;
        }
    }

    public OnlineResource(JSONObject jSONObject) {
        super(jSONObject);
    }

    public Integer a() {
        try {
            if (l("category")) {
                return Integer.valueOf(f("category"));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return null;
    }

    public int b() {
        return f("launch_type");
    }

    public LAUNCH_TYPE c() {
        return LAUNCH_TYPE.a(b());
    }

    public String d() {
        return e("title");
    }

    public String e() {
        return e("fineprint");
    }

    public String f() {
        return e("short_fineprint");
    }

    public String g() {
        return e(NativeProtocol.IMAGE_URL_KEY);
    }

    public String h() {
        return e("description").trim();
    }

    public String i() {
        return e("label");
    }

    public String j() {
        return e("image_url");
    }

    public boolean a(App app) {
        boolean z = false;
        String g = g();
        try {
            z = app.a(Uri.parse(g));
        } catch (Throwable th) {
            getLogger().severe("cannot parse uri from url: " + g);
        }
        if (!z) {
            getLogger().info("no app installed to view url: " + g);
        }
        return z;
    }

    @Override // defpackage.bpq
    public String toString() {
        return "online res: " + g() + " title: " + d() + " catId: " + a();
    }

    public String k() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    public String l() {
        String f = f();
        if (f.length() == 0) {
            f = e();
        }
        return f.trim();
    }
}
