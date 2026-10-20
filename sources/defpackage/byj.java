package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byj extends bya {
    private int a;

    public byj(App app, int i) {
        super("fantasy_team_delete", app, true);
        this.a = i;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    protected void b() {
        if (this.a > 0) {
            a(new BasicNameValuePair("team_id", String.valueOf(this.a)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        getLogger().info("delete fantasy team SUCCESS");
        return v();
    }
}
