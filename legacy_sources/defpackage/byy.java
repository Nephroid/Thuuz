package defpackage;

import com.android.volley.VolleyError;
import java.io.IOException;
import java.util.logging.Level;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONException;

/* loaded from: classes.dex */
public class byy implements ai {
    private final bza a;

    public byy(bza bzaVar) {
        this.a = bzaVar;
    }

    @Override // defpackage.ai
    public void a(VolleyError volleyError) {
        try {
            this.a.a(-1);
            Throwable cause = volleyError.getCause();
            if (cause != null) {
                if (cause instanceof IOException) {
                    if (cause instanceof SSLHandshakeException) {
                        this.a.getLogger().warning("SSL Exception:" + volleyError);
                    }
                    this.a.getLogger().warning("comm problem with 'games': " + volleyError);
                } else if (cause instanceof JSONException) {
                    this.a.getLogger().warning("json parse problem with 'games': " + volleyError);
                } else if (cause instanceof OutOfMemoryError) {
                    Runtime.getRuntime().gc();
                    this.a.getLogger().severe("OoM with 'games': " + volleyError);
                    this.a.k().bX();
                }
            } else {
                this.a.getLogger().warning("failed games request: " + volleyError + " " + this.a.C());
            }
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.m();
        }
    }
}
