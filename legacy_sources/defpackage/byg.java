package defpackage;

import com.thuuz.gtv.App;
import java.util.TimeZone;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byg extends bya {
    public byg(App app) {
        super("device", app, true);
        try {
            JSONObject jSONObject = new JSONObject();
            String aT = k().aT();
            jSONObject.put("registration_id", aT == null ? "" : aT);
            try {
                jSONObject.put("timezone", DateTimeZone.getDefault().getID());
            } catch (Throwable th) {
                try {
                    jSONObject.put("timezone", TimeZone.getDefault().getID());
                } catch (Throwable th2) {
                    getLogger().log(Level.SEVERE, "problem getting tz: ", th2);
                }
            }
            a(new BasicNameValuePair("device_prefs", jSONObject.toString()));
        } catch (Throwable th3) {
            getLogger().log(Level.SEVERE, "problem: ", th3);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        k().aM();
        return v();
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        if (i == -50) {
            k().M();
        }
        k().aL();
    }
}
