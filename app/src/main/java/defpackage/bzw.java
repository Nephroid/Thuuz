package defpackage;

import com.thuuz.gtv.App;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzw extends bya {
    private String a;
    private int b;
    private List<bpz> c;

    public bzw(App app, int i) {
        super("teams", app, false);
        this.c = new ArrayList();
        this.b = i;
        c();
    }

    public bzw(App app, String str) {
        super("teams", app, false);
        this.c = new ArrayList();
        if (str != null) {
            this.a = str;
        }
        c();
    }

    public bzw(App app) {
        super("teams", app, false);
        this.c = new ArrayList();
    }

    private void c() {
        try {
            if (this.b <= 0 && this.a == null) {
                throw new IllegalArgumentException("bad values");
            }
            if (this.b > 0) {
                a("league_ids", Integer.toString(this.b));
            }
            if (this.a != null) {
                a(new BasicNameValuePair("team_ids", this.a));
            }
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            a(jSONObject);
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "problem with parsing teams: " + th);
            a(-1);
        }
        return v();
    }

    public void a(String str) {
        cak.a(k().bs(), str);
    }

    public List<bpz> b(String str) {
        a(new JSONObject(str));
        return b();
    }

    private void a(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("teams");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (!jSONArray.get(i).equals(null)) {
                    bpz bpzVar = new bpz(jSONArray.getJSONObject(i));
                    if (bpzVar.e() <= 0 || bpzVar.c().length() == 0 || bpzVar.b() <= 0) {
                        getLogger().severe("cannot get required team info: " + bpzVar.ar().toString());
                    } else {
                        this.c.add(bpzVar);
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem parsing teams: ", th);
            a(-1);
        }
    }

    public List<bpz> b() {
        return this.c;
    }
}
