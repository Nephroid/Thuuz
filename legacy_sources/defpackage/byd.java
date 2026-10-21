package defpackage;

import android.util.Base64;
import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byd extends bya {
    private String a;
    private String b;

    public byd(App app, String str, String str2) {
        super("change_credentials", app, true);
        this.a = str;
        this.b = str2;
        try {
            if (str == null || str2 == null) {
                throw new IllegalArgumentException("bad values");
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("new", str);
            jSONObject.put("pwd", str2);
            a(new BasicNameValuePair("email_params", "B" + Base64.encodeToString(jSONObject.toString().getBytes(), 8)));
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
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
                k().w(this.a);
                k().s(k().d(this.a, this.b));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return v();
    }
}
