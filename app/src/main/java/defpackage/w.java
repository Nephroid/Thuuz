package defpackage;

import android.os.Process;
import com.android.volley.Request;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes.dex */
public class w extends Thread {
    private static final boolean a = am.b;
    private final BlockingQueue<Request<?>> b;
    private final BlockingQueue<Request<?>> c;
    private final u d;
    private final ak e;
    private volatile boolean f = false;

    public w(BlockingQueue<Request<?>> blockingQueue, BlockingQueue<Request<?>> blockingQueue2, u uVar, ak akVar) {
        this.b = blockingQueue;
        this.c = blockingQueue2;
        this.d = uVar;
        this.e = akVar;
    }

    public void a() {
        this.f = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (a) {
            am.a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.d.a();
        while (true) {
            try {
                Request<?> take = this.b.take();
                take.a("cache-queue-take");
                if (take.g()) {
                    take.b("cache-discard-canceled");
                } else {
                    v a2 = this.d.a(take.d());
                    if (a2 == null) {
                        take.a("cache-miss");
                        this.c.put(take);
                    } else if (a2.a()) {
                        take.a("cache-hit-expired");
                        take.a(a2);
                        this.c.put(take);
                    } else {
                        take.a("cache-hit");
                        ah<?> a3 = take.a(new ae(a2.a, a2.f));
                        take.a("cache-hit-parsed");
                        if (!a2.b()) {
                            this.e.a(take, a3);
                        } else {
                            take.a("cache-hit-refresh-needed");
                            take.a(a2);
                            a3.d = true;
                            this.e.a(take, a3, new x(this, take));
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.f) {
                    return;
                }
            }
        }
    }
}
