package defpackage;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class acu implements Callable<Void> {
    final /* synthetic */ Runnable a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public acu(Runnable runnable) {
        this.a = runnable;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        this.a.run();
        return null;
    }
}
