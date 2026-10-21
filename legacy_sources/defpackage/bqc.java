package defpackage;
// [removed legacy tracker import]
import com.samsung.android.sdk.richnotification.Utilities;
import java.util.Iterator;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bqc extends bpq implements Comparable<bqc> {
    public bqc(JSONObject jSONObject) {
        super(jSONObject);
    }

    public bqc(String str, String str2) {
        super(new JSONObject());
        ar().put(Utilities.DB_KEY_IMAGE_NAME, str2);
        ar().put("id", str);
    }

    public String a() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    public String b() {
        return c() ? e("id") : ar().toString();
    }

    public boolean c() {
        String e = e("id");
        return "-1".equals(e) || "-2".equals(e);
    }

    public String d() {
        return e(ServerProtocol.DIALOG_PARAM_AUTH_TYPE);
    }

    @Override // defpackage.bpq
    public String toString() {
        return new StringBuilder(a()).toString();
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bqc bqcVar) {
        if (a() == null) {
            return -1;
        }
        if (bqcVar == null) {
            return 1;
        }
        return a().compareTo(bqcVar.a());
    }

    public boolean e() {
        return "pairing".equals(d());
    }

    public String f() {
        return e("model");
    }

    public void a(bpq bpqVar) {
        try {
            Iterator<String> keys = bpqVar.ar().keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!"code".equals(next)) {
                    ar().put(next, bpqVar.ar().get(next));
                }
            }
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public String g() {
        return e("wifi");
    }

    public String h() {
        return ar().toString();
    }
}
