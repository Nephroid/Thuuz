package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class d implements Executor {
    private d() {
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
