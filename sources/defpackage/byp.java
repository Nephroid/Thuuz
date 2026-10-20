package defpackage;

import android.os.Handler;
import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byp extends bya {
    private int a;
    private Handler b;

    public byp(App app, int i, Handler handler) {
        super("fantasy_team_prefs", app, true);
        this.a = i;
        this.b = handler;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    protected void b() {
        if (this.a > 0) {
            bph f = k().f(this.a);
            if (f != null) {
                a("team_id", this.a);
                a(new BasicNameValuePair("prefs", f.toString()));
                return;
            }
            throw new Exception("Fantasy Team not found for id: " + this.a);
        }
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        try {
            if (this.b != null) {
                this.b.post(new byq(this));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        getLogger().info("set fantasy team prefs SUCCESS");
        return v();
    }
}
