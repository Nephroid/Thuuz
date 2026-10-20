package defpackage;

import android.content.Context;
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.HashMap;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzn extends bya {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    public bzn(App app, IService iService, String str, String str2, String str3, String str4, String str5) {
        super("register_user", app, iService, true);
        this.b = str;
        this.c = str2;
        this.a = str3;
        this.d = str4;
        this.e = str5;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    protected void b() {
        if (this.d != null) {
            a(new BasicNameValuePair("login_params", this.d));
            a(new BasicNameValuePair("first_name", this.b));
            a(new BasicNameValuePair("last_name", this.c));
            if (this.e != null) {
                a(new BasicNameValuePair("social_params", this.e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        bpw c;
        try {
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            a(-1);
        }
        if (jSONObject.has("user_cookie")) {
            String string = jSONObject.getString("user_cookie");
            if (string.length() > 0) {
                k().r(string);
            } else {
                getLogger().warning("cookie has len 0!");
                a(-1);
            }
            k().l(this.a);
            k().u(this.b);
            k().v(this.c);
            k().w(this.a);
            k().s(this.d);
            k().a(this.e != null ? App.SIGN_IN_TYPE.SIGN_IN_TYPE_FACEBOOK : App.SIGN_IN_TYPE.SIGN_IN_TYPE_USER);
            if (this.e != null && (c = k().aY().c()) != null) {
                try {
                    HashMap hashMap = new HashMap();
                    Context applicationContext = k().getApplicationContext();
                    k().getClass();
                    AppsFlyerLib.a(applicationContext, "facebook_on", hashMap);
                } catch (Throwable th2) {
                    getLogger().log(Level.SEVERE, "problem with AppsFlyer event: ", th2);
                }
                k().c(k().aY().b(), c.a());
            }
            return v();
        }
        getLogger().warning("no cookie!");
        a(-1);
        return v();
    }
}
