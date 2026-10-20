package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;
import org.apache.http.message.BasicNameValuePair;

/* loaded from: classes.dex */
public class bym extends bya {
    private int a;
    private String b;

    public bym(App app, String str, int i) {
        super("fantasy_post_discussion", app, true);
        this.a = i;
        this.b = str;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    protected void b() {
        if (this.a > 0) {
            a("team_id", String.valueOf(this.a));
            a("type", "invite");
            if (this.b != null && this.b.length() > 0) {
                a(new BasicNameValuePair("message", this.b));
            }
        }
    }
}
