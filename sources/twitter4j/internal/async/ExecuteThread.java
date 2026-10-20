package twitter4j.internal.async;

import twitter4j.internal.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ExecuteThread extends Thread {
    private static Logger logger = Logger.getLogger(ExecuteThread.class);
    private boolean alive;
    DispatcherImpl q;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExecuteThread(String str, DispatcherImpl dispatcherImpl, int i) {
        super(str + "[" + i + "]");
        this.alive = true;
        this.q = dispatcherImpl;
    }

    public void shutdown() {
        this.alive = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (this.alive) {
            Runnable poll = this.q.poll();
            if (poll != null) {
                try {
                    poll.run();
                } catch (Exception e) {
                    logger.error("Got an exception while running a task:", e);
                }
            }
        }
    }
}
