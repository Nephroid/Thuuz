package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public class xg {
    private final boolean a;
    private final boolean b;
    private final boolean c;
    private final boolean d;
    private final boolean e;

    private xg(xi xiVar) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        z = xiVar.a;
        this.a = z;
        z2 = xiVar.b;
        this.b = z2;
        z3 = xiVar.c;
        this.c = z3;
        z4 = xiVar.d;
        this.d = z4;
        z5 = xiVar.e;
        this.e = z5;
    }

    public JSONObject a() {
        try {
            return new JSONObject().put("sms", this.a).put("tel", this.b).put("calendar", this.c).put("storePicture", this.d).put("inlineVideo", this.e);
        } catch (JSONException e) {
            adn.b("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
