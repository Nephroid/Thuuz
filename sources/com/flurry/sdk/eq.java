package com.flurry.sdk;

import java.lang.Thread;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class eq {
    private static eq a;
    private final Map<Thread.UncaughtExceptionHandler, Void> c = new WeakHashMap();
    private final Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();

    public static synchronized eq a() {
        eq eqVar;
        synchronized (eq.class) {
            if (a == null) {
                a = new eq();
            }
            eqVar = a;
        }
        return eqVar;
    }

    public void a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        synchronized (this.c) {
            this.c.put(uncaughtExceptionHandler, null);
        }
    }

    private Set<Thread.UncaughtExceptionHandler> b() {
        Set<Thread.UncaughtExceptionHandler> keySet;
        synchronized (this.c) {
            keySet = this.c.keySet();
        }
        return keySet;
    }

    private eq() {
        Thread.setDefaultUncaughtExceptionHandler(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Thread thread, Throwable th) {
        Iterator<Thread.UncaughtExceptionHandler> it = b().iterator();
        while (it.hasNext()) {
            try {
                it.next().uncaughtException(thread, th);
            } catch (Throwable th2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Thread thread, Throwable th) {
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }

    /* loaded from: classes.dex */
    final class a implements Thread.UncaughtExceptionHandler {
        private a() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            eq.this.a(thread, th);
            eq.this.b(thread, th);
        }
    }
}
