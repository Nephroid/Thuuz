package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class byt extends bya {
    private final int a;
    private final int b;
    private boolean c;

    public byt(App app, int i, IService iService, int i2) {
        super("fantasy_update_teams", app, iService, true);
        this.b = i;
        this.a = i2;
        try {
            if (this.a <= 0) {
                throw new IllegalArgumentException("bad values");
            }
            if (z() == null) {
                throw new Exception("null service");
            }
            a("competition_id", this.a);
            a("site_id", this.b);
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        return a(jSONObject);
    }

    private int a(JSONObject jSONObject) {
        IService z;
        try {
            this.c = jSONObject.getBoolean("teams_changed");
            boolean z2 = this.c;
            if (!this.c && (z = z()) != null) {
                TreeSet<bpg> a = z.a(this.b, this.a);
                z2 = a.size() == 0;
                if (!z2) {
                    Iterator<bpg> it = a.iterator();
                    boolean z3 = true;
                    while (it.hasNext()) {
                        z3 = k().f(it.next().a()) != null;
                        if (!z3) {
                            break;
                        }
                    }
                    z2 = !z3;
                }
            }
            if (!z2) {
                return 0;
            }
            car carVar = new car();
            carVar.a(new byl(k()));
            carVar.a(new byr(k(), z(), this.a));
            try {
                try {
                    carVar.a(60000L);
                    return 0;
                } catch (InterruptedException e) {
                    getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
                    a(-1);
                    carVar.a();
                    return -1;
                }
            } finally {
                carVar.a();
            }
        } catch (Throwable th) {
            a(-1);
            getLogger().log(Level.SEVERE, "Problem syncing fantasy teams from server: ", th);
            return -1;
        }
    }

    public boolean b() {
        return this.c;
    }
}
