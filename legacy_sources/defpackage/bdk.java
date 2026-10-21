package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import java.util.TimerTask;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdk extends TimerTask {
    final /* synthetic */ long a;
    final /* synthetic */ AbstractActivity b;

    public bdk(AbstractActivity abstractActivity, long j) {
        this.b = abstractActivity;
        this.a = j;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        long j;
        long q;
        try {
            try {
                if (!this.b.d()) {
                    try {
                        if (j != q) {
                            return;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        return;
                    }
                }
                if (this.b.F() == null) {
                    try {
                        if (this.a != this.b.q()) {
                            this.b.e().info("resetting refresh period from: " + (this.a / 1000) + " to: " + (this.b.q() / 1000) + " sec");
                            this.b.s();
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        this.b.e().log(Level.SEVERE, "cannot handle: ", th2);
                        return;
                    }
                }
                this.b.runOnUiThread(new bdl(this));
                try {
                    if (this.a != this.b.q()) {
                        this.b.e().info("resetting refresh period from: " + (this.a / 1000) + " to: " + (this.b.q() / 1000) + " sec");
                        this.b.s();
                    }
                } catch (Throwable th3) {
                    this.b.e().log(Level.SEVERE, "cannot handle: ", th3);
                }
            } catch (Throwable th4) {
                this.b.e().log(Level.SEVERE, "cannot handle: ", th4);
                try {
                    if (this.a != this.b.q()) {
                        this.b.e().info("resetting refresh period from: " + (this.a / 1000) + " to: " + (this.b.q() / 1000) + " sec");
                        this.b.s();
                    }
                } catch (Throwable th5) {
                    this.b.e().log(Level.SEVERE, "cannot handle: ", th5);
                }
            }
        } finally {
            try {
                if (this.a != this.b.q()) {
                    this.b.e().info("resetting refresh period from: " + (this.a / 1000) + " to: " + (this.b.q() / 1000) + " sec");
                    this.b.s();
                }
            } catch (Throwable th6) {
                this.b.e().log(Level.SEVERE, "cannot handle: ", th6);
            }
        }
    }
}
