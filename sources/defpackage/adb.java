package defpackage;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@zy
/* loaded from: classes.dex */
public class adb<T> implements Future<T> {
    private final T a;

    public adb(T t) {
        this.a = t;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public T get() {
        return this.a;
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) {
        return this.a;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }
}
