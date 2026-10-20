package defpackage;

import android.util.Base64;
import java.util.StringTokenizer;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bqd extends bzy {
    private String a;

    public bqd(String str) {
        this.a = str;
    }

    public String a() {
        try {
            if (this.a == null) {
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(this.a, ":");
            if (stringTokenizer.countTokens() < 1) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(new String(Base64.decode(stringTokenizer.nextToken(), 8)));
            if (jSONObject.has("u")) {
                return jSONObject.getString("u");
            }
            return null;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }
}
