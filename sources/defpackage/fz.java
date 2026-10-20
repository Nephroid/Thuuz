package defpackage;

import com.google.android.gms.analytics.r;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class fz extends TimerTask {
    final /* synthetic */ r a;

    private fz(r rVar) {
        this.a = rVar;
    }

    public /* synthetic */ fz(r rVar, fx fxVar) {
        this(rVar);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        r.a aVar;
        Timer timer;
        long j;
        Queue queue;
        long j2;
        long j3;
        afx afxVar;
        aVar = this.a.b;
        if (aVar == r.a.CONNECTED_SERVICE) {
            queue = this.a.i;
            if (queue.isEmpty()) {
                j2 = this.a.a;
                j3 = this.a.s;
                long j4 = j2 + j3;
                afxVar = this.a.r;
                if (j4 < afxVar.b()) {
                    gn.c("Disconnecting due to inactivity");
                    this.a.l();
                    return;
                }
            }
        }
        timer = this.a.m;
        fz fzVar = new fz(this.a);
        j = this.a.s;
        timer.schedule(fzVar, j);
    }
}
