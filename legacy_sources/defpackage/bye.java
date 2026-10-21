package defpackage;

import android.util.Base64;
import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bye extends bya {
    private String a;

    public bye(App app, String str, String str2) {
        super("change_credentials", app, true);
        this.a = str2;
        try {
            if (str == null) {
                throw new IllegalArgumentException("bad values");
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("old", str);
            jSONObject.put("new", str2);
            a(new BasicNameValuePair("pwd_params", "B" + Base64.encodeToString(jSONObject.toString().getBytes(), 8)));
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            if (jSONObject.has("user_cookie")) {
                String string = jSONObject.getString("user_cookie");
                if (string != null) {
                    k().r(string);
                }
                k().s(k().d(k().by(), this.a));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return v();
    }
}
