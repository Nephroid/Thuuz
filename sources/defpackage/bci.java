package defpackage;

import android.os.PowerManager;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bci implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bci(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock wakeLock2;
        Thread thread;
        Thread thread2;
        try {
            wakeLock2 = this.a.C;
            wakeLock2.acquire();
            try {
                thread2 = this.a.u;
                if (thread2 != null && thread2.isAlive()) {
                    thread2.interrupt();
                    thread2.join();
                }
            } catch (Throwable th) {
                this.a.H().log(Level.SEVERE, "problem: ", th);
            }
            bcj bcjVar = new bcj(this);
            this.a.u = new Thread(bcjVar);
            thread = this.a.u;
            thread.start();
        } catch (Throwable th2) {
            this.a.H().log(Level.SEVERE, "problem: ", th2);
        } finally {
            this.a.stopForeground(true);
            wakeLock = this.a.C;
            wakeLock.release();
        }
    }
}
