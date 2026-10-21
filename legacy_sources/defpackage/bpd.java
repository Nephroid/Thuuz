package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpd extends bpq implements Comparable<bpd> {
    private int a;
    private Set<bpc> c;

    public bpd(JSONObject jSONObject) {
        super(jSONObject);
        this.c = new TreeSet();
    }

    public void a(int i) {
        this.a = i;
    }

    public void a(Set<bpc> set) {
        this.c.clear();
        this.c.addAll(set);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpd bpdVar) {
        if (bpdVar == null || a() == null || bpdVar.a() == null) {
            return 0;
        }
        return a().compareTo(bpdVar.a());
    }

    public String a() {
        try {
            return ar().getString(Utilities.DB_KEY_IMAGE_NAME);
        } catch (JSONException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            return null;
        }
    }

    public Set<bpc> b() {
        return this.c;
    }
}
