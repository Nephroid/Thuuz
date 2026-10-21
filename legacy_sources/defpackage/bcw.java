package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcw implements Runnable {
    final /* synthetic */ AbstractActivity a;

    public bcw(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!this.a.isDestroyed() && !this.a.isFinishing()) {
                this.a.n();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem binding to service: ", th);
        }
    }
}
