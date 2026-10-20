package defpackage;

import com.facebook.internal.NativeProtocol;
import com.thuuz.gtv.App;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byu extends bya {
    List<bqb> a;
    private Set<String> b;

    public byu(App app) {
        super("find_friends", app, true);
        this.a = new ArrayList();
        this.b = new HashSet();
        a("provider", "facebook");
        a(new BasicNameValuePair("social_params", k().aY().a(k().bd())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        a(jSONObject);
        return v();
    }

    private void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.has(NativeProtocol.AUDIENCE_FRIENDS)) {
                getLogger().severe("cannot find 'friends' key in downloaded friends");
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray(NativeProtocol.AUDIENCE_FRIENDS);
            for (int i = 0; i < jSONArray.length(); i++) {
                this.a.add(new bqb(jSONArray.getJSONObject(i)));
            }
            if (!jSONObject.has("invites")) {
                getLogger().severe("cannot find 'invites' key in downloaded friends");
                return;
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("invites");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                this.b.add(jSONArray2.getJSONObject(i2).getString("sid"));
            }
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem getting from server: ", th);
        }
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        if (i == -24) {
            getLogger().info("clearing facebook info to cause re-auth because server has different info");
            k().aY().a(false);
        }
    }

    @Override // defpackage.bya
    protected void q() {
        try {
            if (v() == -24) {
                getLogger().info("dissociate result: " + new bzr(k(), k().aY().a(k().bd()), true).l());
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Problem doing postTask: ", th);
        }
    }

    public List<bqb> b() {
        return this.a;
    }

    public Set<String> c() {
        return this.b;
    }
}
