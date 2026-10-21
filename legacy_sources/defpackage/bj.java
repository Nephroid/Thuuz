package defpackage;

import com.android.volley.ParseError;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bj extends bk<JSONObject> {
    public bj(int i, String str, JSONObject jSONObject, aj<JSONObject> ajVar, ai aiVar) {
        super(i, str, jSONObject == null ? null : jSONObject.toString(), ajVar, aiVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bk, com.android.volley.Request
    public ah<JSONObject> a(ae aeVar) {
        try {
            return ah.a(new JSONObject(new String(aeVar.b, aw.a(aeVar.c))), aw.a(aeVar));
        } catch (UnsupportedEncodingException e) {
            return ah.a(new ParseError(e));
        } catch (JSONException e2) {
            return ah.a(new ParseError(e2));
        }
    }
}
