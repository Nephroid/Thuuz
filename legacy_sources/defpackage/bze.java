package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bze extends bya {
    public bze(App app, IService iService) {
        super("gamecounts", app, iService, true);
        c(true);
        a("type", "live");
        if (k().aB()) {
            a(new BasicNameValuePair("team_ids", k().D()));
        }
        if (k().az()) {
            a(new BasicNameValuePair("league_ids", k().B()));
        }
        if (k().aA()) {
            a(new BasicNameValuePair("league_group_ids", k().C()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        IService z;
        int i;
        int parseInt;
        int i2 = 0;
        try {
            z = z();
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "problem parsing live game count: " + th);
            a(-1);
        }
        if (z == null) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(-5, Integer.valueOf(jSONObject.getInt("shared")));
        hashMap.put(-2, Integer.valueOf(jSONObject.has("my_teams") ? jSONObject.getInt("my_teams") : 0));
        hashMap.put(-1, Integer.valueOf(jSONObject.has("my_sports") ? jSONObject.getInt("my_sports") : 0));
        JSONObject jSONObject2 = jSONObject.getJSONObject("leagues");
        boolean g = z.g();
        HashMap hashMap2 = new HashMap();
        for (bpr bprVar : z.y()) {
            hashMap2.put(Integer.valueOf(bprVar.g()), bprVar);
        }
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                parseInt = Integer.parseInt(next);
            } catch (Throwable th2) {
                i = i2;
            }
            if (!g || hashMap2.get(Integer.valueOf(parseInt)) != null) {
                int i3 = jSONObject2.getInt(next);
                hashMap.put(Integer.valueOf(parseInt), Integer.valueOf(i3));
                i = hashMap2.get(Integer.valueOf(parseInt)) != null ? i3 + i2 : i2;
                i2 = i;
            }
        }
        hashMap.put(-7, Integer.valueOf(i2));
        z.b(hashMap);
        getLogger().info("live game count: " + hashMap.size());
        return v();
    }

    @Override // defpackage.bya
    protected boolean e() {
        return true;
    }
}
