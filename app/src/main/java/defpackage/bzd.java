package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzd extends bya {
    String a;
    bpl b;
    String c;

    public bzd(App app, String str, bpl bplVar) {
        super("invite", app, true);
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = str;
        this.b = bplVar;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    public bzd(App app, String str) {
        super("invite", app, true);
        this.a = null;
        this.b = null;
        this.c = null;
        this.c = str;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    protected void b() {
        if (this.c != null || this.b != null) {
            if (this.c != null) {
                a(new BasicNameValuePair("provider", "email"));
            } else {
                a(new BasicNameValuePair("provider", "facebook"));
            }
            if (this.a != null) {
                a(new BasicNameValuePair("request_id", this.a));
            }
            JSONObject jSONObject = new JSONObject();
            if (this.c != null) {
                jSONObject.put("email", this.c);
            } else {
                jSONObject.put("sid", this.b.b());
                jSONObject.put("fname", this.b.f());
                jSONObject.put("lname", this.b.g());
            }
            a(new BasicNameValuePair("request_params", jSONObject.toString()));
        }
    }
}
