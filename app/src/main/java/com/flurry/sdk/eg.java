package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* loaded from: classes.dex */
public class eg {
    private static eg a;
    private final Context b;
    private final Handler c = new Handler(Looper.getMainLooper());
    private final Handler d;

    public static synchronized void a(Context context) {
        synchronized (eg.class) {
            if (a == null) {
                if (context == null) {
                    throw new IllegalArgumentException("Context cannot be null");
                }
                a = new eg(context);
            }
        }
    }

    public static eg a() {
        return a;
    }

    private eg(Context context) {
        this.b = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("BackgroundHandler");
        handlerThread.start();
        this.d = new Handler(handlerThread.getLooper());
    }

    public Context b() {
        return this.b;
    }

    public PackageManager c() {
        return this.b.getPackageManager();
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            this.c.post(runnable);
        }
    }
}
