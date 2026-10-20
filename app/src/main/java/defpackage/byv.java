package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;

/* loaded from: classes.dex */
public class byv extends bya {
    public byv(App app, int i) {
        this(app, i, false);
    }

    public byv(App app, int i, boolean z) {
        super("follow_user", app, true);
        try {
            if (i <= 0) {
                throw new IllegalArgumentException("bad values");
            }
            a(new BasicNameValuePair("followed_user", String.valueOf(i)));
            if (z) {
                a("delete", "True");
            }
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }
}
