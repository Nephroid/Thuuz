package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpl extends bpq implements Comparable<bpl> {
    public bpl(JSONObject jSONObject) {
        super(jSONObject);
    }

    public int a() {
        try {
            if (ar().has("uid")) {
                return ar().getInt("uid");
            }
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
        return -1;
    }

    public void a(int i) {
        try {
            ar().put("uid", i);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public String b() {
        try {
            if (ar().has("sid")) {
                return ar().getString("sid");
            }
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
        return null;
    }

    public boolean c() {
        return d();
    }

    public boolean d() {
        try {
            if (ar().has("is_fol")) {
                return ar().getBoolean("is_fol");
            }
            return false;
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return false;
        }
    }

    public void a(boolean z) {
        b(z);
    }

    public void b(boolean z) {
        try {
            ar().put("is_fol", z);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public String e() {
        try {
            return ar().getString(Utilities.DB_KEY_IMAGE_NAME);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return null;
        }
    }

    public void a(String str) {
        try {
            ar().put(Utilities.DB_KEY_IMAGE_NAME, str);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public String f() {
        try {
            return ar().getString("fname");
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return null;
        }
    }

    public String g() {
        try {
            return ar().getString("lname");
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return null;
        }
    }

    public void b(String str) {
        try {
            ar().put("fname", str);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public void c(String str) {
        try {
            ar().put("lname", str);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    @Override // defpackage.bpq
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bpl bplVar = (bpl) obj;
        return (a() == -1 || bplVar.a() == -1 || a() == bplVar.a()) && (b() == null || b().equals(bplVar.b()));
    }

    @Override // defpackage.bpq
    public int hashCode() {
        int a = a();
        String b = b();
        return (b != null ? b.hashCode() : 0) + (a * 31);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpl bplVar) {
        if (e() == null || bplVar.e() == null) {
            return 0;
        }
        return e().toLowerCase().compareTo(bplVar.e().toLowerCase());
    }

    public void c(boolean z) {
        try {
            ar().put("is_invited", z);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public boolean h() {
        try {
            if (ar().has("is_invited")) {
                return ar().getBoolean("is_invited");
            }
            return false;
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
        String str;
        try {
            if (!jSONObject.has("fname")) {
                str = "";
            } else {
                str = jSONObject.getString("fname");
            }
            String str2 = "";
            if (jSONObject.has("lname")) {
                str2 = jSONObject.getString("lname");
            }
            a(str + " " + str2);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }
}
