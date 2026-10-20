package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzs extends bya {
    private String a;
    private Set<bpz> b;

    public bzs(App app, IService iService, String str) {
        super("social_recommend_prefs", app, iService, true);
        this.b = new TreeSet();
        if (iService == null) {
            getLogger().severe("service param null");
        }
        c(true);
        this.a = str;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    protected void b() {
        if (this.a != null && this.a.length() != 0) {
            a(new BasicNameValuePair("social_params", this.a));
        }
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("recommended_prefs").getJSONArray("teams");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (!jSONArray.get(i).equals(null)) {
                    bpz bpzVar = new bpz(jSONArray.getJSONObject(i));
                    if (bpzVar.e() <= 0 || bpzVar.c().length() == 0 || bpzVar.b() <= 0) {
                        getLogger().severe("cannot get required team info: " + bpzVar.ar().toString());
                    } else {
                        this.b.add(bpzVar);
                        IService z = z();
                        if (z != null) {
                            k().a(bqa.b(Integer.valueOf(bpzVar.b()), z.e(bpzVar.e())));
                        }
                    }
                }
            }
            return v();
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "problem: ", th);
            return super.b(jSONObject);
        }
    }

    public Set<bpz> c() {
        return this.b;
    }
}
