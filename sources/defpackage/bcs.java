package defpackage;

import android.content.Intent;
import android.os.PowerManager;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.data.GameData;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcs implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ ThuuzService b;

    public bcs(ThuuzService thuuzService, Intent intent) {
        this.b = thuuzService;
        this.a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock wakeLock2;
        GameData a;
        try {
            wakeLock2 = this.b.C;
            wakeLock2.acquire();
            this.b.m().a(System.currentTimeMillis());
            String stringExtra = this.a.getStringExtra("id");
            if (stringExtra == null) {
                this.b.H().severe("cannot handle game alert without id. All extras: " + bzy.logAllStringExtras(this.a));
                return;
            }
            int parseInt = Integer.parseInt(stringExtra);
            if (parseInt == -1) {
                this.b.H().info("received push validation alert");
                return;
            }
            String stringExtra2 = this.a.hasExtra("msg") ? this.a.getStringExtra("msg") : null;
            a = this.b.a(this.a, stringExtra);
            this.b.H().info("got push: " + a.w() + " at " + a.x() + " with raw gec: " + this.a.getStringExtra("gex") + " " + a.k() + "/" + a.l());
            this.b.a(parseInt, a, stringExtra2);
        } catch (Throwable th) {
            this.b.H().log(Level.SEVERE, "cannot handle pushed game: ", th);
        } finally {
            this.b.stopForeground(true);
            wakeLock = this.b.C;
            wakeLock.release();
        }
    }
}
