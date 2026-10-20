package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class car extends bzy {
    private List<bya> b = new ArrayList();
    private ExecutorService a = new ThreadPoolExecutor(0, 16, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public void a(bya byaVar) {
        try {
            if (!this.a.isShutdown()) {
                this.b.add(byaVar);
                this.a.submit(new cas(this, byaVar));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public int a(long j) {
        try {
            this.a.shutdown();
            if (!this.a.awaitTermination(j, TimeUnit.MILLISECONDS)) {
                throw new InterruptedException("timeout waiting for tasks");
            }
            for (bya byaVar : this.b) {
                if (!byaVar.y()) {
                    return byaVar.v();
                }
            }
            return 0;
        } catch (InterruptedException e) {
            throw e;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return -1;
        }
    }

    public void a() {
        try {
            this.a.shutdownNow();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
