package defpackage;

import android.os.Handler;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class aa implements Executor {
    final /* synthetic */ Handler a;
    final /* synthetic */ z b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aa(z zVar, Handler handler) {
        this.b = zVar;
        this.a = handler;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.a.post(runnable);
    }
}
