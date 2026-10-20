package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FantasySite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bys extends bya {
    private int a;
    private HashMap<Integer, FantasySite> b;

    public bys(App app, int i, IService iService) {
        super("fantasy_sites", app, iService, true);
        this.b = new HashMap<>();
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

    private void a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("sites");
            if (jSONObject2 != null) {
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    FantasySite fantasySite = new FantasySite(jSONObject2.getJSONObject(next));
                    fantasySite.a(Integer.parseInt(next));
                    fantasySite.b(this.a);
                    this.b.put(Integer.valueOf(fantasySite.a()), fantasySite);
                }
                IService z = z();
                if (z != null) {
                    z.a(Integer.valueOf(this.a), this.b);
                }
                getLogger().info("fantasy sites succeeded with sites: " + this.b.size());
            }
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem syncing fantasy teams from server: ", th);
        }
    }

    public List<FantasySite> b() {
        return new ArrayList(this.b.values());
    }
}
