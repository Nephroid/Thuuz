package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpg extends bpq implements Comparable<bpg> {
    public bpg(JSONObject jSONObject) {
        super(jSONObject);
    }

    public int a() {
        return f("id");
    }

    public void a(int i) {
        a("id", i);
    }

    public int b() {
        return f("competition_id");
    }

    public void a(Integer num) {
        a("competition_id", num.intValue());
    }

    public int c() {
        return f("site_id");
    }

    public void b(Integer num) {
        a("site_id", num.intValue());
    }

    public String d() {
        return e("league_name");
    }

    public String e() {
        return e(Utilities.DB_KEY_IMAGE_NAME).trim();
    }

    public void a(String str) {
        a(Utilities.DB_KEY_IMAGE_NAME, str);
    }

    public boolean f() {
        return g("is_eliminated");
    }

    @Override // defpackage.bpq
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return a() == ((bpg) obj).a();
    }

    @Override // defpackage.bpq
    public int hashCode() {
        return a();
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpg bpgVar) {
        if (bpgVar == null) {
            return 0;
        }
        if (e() == null || bpgVar.e() == null) {
            return 0;
        }
        int compareTo = e().compareTo(bpgVar.e());
        if (compareTo == 0) {
            if (d() == null || bpgVar.d() == null) {
                return 0;
            }
            return d().compareTo(bpgVar.d());
        }
        return compareTo;
    }
}
