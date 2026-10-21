package defpackage;
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import java.util.HashMap;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzk extends bya {
    public bzk(App app) {
        super("prefs", app, true);
        try {
            JSONObject b = b();
            bzj bzjVar = new bzj(false, app);
            bzjVar.l();
            a(app, bzjVar.x() != null ? (JSONObject) bzjVar.x().get("prefs") : new JSONObject(), b);
            a(new BasicNameValuePair("prefs", b().toString()));
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem in PrefsUpTask: ", th);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        k().bv();
        return v();
    }

    private boolean a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).length() > 0;
        } catch (Throwable th) {
            return false;
        }
    }

    private boolean a(JSONObject jSONObject) {
        return a(jSONObject, "leagues") || a(jSONObject, "league_groups") || a(jSONObject, "teams");
    }

    private void a(App app, JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            boolean a = a(jSONObject);
            boolean a2 = a(jSONObject2);
            String str = null;
            if (!a && a2) {
                app.getClass();
                str = "prefs_set";
            } else if (a && !a2) {
                app.getClass();
                str = "prefs_off";
            }
            if (str != null) {
                AppsFlyerLib.a(app.getApplicationContext(), str, new HashMap());
            }
            getLogger().info("Old Prefs JSON:" + jSONObject.toString());
            getLogger().info("New Prefs JSON:" + jSONObject2.toString());
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem in trackPrefChanges: ", th);
        }
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        k().bt();
    }

    private JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("leagues", k().n());
        jSONObject.put("league_groups", k().q());
        jSONObject.put("teams", k().u());
        String ay = k().ay();
        if (ay != null) {
            jSONObject.put("tv_provider", ay);
        }
        String bp = k().bp();
        if (bp != null) {
            jSONObject.put("zipcode", bp);
        }
        jSONObject.put("show_scores", k().aK());
        jSONObject.put("push_alerts", k().bD());
        jSONObject.put("notify_share", k().bB());
        jSONObject.put("notify_invite", k().bC());
        return jSONObject;
    }
}
