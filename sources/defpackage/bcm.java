package defpackage;

import android.os.PowerManager;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcm implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bcm(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock wakeLock2;
        Object obj;
        try {
            wakeLock2 = this.a.C;
            wakeLock2.acquire();
            obj = this.a.x;
            synchronized (obj) {
                this.a.H().info("handling registration to Thuuz");
                new byg(this.a.m()).l();
            }
        } catch (Throwable th) {
            this.a.H().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.stopForeground(true);
            wakeLock = this.a.C;
            wakeLock.release();
        }
    }
}
