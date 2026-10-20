package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import com.thuuz.gtv.App;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpr extends bpq implements Comparable<bpr> {
    private bpx a;
    private List<bpb> c;

    public bpr(JSONObject jSONObject, String str, bpx bpxVar) {
        super(jSONObject);
        this.c = new ArrayList();
        b("league_key", str);
        this.a = bpxVar;
    }

    public bpr(bpx bpxVar, String str, int i, String str2, String str3, int i2, String str4, boolean z) {
        super(new JSONObject());
        this.c = new ArrayList();
        b("id", i);
        b(Utilities.DB_KEY_IMAGE_NAME, str3);
        b("league_key", str2);
        this.a = bpxVar;
        b("slug", str);
        b("display_order", i2);
        b("super_short_name", str4);
        b("rule_auto_select", z);
    }

    public bpr(bpr bprVar) {
        super(new JSONObject(bprVar.ar().toString()));
        this.c = new ArrayList();
        this.a = bprVar.a;
        this.c = bprVar.c;
    }

    @Override // defpackage.bpq
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return g() != ((bpr) obj).g();
    }

    @Override // defpackage.bpq
    public int hashCode() {
        return g();
    }

    public List<bpb> a() {
        return this.c;
    }

    public int b() {
        return f("display_order");
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpr bprVar) {
        if (bprVar == null) {
            return 1;
        }
        bpx c = c();
        if (c == null) {
            getLogger().severe("cannot find sport for league: " + e());
            return -1;
        }
        bpx c2 = bprVar.c();
        if (c2 == null) {
            getLogger().severe("cannot find sport for league: " + bprVar.e());
            return 1;
        }
        int compareTo = c.compareTo(c2);
        if (compareTo == 0) {
            compareTo = Integer.valueOf(b()).compareTo(Integer.valueOf(bprVar.b()));
        }
        String h = h();
        if (compareTo == 0 && h != null) {
            return h.compareTo(bprVar.h());
        }
        return compareTo;
    }

    @Override // defpackage.bzy
    public synchronized Logger getLogger() {
        Logger logger;
        logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public bpx c() {
        return this.a;
    }

    @Override // defpackage.bpq
    public String toString() {
        return h();
    }

    public boolean a(Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        for (bpb bpbVar : this.c) {
            if (bpbVar.c() != null) {
                if (bpbVar.c().isBefore(dateTime) && bpbVar.a().isAfter(dateTime)) {
                    return true;
                }
            } else if (bpbVar.a().isAfter(dateTime)) {
                return true;
            }
        }
        return false;
    }

    public String d() {
        return e("league_key");
    }

    public String e() {
        return e("slug");
    }

    public String f() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    public int g() {
        return f("id");
    }

    public String h() {
        return e("super_short_name");
    }

    public boolean i() {
        return e().equalsIgnoreCase("ncaa");
    }

    public void a(String str) {
        b(Utilities.DB_KEY_IMAGE_NAME, str);
    }

    public boolean j() {
        return g("has_standings");
    }

    public boolean k() {
        return g("rule_auto_select");
    }

    public boolean l() {
        return b() != -1;
    }

    public boolean m() {
        return g("has_team_logos");
    }
}
