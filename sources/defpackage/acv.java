package defpackage;

import android.os.Process;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
public final class acv<T> implements Callable<T> {
    final /* synthetic */ Callable a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public acv(Callable callable) {
        this.a = callable;
    }

    @Override // java.util.concurrent.Callable
    public T call() {
        try {
            Process.setThreadPriority(10);
            return (T) this.a.call();
        } catch (Exception e) {
            acl.a(e);
            return null;
        }
    }
}
