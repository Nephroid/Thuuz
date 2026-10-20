package defpackage;

import com.android.volley.Request;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes.dex */
class x implements Runnable {
    final /* synthetic */ Request a;
    final /* synthetic */ w b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(w wVar, Request request) {
        this.b = wVar;
        this.a = request;
    }

    @Override // java.lang.Runnable
    public void run() {
        BlockingQueue blockingQueue;
        try {
            blockingQueue = this.b.c;
            blockingQueue.put(this.a);
        } catch (InterruptedException e) {
        }
    }
}
