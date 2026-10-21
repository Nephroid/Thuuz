package defpackage;

import com.thuuz.gtv.data.GameData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpa extends bpq {
    public bpa(JSONObject jSONObject) {
        super(jSONObject);
    }

    public String a() {
        return e("callsign");
    }

    public String b() {
        return e("channels");
    }

    public String c() {
        return e("station_title");
    }

    public Date d() {
        return l("start") ? GameData.a(e("start")) : new Date();
    }

    public Date e() {
        return l("start") ? GameData.b(e("start")) : new Date();
    }

    public int f() {
        return f("duration_min");
    }

    public String g() {
        String a = a();
        if (a.length() == 0) {
            a = c();
        }
        return a + " (" + b() + ")";
    }

    public List<String> h() {
        ArrayList arrayList = new ArrayList();
        for (String str : e("callsign_alt").split(",")) {
            if (str.length() > 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public int i() {
        try {
            return Integer.parseInt(b().split(",")[0].trim());
        } catch (Throwable th) {
            return 0;
        }
    }

    @Override // defpackage.bpq
    public String toString() {
        return "broadcast with channels: " + b() + " starting at " + d();
    }

    public List<String> j() {
        return i(b());
    }

    public Date k() {
        return new Date(d().getTime() + (f() * 60 * 1000));
    }
}
