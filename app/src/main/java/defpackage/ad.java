package defpackage;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes.dex */
public class ad extends Thread {
    private final BlockingQueue<Request<?>> a;
    private final ac b;
    private final u c;
    private final ak d;
    private volatile boolean e = false;

    public ad(BlockingQueue<Request<?>> blockingQueue, ac acVar, u uVar, ak akVar) {
        this.a = blockingQueue;
        this.b = acVar;
        this.c = uVar;
        this.d = akVar;
    }

    public void a() {
        this.e = true;
        interrupt();
    }

    @TargetApi(14)
    private void a(Request<?> request) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.b());
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                Request<?> take = this.a.take();
                try {
                    take.a("network-queue-take");
                    if (take.g()) {
                        take.b("network-discard-cancelled");
                    } else {
                        a(take);
                        ae a = this.b.a(take);
                        take.a("network-http-complete");
                        if (a.d && take.v()) {
                            take.b("not-modified");
                        } else {
                            ah<?> a2 = take.a(a);
                            take.a("network-parse-complete");
                            if (take.q() && a2.b != null) {
                                this.c.a(take.d(), a2.b);
                                take.a("network-cache-written");
                            }
                            take.u();
                            this.d.a(take, a2);
                        }
                    }
                } catch (VolleyError e) {
                    e.a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    a(take, e);
                } catch (Exception e2) {
                    am.a(e2, "Unhandled exception %s", e2.toString());
                    VolleyError volleyError = new VolleyError(e2);
                    volleyError.a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.d.a(take, volleyError);
                }
            } catch (InterruptedException e3) {
                if (this.e) {
                    return;
                }
            }
        }
    }

    private void a(Request<?> request, VolleyError volleyError) {
        this.d.a(request, request.a(volleyError));
    }
}
