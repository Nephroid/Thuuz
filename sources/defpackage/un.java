package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class un {
    private ajr a;
    private sm b;
    private JSONObject c;

    public un(ajr ajrVar, sm smVar, JSONObject jSONObject) {
        this.a = ajrVar;
        this.b = smVar;
        this.c = jSONObject;
    }

    public void a() {
        this.a.j();
    }

    public void a(String str, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("asset", i);
            jSONObject.put("template", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ad", this.c);
            jSONObject2.put("click", jSONObject);
            this.b.a("google.afma.nativeAds.handleClick", jSONObject2);
        } catch (JSONException e) {
            adn.b("Unable to create click JSON.", e);
        }
    }
}
