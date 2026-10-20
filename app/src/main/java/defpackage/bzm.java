package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzm extends bya {
    private String a;
    private String b;

    public bzm(App app, String str, String str2) {
        super("profile", app, true);
        this.a = str;
        this.b = str2;
        try {
            if (str == null && str2 == null) {
                throw new IllegalArgumentException("bad values");
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("fname", str);
            jSONObject.put("lname", str2);
            a(new BasicNameValuePair("profile_prefs", jSONObject.toString()));
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            k().u(this.a);
            k().v(this.b);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return v();
    }
}
