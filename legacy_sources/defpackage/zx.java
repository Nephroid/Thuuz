package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.internal.gt;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.LinkedList;

@zy
/* loaded from: classes.dex */
public class zx implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler a;
    private Thread.UncaughtExceptionHandler b;
    private Context c;
    private gt d;

    public zx(Context context, gt gtVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Thread.UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.a = uncaughtExceptionHandler;
        this.b = uncaughtExceptionHandler2;
        this.c = context;
        this.d = gtVar;
    }

    private String a() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        return str2.startsWith(str) ? str2 : str + " " + str2;
    }

    public static zx a(Context context, Thread thread, gt gtVar) {
        if (context == null || thread == null || gtVar == null) {
            return null;
        }
        acl.m();
        if (!a(context)) {
            return null;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
        zx zxVar = new zx(context, gtVar, uncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
        if (uncaughtExceptionHandler != null && (uncaughtExceptionHandler instanceof zx)) {
            return (zx) uncaughtExceptionHandler;
        }
        try {
            thread.setUncaughtExceptionHandler(zxVar);
            return zxVar;
        } catch (SecurityException e) {
            adn.c("Fail to set UncaughtExceptionHandler.", e);
            return null;
        }
    }

    private static boolean a(Context context) {
        Bundle m = acl.m();
        return m != null && m.getBoolean("gads:sdk_crash_report_enabled", false);
    }

    private Throwable d(Throwable th) {
        Throwable th2;
        Bundle m = acl.m();
        if (m != null && m.getBoolean("gads:sdk_crash_report_full_stacktrace", false)) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th3 = null;
        while (!linkedList.isEmpty()) {
            Throwable th4 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th4.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean z = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (a(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    z = true;
                } else if (b(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                } else {
                    arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                }
            }
            if (z) {
                th2 = th3 == null ? new Throwable(th4.getMessage()) : new Throwable(th4.getMessage(), th3);
                th2.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            } else {
                th2 = th3;
            }
            th3 = th2;
        }
        return th3;
    }

    protected boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.startsWith("com.google.android.gms.ads") && !str.startsWith("com.google.ads")) {
            try {
                return Class.forName(str).isAnnotationPresent(zy.class);
            } catch (Exception e) {
                adn.a("Fail to check class type for class " + str, e);
                return false;
            }
        }
        return true;
    }

    protected boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        while (th != null) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (a(stackTraceElement.getClassName())) {
                    z2 = true;
                }
                if (getClass().getName().equals(stackTraceElement.getClassName())) {
                    z = true;
                }
            }
            th = th.getCause();
        }
        return z2 && !z;
    }

    public void b(Throwable th) {
        Throwable d;
        if (a(this.c) && (d = d(th)) != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(c(d));
            acx.a(this.c, this.d.b, arrayList, acl.k());
        }
    }

    protected boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("android.") || str.startsWith("java.");
    }

    protected String c(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT)).appendQueryParameter("device", a()).appendQueryParameter("js", this.d.b).appendQueryParameter("appid", this.c.getApplicationContext().getPackageName()).appendQueryParameter("stacktrace", stringWriter.toString()).toString();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (a(th)) {
            b(th);
            if (Looper.getMainLooper().getThread() != thread) {
                return;
            }
        }
        if (this.a != null) {
            this.a.uncaughtException(thread, th);
        } else if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
