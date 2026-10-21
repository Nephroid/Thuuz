package defpackage;

import com.samsung.android.sdk.richnotification.Utilities;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class abt implements abr<um> {
    @Override // defpackage.abr
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public um a(abo aboVar, JSONObject jSONObject) {
        return new um(jSONObject.getString("headline"), aboVar.a(jSONObject, Utilities.DB_KEY_IMAGE, true).get(), jSONObject.getString("body"), aboVar.a(jSONObject, "secondary_image", false).get(), jSONObject.getString("call_to_action"), jSONObject.getString("attribution"));
    }
}
