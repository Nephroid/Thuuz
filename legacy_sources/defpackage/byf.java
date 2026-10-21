package defpackage;

import com.thuuz.gtv.App;
import java.util.TimeZone;
import java.util.logging.Level;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byf extends bya {
    public byf(App app) {
        super("device", app, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        String str;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("device_prefs");
            String string = jSONObject2.getString("registration_id");
            String aT = k().aT();
            if ((aT == null && string != null && string.length() > 0) || ((aT != null && !aT.equals(string)) || (string != null && string.length() == 0))) {
                k().aL();
            }
            String string2 = jSONObject2.getString("timezone");
            try {
                str = DateTimeZone.getDefault().getID();
            } catch (Throwable th) {
                try {
                    str = TimeZone.getDefault().getID();
                } catch (Throwable th2) {
                    getLogger().log(Level.SEVERE, "problem getting tz: ", th2);
                    str = null;
                }
            }
            if (str != null && !str.equals(string2)) {
                k().aL();
            }
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
        return v();
    }
}
