package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzr extends bya {
    private String a;
    private boolean b;
    private String c;

    public bzr(App app, String str) {
        this(app, str, false);
    }

    public bzr(App app, String str, boolean z) {
        super("social_associate", app, true);
        this.a = str;
        this.b = z;
        this.c = "";
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
            if (this.b) {
                a(new BasicNameValuePair("delete", "True"));
            }
        }
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        try {
            if (i == -41) {
                if (jSONObject.has("email")) {
                    this.c = jSONObject.getString("email");
                } else {
                    getLogger().warning("No EMAIL returned for ERROR_SOCIAL_IN_USE.");
                }
            } else if (i == -23) {
                d();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        int i = 0;
        if (this.b) {
            d();
        } else {
            try {
                String be = k().be();
                JSONArray jSONArray = new JSONArray();
                if (be != null) {
                    JSONArray jSONArray2 = new JSONArray(be);
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                        if (!jSONObject2.getString("prov").equalsIgnoreCase("facebook")) {
                            jSONArray.put(i, jSONObject2);
                            i++;
                        }
                    }
                }
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("prov", "facebook");
                jSONObject3.put("sid", k().bd());
                jSONArray.put(i, jSONObject3);
                k().p(jSONArray.toString());
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
        }
        return super.b(jSONObject);
    }

    private void d() {
        try {
            getLogger().info("after deleting fb association, removing fb info");
            k().aY().a(false);
            k().q(k().aY().b());
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Problem logging out of fb: ", th);
        }
    }

    public String c() {
        return this.c;
    }
}
