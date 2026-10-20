package defpackage;

import java.lang.Throwable;

/* loaded from: classes.dex */
public class cbf<T, E extends Throwable> {
    private final boolean a;
    private final T b;
    private final E c;

    public cbf(T t) {
        this.a = true;
        this.b = t;
        this.c = null;
    }

    public cbf(E e) {
        this.a = false;
        this.b = null;
        this.c = e;
    }

    public boolean a() {
        return this.a;
    }

    public T b() {
        return this.b;
    }

    public E c() {
        return this.c;
    }

    public String toString() {
        return "{hasResult=" + this.a + " result=" + this.b + " exception=" + this.c + "}";
    }
}
