package defpackage;

import com.facebook.internal.NativeProtocol;
import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzq extends bya {
    private String a;
    private String b;

    public bzq(App app, String str) {
        super("shorten_url", app, false);
        this.b = str;
        this.a = str;
        try {
            if (this.a == null || this.a.length() == 0) {
                throw new IllegalArgumentException("bad values");
            }
            a(NativeProtocol.IMAGE_URL_KEY, this.a);
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        return a(jSONObject);
    }

    @Override // defpackage.bya
    protected void a(int i, JSONObject jSONObject) {
        this.b = this.a;
    }

    private int a(JSONObject jSONObject) {
        int v = v();
        try {
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Problem getting shortened url from server: ", th);
            this.b = this.a;
            v = -1;
        }
        if (!jSONObject.has("short_url")) {
            getLogger().severe("cannot find 'short_url' key in shorten_url response");
            return -1;
        }
        this.b = jSONObject.getString("short_url");
        if (this.b == null || this.b.length() == 0) {
            getLogger().severe("url_shorten returned empty for input: " + this.a);
            return -1;
        }
        return v;
    }

    public String b() {
        return this.b;
    }
}
