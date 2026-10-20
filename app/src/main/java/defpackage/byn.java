package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byn extends bya {
    private int a;

    public byn(App app, IService iService, int i) {
        super("fantasy_league", app, iService, true);
        this.a = i;
        try {
            if (this.a <= 0) {
                throw new IllegalArgumentException("bad values");
            }
            a("team_id", this.a);
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        a(jSONObject);
        return v();
    }

    private void a(JSONObject jSONObject) {
        try {
            z().a(this.a, new bpf(jSONObject, this.a));
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem syncing fantasy teams from server: ", th);
        }
    }
}
