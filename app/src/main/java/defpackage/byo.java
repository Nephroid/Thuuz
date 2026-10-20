package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FantasySite;
import java.util.Locale;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byo extends bya {
    private int a;
    private int b;
    private String c;

    public byo(App app, IService iService, int i, int i2, String str) {
        super("fantasy_login", app, iService, true);
        if (str != null) {
            a(new BasicNameValuePair("login_params", str));
        }
        this.a = i;
        this.b = i2;
        try {
            if (this.a <= 0) {
                throw new IllegalArgumentException("bad values");
            }
            a("competition_id", this.a);
            a("site_id", this.b);
            a("country", Locale.getDefault().getCountry());
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            FantasySite b = z().b(this.b, this.a);
            if (b != null) {
                b.a(FantasySite.FantasySiteStatus.CONNECTED);
                b.a("Connected");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return v();
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("message_html")) {
            try {
                this.c = jSONObject.getString("message_html");
            } catch (JSONException e) {
                getLogger().log(Level.SEVERE, "Cannot unpack html err message from json: ", (Throwable) e);
            }
        }
    }

    public String b() {
        return this.c;
    }
}
