package defpackage;

import android.os.Build;
import android.telephony.TelephonyManager;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FetchRequest;
import java.util.Iterator;
import java.util.logging.Level;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byx extends bxz {
    public byx(App app, FetchRequest fetchRequest, IService iService) {
        super("game", app, iService, fetchRequest, true);
    }

    @Override // defpackage.bxz
    protected void b() {
        String networkOperatorName;
        if (this.b != null) {
            a("game_id", this.b.g().intValue());
            a("device_os_vers", Build.VERSION.SDK_INT);
            if (this.b.s()) {
                a("update_only", "Y");
            }
            if (!this.b.s()) {
                a("schedule", "True");
                a("online", "Y");
                a("detail", "Y");
            }
            try {
                TelephonyManager telephonyManager = (TelephonyManager) k().getSystemService("phone");
                if (telephonyManager != null && (networkOperatorName = telephonyManager.getNetworkOperatorName()) != null && networkOperatorName.length() > 0) {
                    a("operator", networkOperatorName);
                }
            } catch (Throwable th) {
                getLogger().log(Level.WARNING, "no telephony manager found", th);
            }
            String ay = k().ay();
            if (ay != null) {
                a("tv_provider", ay);
            }
            String str = "highlights,fantasy_players,friend_thuuzes";
            if (!k().cb()) {
                str = "fantasy_players,friend_thuuzes";
            }
            a("objects", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            a(d(jSONObject));
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "problem with parsing game: " + th);
            a(-1);
        }
        return v();
    }

    private JSONObject d(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (!jSONObject.has("game")) {
            throw new IllegalArgumentException("cannot find simgle-game key: game in: " + jSONObject);
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!"game".equals(next)) {
                jSONObject2.put(next, jSONObject.get(next));
            }
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(0, jSONObject.get("game"));
        jSONObject2.put("ratings", jSONArray);
        return jSONObject2;
    }
}
