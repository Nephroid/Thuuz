package defpackage;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.VolleyError;
import com.thuuz.gtv.App;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzc extends bj {
    private final bza a;

    public bzc(bza bzaVar, aj<JSONObject> ajVar, ai aiVar) {
        super(1, bzaVar.C(), null, ajVar, aiVar);
        this.a = bzaVar;
        a((al) new y(15000, 1, 1.0f));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public Map<String, String> m() {
        return this.a.D();
    }

    @Override // defpackage.bk, com.android.volley.Request
    public byte[] p() {
        try {
            Map<String, String> m = m();
            if (m == null || m.size() <= 0) {
                return null;
            }
            return a(m, n());
        } catch (Throwable th) {
            w().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    private byte[] a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(URLEncoder.encode(entry.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue(), str));
                sb.append('&');
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public Logger w() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public bza x() {
        return this.a;
    }

    @Override // defpackage.bj, defpackage.bk, com.android.volley.Request
    protected ah<JSONObject> a(ae aeVar) {
        try {
            return super.a(aeVar);
        } catch (OutOfMemoryError e) {
            this.a.u();
            return ah.a(new ParseError());
        }
    }

    @Override // com.android.volley.Request
    public void b(VolleyError volleyError) {
        try {
            super.b(volleyError);
        } catch (Throwable th) {
            w().log(Level.SEVERE, "problem delivering volley error: ", th);
            super.b(new NetworkError());
        }
    }
}
