package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.locks.Lock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class hy extends Handler {
    final /* synthetic */ hs a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public hy(hs hsVar, Looper looper) {
        super(looper);
        this.a = hsVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Lock lock;
        Lock lock2;
        boolean g;
        if (message.what != 1) {
            Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
            return;
        }
        lock = this.a.d;
        lock.lock();
        try {
            if (!this.a.c() && !this.a.d()) {
                g = this.a.g();
                if (g) {
                    hs.h(this.a);
                    this.a.a();
                }
            }
        } finally {
            lock2 = this.a.d;
            lock2.unlock();
        }
    }
}
