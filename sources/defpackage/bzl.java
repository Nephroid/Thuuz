package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzl extends bya {
    public bzl(App app) {
        super("profile", app, true);
    }

    public bzl(App app, boolean z) {
        this(app);
        b(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        a(jSONObject);
        return v();
    }

    private void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("profile_prefs")) {
                getLogger().severe("missing profile_prefs!");
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("profile_prefs");
            if (jSONObject2.has("fname")) {
                k().u(jSONObject2.getString("fname"));
            }
            if (jSONObject2.has("lname")) {
                k().v(jSONObject2.getString("lname"));
            }
            if (jSONObject2.has("email")) {
                String string = jSONObject2.getString("email");
                if (!string.equalsIgnoreCase("foo@dummy")) {
                    k().w(string);
                }
            }
            if (jSONObject2.has("social_ids")) {
                k().p(jSONObject2.getString("social_ids"));
            }
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem getting profile from server: ", th);
        }
    }
}
