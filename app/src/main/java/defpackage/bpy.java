package defpackage;

import android.net.http.Headers;
import com.samsung.android.sdk.richnotification.Utilities;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpy extends bpq implements Comparable<bpy> {
    public bpy(JSONObject jSONObject) {
        super(jSONObject);
    }

    public String a() {
        return e("id");
    }

    public String b() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    public String c() {
        return e(Headers.LOCATION);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpy bpyVar) {
        if (b() == null) {
            return -1;
        }
        if (bpyVar == null) {
            return 1;
        }
        return b().compareTo(bpyVar.b());
    }

    public String d() {
        return e("ctv_type");
    }
}
