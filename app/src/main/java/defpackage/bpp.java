package defpackage;

import com.facebook.internal.NativeProtocol;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpp extends bpq {
    public bpp(JSONObject jSONObject) {
        super(jSONObject);
    }

    public String a() {
        return e(NativeProtocol.IMAGE_URL_KEY);
    }

    public String b() {
        return e("thumbnail");
    }
}
