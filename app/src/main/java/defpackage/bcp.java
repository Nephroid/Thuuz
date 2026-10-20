package defpackage;

import android.os.PowerManager;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcp implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ThuuzService b;

    public bcp(ThuuzService thuuzService, int i) {
        this.b = thuuzService;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock wakeLock2;
        try {
            wakeLock2 = this.b.C;
            wakeLock2.acquire();
            this.b.H().info("handling delete fantasy team to Thuuz");
            this.b.s(this.a);
        } catch (Throwable th) {
            this.b.H().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.b.stopForeground(true);
            wakeLock = this.b.C;
            wakeLock.release();
        }
    }
}
