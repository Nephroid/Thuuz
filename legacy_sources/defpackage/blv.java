package defpackage;

import android.app.AlertDialog;
import com.thuuz.gtv.activity.nobar.SplashActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blv implements Runnable {
    final /* synthetic */ bpo a;
    final /* synthetic */ SplashActivity b;

    public blv(SplashActivity splashActivity, bpo bpoVar) {
        this.b = splashActivity;
        this.a = bpoVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.a == null || this.a.b()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this.b.k());
                builder.setMessage("Failed to download data. Try again?");
                builder.setCancelable(true);
                AlertDialog create = builder.create();
                create.setButton(-1, "Ok", new blw(this, create));
                create.setButton(-2, "Quit", new blx(this, create));
                create.setOnCancelListener(new bly(this));
                this.b.a(create);
                create.show();
            } else {
                this.b.j().f(true);
                this.b.aG();
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "Splash: Cannot handle: " + th, th);
        } finally {
            this.b.f = null;
        }
    }
}
