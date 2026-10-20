package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;

@zy
/* loaded from: classes.dex */
public final class act {
    private static final ThreadFactory a = new acw();
    private static final ExecutorService b = Executors.newFixedThreadPool(10, a);

    public static Future<Void> a(Runnable runnable) {
        return a(new acu(runnable));
    }

    public static <T> Future<T> a(Callable<T> callable) {
        try {
            return b.submit(new acv(callable));
        } catch (RejectedExecutionException e) {
            adn.d("Thread execution is rejected.", e);
            return new adb(null);
        }
    }
}
