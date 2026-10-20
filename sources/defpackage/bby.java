package defpackage;

import android.os.Process;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bby implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bby(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        try {
            Process.setThreadPriority(1);
            Thread.sleep(12000L);
            this.a.an();
            this.a.Z();
            this.a.Y();
        } catch (Throwable th) {
            z = this.a.aa;
            if (!z) {
                this.a.H().log(Level.SEVERE, "problem in thread running doOnetime: ", th);
            }
        }
    }
}
