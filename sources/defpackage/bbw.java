package defpackage;

import android.os.PowerManager;
import com.thuuz.gtv.ThuuzService;

/* loaded from: classes.dex */
public class bbw implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bbw(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock wakeLock2;
        try {
            wakeLock2 = this.a.C;
            wakeLock2.acquire();
            this.a.az();
        } finally {
            this.a.stopForeground(true);
            wakeLock = this.a.C;
            wakeLock.release();
        }
    }
}
