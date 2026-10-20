package defpackage;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
final class h {
    private static final h a = new h();
    private final ExecutorService b;
    private final Executor c;

    private static boolean c() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains("android");
    }

    private h() {
        this.b = !c() ? Executors.newCachedThreadPool() : b.a();
        this.c = new j();
    }

    public static ExecutorService a() {
        return a.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor b() {
        return a.c;
    }
}
