package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpq extends bzy {
    protected JSONObject b;

    public bpq(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    public String e(String str) {
        try {
            if (this.b.has(str)) {
                return this.b.getString(str);
            }
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "getString() key not found: " + str);
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, String str2) {
        try {
            this.b.put(str, str2);
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "setString() error: " + th.getMessage());
        }
    }

    public int f(String str) {
        try {
            if (this.b.has(str) && !k(str)) {
                return this.b.getInt(str);
            }
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "getInt() key not found: " + str);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, int i) {
        try {
            this.b.put(str, i);
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "setInt() error: " + th.getMessage());
        }
    }

    public boolean g(String str) {
        try {
            if (this.b.has(str)) {
                return this.b.getBoolean(str);
            }
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "getBoolean() key not found: " + str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, boolean z) {
        try {
            this.b.put(str, z);
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "setBoolean() error: " + th.getMessage());
        }
    }

    public String toString() {
        return this.b.toString();
    }

    public JSONObject ar() {
        return this.b;
    }

    public JSONObject h(String str) {
        try {
            if (this.b.has(str)) {
                return this.b.getJSONObject(str);
            }
        } catch (Throwable th) {
            getLogger().log(Level.WARNING, "getJsonObject() key not found: " + str);
        }
        return null;
    }

    public void b(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> i(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            for (String str2 : str.split(",")) {
                String trim = str2.trim();
                if (trim.length() != 0) {
                    arrayList.add(trim);
                }
            }
        }
        return arrayList;
    }

    public JSONArray j(String str) {
        try {
            if (this.b.has(str)) {
                return this.b.getJSONArray(str);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return new JSONArray();
    }

    public boolean k(String str) {
        return this.b.isNull(str);
    }

    public boolean l(String str) {
        return this.b.has(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bpq bpqVar = (bpq) obj;
        if (this.b != null) {
            if (this.b.toString().equals(bpqVar.b.toString())) {
                return true;
            }
        } else if (bpqVar.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.b != null) {
            return this.b.toString().hashCode();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(String str, String str2) {
        try {
            ar().put(str, str2);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(String str, boolean z) {
        try {
            ar().put(str, z);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(String str, int i) {
        try {
            ar().put(str, i);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
