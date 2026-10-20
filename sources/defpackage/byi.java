package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byi extends bya {
    private boolean a;

    public byi(App app, IService iService) {
        super("fantasy_competitions", app, iService, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        a(jSONObject);
        return v();
    }

    private void a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("competitions");
            IService z = z();
            if (jSONObject2 == null || z == null) {
                a(-1);
                getLogger().log(Level.SEVERE, "competition or service null: " + jSONObject2 + "/" + z);
                return;
            }
            z.i();
            HashMap hashMap = new HashMap();
            boolean z2 = false;
            Map<Integer, bpb> x = z.x();
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bpe bpeVar = new bpe(jSONObject2.getJSONObject(next));
                bpeVar.a(Integer.parseInt(next));
                boolean z3 = bpeVar.f() ? true : z2;
                bpb bpbVar = x.get(Integer.valueOf(bpeVar.a()));
                if (bpbVar == null) {
                    if (!"-1".equals(next)) {
                        getLogger().warning("cannot find comp for fantasy even after sportinfo wait, comp id: " + next);
                    }
                } else {
                    bpeVar.b(bpbVar.d());
                }
                hashMap.put(Integer.valueOf(bpeVar.a()), bpeVar);
                z2 = z3;
            }
            z.a(hashMap);
            if (jSONObject.has("has_fantasy_account")) {
                this.a = jSONObject.getBoolean("has_fantasy_account");
                k().a(this.a);
            }
            if (!z2) {
                k().a((JSONObject) null);
            }
            getLogger().info("fantasy competitions: " + hashMap.size() + "; has account(s): " + z2);
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem syncing fantasy competitions from server: ", th);
        }
    }

    public boolean b() {
        return this.a;
    }

    @Override // defpackage.bya
    protected boolean e() {
        return true;
    }
}
