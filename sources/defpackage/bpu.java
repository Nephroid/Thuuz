package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpu extends bpq implements Comparable<bpu> {
    public bpu(JSONObject jSONObject) {
        super(jSONObject);
    }

    public bpu(Integer num, String str, String str2, String str3, String str4, String str5) {
        super(null);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", num.intValue());
            jSONObject.put(Utilities.DB_KEY_IMAGE_NAME, str);
            jSONObject.put("zipcode", str2);
            jSONObject.put("provider_id", str3);
            jSONObject.put("provider_name", str4);
            jSONObject.put("ctv_type", str5);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        super.b(jSONObject);
    }

    public Integer a() {
        return Integer.valueOf(f("id"));
    }

    public String b() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    public void a(String str) {
        a(Utilities.DB_KEY_IMAGE_NAME, str);
    }

    public String c() {
        return e("zipcode");
    }

    public void b(String str) {
        a("zipcode", str);
    }

    public String d() {
        return e("provider_id");
    }

    public void c(String str) {
        a("provider_id", str);
    }

    public String e() {
        return e("provider_name");
    }

    public void d(String str) {
        a("provider_name", str);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpu bpuVar) {
        if (b() == null || bpuVar.b() == null) {
            return 0;
        }
        return b().compareTo(bpuVar.b());
    }

    public boolean f() {
        String d = d();
        if (d.length() > 0 && k().equals("dish")) {
            return true;
        }
        return d.length() > 0 && (d.startsWith("DISH") || d.startsWith("ECHOST"));
    }

    public boolean g() {
        return d().compareTo("-1") == 0;
    }

    public void a(bqc bqcVar) {
        if (bqcVar == null || "-2".equals(bqcVar.b())) {
            n();
        } else {
            a("device_id", bqcVar.ar().toString());
        }
    }

    public String h() {
        return e("device_id");
    }

    public String i() {
        bqc m;
        String e = e("device_name");
        if (e.length() == 0 && (m = m()) != null) {
            String bqcVar = m.toString();
            String q = q();
            if (q.length() > 0) {
                return bqcVar + ", wifi: " + q;
            }
            return bqcVar;
        }
        return e;
    }

    private String q() {
        bqc m = m();
        return m != null ? m.e("wifi") : "";
    }

    public String j() {
        StringBuilder sb = new StringBuilder(e());
        String i = i();
        if (i != null && i.length() > 0) {
            sb.append(", device: ");
            sb.append(i);
        }
        return sb.toString();
    }

    public String k() {
        return e("ctv_type");
    }

    public void m(String str) {
        a("ctv_type", str);
    }

    public boolean l() {
        return h().length() > 0;
    }

    public bqc m() {
        String h = h();
        if (h.length() == 0) {
            return null;
        }
        try {
            return new bqc(new JSONObject(h));
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem creating tv device: ", th);
            return null;
        }
    }

    public void n() {
        try {
            ar().remove("device_id");
            ar().remove("device_name");
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean o() {
        String k = k();
        return k != null && k.length() > 0;
    }

    public boolean p() {
        String k;
        String d = d();
        boolean z = d != null && d.startsWith("DITV");
        if (!z && (k = k()) != null) {
            return k.startsWith("directv");
        }
        return z;
    }
}
