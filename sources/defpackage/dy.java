package defpackage;

import android.content.Context;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class dy implements Thread.UncaughtExceptionHandler {
    private final Thread.UncaughtExceptionHandler a;
    private final ej b;
    private final Context c;
    private dx d;

    public dy(ej ejVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (ejVar == null) {
            throw new NullPointerException("tracker cannot be null");
        }
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.a = uncaughtExceptionHandler;
        this.b = ejVar;
        this.d = new ei(context, new ArrayList());
        this.c = context.getApplicationContext();
        gn.c("ExceptionReporter created, original handler is " + (uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Thread.UncaughtExceptionHandler a() {
        return this.a;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String str = "UncaughtException";
        if (this.d != null) {
            str = this.d.a(thread != null ? thread.getName() : null, th);
        }
        gn.c("Tracking Exception: " + str);
        this.b.a((Map<String, String>) new ef().a(str).a(true).a());
        dz.a(this.c).e();
        if (this.a != null) {
            gn.c("Passing exception to original handler.");
            this.a.uncaughtException(thread, th);
        }
    }
}
