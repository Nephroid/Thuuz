package defpackage;

import com.thuuz.gtv.ThuuzService;
import java.util.TimerTask;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcf extends TimerTask {
    final /* synthetic */ ThuuzService a;

    public bcf(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        try {
            this.a.au();
        } catch (Throwable th) {
            this.a.H().log(Level.SEVERE, "problem: ", th);
        }
    }
}
