package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import com.thuuz.gtv.IService;
import java.util.Collection;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpz extends bpq implements Comparable<bpz> {
    public bpz(JSONObject jSONObject) {
        super(jSONObject);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpz bpzVar) {
        if (c() == null || bpzVar.c() == null) {
            return 0;
        }
        return c().compareTo(bpzVar.c());
    }

    public String a() {
        return e("short_name");
    }

    @Override // defpackage.bpq
    public String toString() {
        return c();
    }

    public int b() {
        return f("id");
    }

    public String c() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    public bpr a(Collection<bpr> collection) {
        int e = e();
        for (bpr bprVar : collection) {
            if (bprVar.g() == e) {
                return bprVar;
            }
        }
        return null;
    }

    public int d() {
        return f("league_group_id");
    }

    public int e() {
        return f("league_id");
    }

    public String a(IService iService) {
        return a(a(), iService);
    }

    public String b(IService iService) {
        return a(c(), iService);
    }

    private String a(String str, IService iService) {
        bpr c;
        if (iService != null) {
            try {
                String str2 = "";
                boolean z = false;
                bps d = iService.d(d());
                if (d != null) {
                    z = d.g();
                    str2 = d.d().c().c();
                }
                if (!z && (c = iService.c(e())) != null) {
                    z = c.i();
                    str2 = c.c().c();
                }
                if (z) {
                    return str + " " + str2;
                }
                return str;
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
                return str;
            }
        }
        return str;
    }
}
