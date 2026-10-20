package defpackage;

import android.text.TextUtils;
import com.facebook.Response;
import com.google.android.gms.internal.gv;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class abp implements vg {
    final /* synthetic */ sm a;
    final /* synthetic */ ada b;
    final /* synthetic */ abo c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abp(abo aboVar, sm smVar, ada adaVar) {
        this.c = aboVar;
        this.a = smVar;
        this.b = adaVar;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        this.a.b("/nativeAdPreProcess");
        try {
            String str = map.get(Response.SUCCESS_KEY);
            if (!TextUtils.isEmpty(str)) {
                this.b.a(new JSONObject(str).getJSONArray("ads").getJSONObject(0));
                return;
            }
        } catch (JSONException e) {
            adn.b("Malformed native JSON response.", e);
        }
        this.c.a(0);
        kb.a(this.c.b(), "Unable to set the ad state error!");
        this.b.a(null);
    }
}
