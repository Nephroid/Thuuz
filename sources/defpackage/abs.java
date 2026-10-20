package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class abs implements abr<ul> {
    @Override // defpackage.abr
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ul a(abo aboVar, JSONObject jSONObject) {
        return new ul(jSONObject.getString("headline"), aboVar.a(jSONObject, Utilities.DB_KEY_IMAGE, true).get(), jSONObject.getString("body"), aboVar.a(jSONObject, "app_icon", true).get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString("store"), jSONObject.optString("price"));
    }
}
