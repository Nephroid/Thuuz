package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class byk extends bya {
    private int a;
    private int b;

    public byk(App app, int i, int i2) {
        super("fantasy_disconnect", app, true);
        this.a = i;
        this.b = i2;
        try {
            b();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
            a(-1);
        }
    }

    protected void b() {
        if (this.a > 0) {
            a("site_id", String.valueOf(this.a));
            a("competition_id", String.valueOf(this.b));
        }
    }
}
