package defpackage;

import android.os.PowerManager;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcq implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bcq(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock wakeLock2;
        try {
            wakeLock2 = this.a.C;
            wakeLock2.acquire();
            this.a.H().info("handling push registration from Google");
            this.a.G();
        } catch (Throwable th) {
            this.a.H().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.stopForeground(true);
            wakeLock = this.a.C;
            wakeLock.release();
        }
    }
}
