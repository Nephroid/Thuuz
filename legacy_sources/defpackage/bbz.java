package defpackage;

import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bbz implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bbz(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.Y();
        } catch (Throwable th) {
            this.a.H().log(Level.SEVERE, "problem initializing platform: ", th);
        }
    }
}
