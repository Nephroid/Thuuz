package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byl extends bya {
    public byl(App app) {
        super("fantasy_team_prefs", app, true);
    }

    public byl(App app, boolean z) {
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
            if (jSONObject.has("team_prefs")) {
                k().a(jSONObject.getJSONObject("team_prefs"));
            }
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem getting fantasy prefs from server: ", th);
        }
    }
}
