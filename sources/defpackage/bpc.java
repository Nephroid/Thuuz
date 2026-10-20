package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpc extends bpq implements Comparable<bpc> {
    private int a;
    private bpd c;
    private boolean d;

    public bpc(JSONObject jSONObject) {
        super(jSONObject);
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpc bpcVar) {
        if (bpcVar == null || b() == null || bpcVar.b() == null) {
            return 0;
        }
        return b().compareTo(bpcVar.b());
    }

    public String b() {
        try {
            return ar().getString(Utilities.DB_KEY_IMAGE_NAME);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return null;
        }
    }

    public void a(bpd bpdVar) {
        this.c = bpdVar;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public bpd c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public boolean e() {
        try {
            return ar().getBoolean("following");
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return false;
        }
    }

    public String f() {
        try {
            return ar().getString("title");
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return null;
        }
    }

    public void b(boolean z) {
        try {
            ar().put("following", z);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }
}
