package defpackage;

import android.content.Intent;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FantasySite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byr extends bya {
    private int a;
    private Map<Integer, bpg> b;

    public byr(App app, IService iService, int i) {
        super("fantasy_teams", app, iService, true);
        this.b = new HashMap();
        this.a = i;
        try {
            if (this.a <= 0) {
                throw new IllegalArgumentException("bad values");
            }
            a("competition_id", this.a);
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

    @Override // defpackage.bya
    protected void q() {
        Intent intent = new Intent("fantasy_sync");
        intent.putExtra("result", v());
        k().sendBroadcast(intent, "com.thuuz.permission.local.broadcast");
    }

    private void a(JSONObject jSONObject) {
        try {
            IService z = z();
            if (z == null) {
                getLogger().severe("no service");
                a(-1);
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("sites");
            if (jSONObject2 != null) {
                HashMap hashMap = new HashMap();
                Iterator<String> keys = jSONObject2.keys();
                int i = 0;
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONObject jSONObject3 = jSONObject2.getJSONObject(next);
                    FantasySite fantasySite = new FantasySite(jSONObject3);
                    fantasySite.a(Integer.parseInt(next));
                    fantasySite.b(this.a);
                    hashMap.put(Integer.valueOf(fantasySite.a()), fantasySite);
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("teams");
                    if (jSONObject4 != null) {
                        Iterator<String> keys2 = jSONObject4.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            bpg bpgVar = new bpg(jSONObject4.getJSONObject(next2));
                            bpgVar.a(Integer.parseInt(next2));
                            bpgVar.a(Integer.valueOf(this.a));
                            bpgVar.b(Integer.valueOf(fantasySite.a()));
                            this.b.put(Integer.valueOf(bpgVar.a()), bpgVar);
                        }
                    }
                    i = this.b.size() + i;
                }
                z.b(Integer.valueOf(this.a), this.b);
                z.a(Integer.valueOf(this.a), hashMap);
                getLogger().info("fantasy teams succeeded with sites: " + hashMap.size() + " and teams: " + i);
            }
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem syncing fantasy teams from server: ", th);
        }
    }
}
