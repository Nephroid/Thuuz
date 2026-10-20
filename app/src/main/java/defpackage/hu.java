package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.concurrent.locks.Lock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class hu implements ho {
    final /* synthetic */ hs a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public hu(hs hsVar) {
        this.a = hsVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000e. Please report as an issue. */
    @Override // defpackage.ho
    public void a(int i) {
        Lock lock;
        Lock lock2;
        boolean g;
        long j;
        lock = this.a.d;
        lock.lock();
        try {
            this.a.a(i);
            switch (i) {
                case 1:
                    g = this.a.g();
                    if (g) {
                        return;
                    }
                    this.a.l = 2;
                    Handler handler = this.a.b;
                    Message obtainMessage = this.a.b.obtainMessage(1);
                    j = this.a.o;
                    handler.sendMessageDelayed(obtainMessage, j);
                    return;
                case 2:
                    this.a.a();
                    return;
                default:
                    return;
            }
        } finally {
            lock2 = this.a.d;
            lock2.unlock();
        }
    }

    @Override // defpackage.ho
    public void a(Bundle bundle) {
        Lock lock;
        Lock lock2;
        int i;
        Bundle bundle2;
        lock = this.a.d;
        lock.lock();
        try {
            i = this.a.k;
            if (i == 1) {
                if (bundle != null) {
                    bundle2 = this.a.p;
                    bundle2.putAll(bundle);
                }
                this.a.e();
            }
        } finally {
            lock2 = this.a.d;
            lock2.unlock();
        }
    }
}
