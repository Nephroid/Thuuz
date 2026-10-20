package defpackage;

import com.facebook.internal.ServerProtocol;
import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzp extends bya {
    private String a;

    public bzp(App app, String str, String str2, boolean z, String str3, boolean z2) {
        super("create_share", app, true);
        this.a = "";
        try {
            if (str == null) {
                throw new IllegalArgumentException("bad values");
            }
            a(new BasicNameValuePair("content_type", str));
            a(new BasicNameValuePair("object_id", str2));
            a(new BasicNameValuePair("description", str3));
            a(new BasicNameValuePair("notify", z2 ? "True" : "False"));
            a(new BasicNameValuePair("public", z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false"));
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    public void a(String str) {
        this.a = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        return v();
    }

    @Override // defpackage.bya
    public int o() {
        if (this.a.length() > 0) {
            a(new BasicNameValuePair("tagged_user", this.a));
        }
        return super.o();
    }
}
