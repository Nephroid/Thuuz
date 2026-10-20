package twitter4j.internal.async;

import java.util.LinkedList;
import java.util.List;
import twitter4j.conf.Configuration;

/* loaded from: classes.dex */
final class DispatcherImpl implements Dispatcher {
    private ExecuteThread[] threads;
    private final List<Runnable> q = new LinkedList();
    final Object ticket = new Object();
    private boolean active = true;

    public DispatcherImpl(Configuration configuration) {
        this.threads = new ExecuteThread[configuration.getAsyncNumThreads()];
        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new ExecuteThread("Twitter4J Async Dispatcher", this, i);
            this.threads[i].setDaemon(true);
            this.threads[i].start();
        }
        Runtime.getRuntime().addShutdownHook(new Thread() { // from class: twitter4j.internal.async.DispatcherImpl.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (DispatcherImpl.this.active) {
                    DispatcherImpl.this.shutdown();
                }
            }
        });
    }

    @Override // twitter4j.internal.async.Dispatcher
    public synchronized void invokeLater(Runnable runnable) {
        synchronized (this.q) {
            this.q.add(runnable);
        }
        synchronized (this.ticket) {
            this.ticket.notify();
        }
    }

    public Runnable poll() {
        Runnable remove;
        while (this.active) {
            synchronized (this.q) {
                if (this.q.size() > 0 && (remove = this.q.remove(0)) != null) {
                    return remove;
                }
            }
            synchronized (this.ticket) {
                try {
                    this.ticket.wait();
                } catch (InterruptedException e) {
                }
            }
        }
        return null;
    }

    @Override // twitter4j.internal.async.Dispatcher
    public synchronized void shutdown() {
        synchronized (this) {
            if (this.active) {
                this.active = false;
                for (ExecuteThread executeThread : this.threads) {
                    executeThread.shutdown();
                }
                synchronized (this.ticket) {
                    this.ticket.notify();
                }
            }
        }
    }
}
