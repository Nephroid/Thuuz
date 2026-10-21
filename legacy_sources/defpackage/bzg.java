package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzg extends bya {
    private String a;
    private String b;

    public bzg(App app, String str) {
        this(app);
        if (str != null) {
            a(new BasicNameValuePair("login_params", str));
        }
        this.a = str;
    }

    public bzg(String str, App app) {
        this(app);
        a(new BasicNameValuePair("social_params", str));
        this.b = str;
    }

    private bzg(App app) {
        super("login", app, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        bpw c;
        try {
            if (jSONObject.has("user_cookie")) {
                String string = jSONObject.getString("user_cookie");
                if (string.length() > 0) {
                    k().r(string);
                    boolean z = this.b != null;
                    if (this.a == null && !z) {
                        k().a(App.SIGN_IN_TYPE.SIGN_IN_TYPE_ANONYMOUS);
                    } else {
                        k().a(z ? App.SIGN_IN_TYPE.SIGN_IN_TYPE_FACEBOOK : App.SIGN_IN_TYPE.SIGN_IN_TYPE_USER);
                    }
                    if (z && (c = k().aY().c()) != null) {
                        k().c(k().aY().b(), c.a());
                    }
                    if (k().i()) {
                        b();
                    }
                    if (k().aX()) {
                        getLogger().info("login causes google push change");
                        k().ap();
                    } else {
                        k().ar();
                    }
                    if (k().z()) {
                        k().L();
                    }
                } else {
                    getLogger().warning("cookie has len 0!");
                    a(-1);
                }
            } else {
                getLogger().warning("no cookie from server!");
                a(-1);
            }
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
        return v();
    }

    private void b() {
        boolean z = false;
        car carVar = new car();
        bzj bzjVar = new bzj(k(), false);
        carVar.a(bzjVar);
        bzl bzlVar = new bzl(k(), false);
        carVar.a(bzlVar);
        byl bylVar = new byl(k(), false);
        carVar.a(bylVar);
        try {
            carVar.a(240000L);
            z = true;
        } catch (InterruptedException e) {
            getLogger().log(Level.SEVERE, "Timeout after " + (240000 / 60000) + " min., syncing prefs/profile/fantasy: " + bzjVar.v() + "/" + bzlVar.v() + "/" + bylVar.v());
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Problem syncing prefs/profile/fantasy: " + bzjVar.v() + "/" + bzlVar.v() + "/" + bylVar.v(), th);
        } finally {
            carVar.a();
        }
        if (z) {
            k().l();
        }
    }

    @Override // defpackage.bya
    public int l() {
        try {
            t();
            JSONObject x = x();
            if (x == null) {
                if (v() == 0) {
                    a(-1);
                }
            } else {
                a(x.getInt("result"));
                if (y()) {
                    a(b(x));
                }
            }
            if (!y() && r()) {
                a(-10);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem logging in: ", th);
            a(-1);
        }
        return v();
    }
}
