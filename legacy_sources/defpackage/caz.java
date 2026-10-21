package defpackage;

import android.database.sqlite.SQLiteDiskIOException;
import android.util.Log;
import android.widget.Toast;
import com.thuuz.gtv.App;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class caz implements Thread.UncaughtExceptionHandler {
    App a;
    private Thread.UncaughtExceptionHandler b;

    public caz(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, App app) {
        this.b = uncaughtExceptionHandler;
        this.a = app;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            boolean a = a(th);
            if (a) {
                System.gc();
                System.gc();
            }
            boolean b = b(th);
            a(thread, th, a, b);
            if (a) {
                try {
                    if (this.a != null) {
                        Toast.makeText(this.a, "Out of Memory: please quit all apps and try again later.", 1).show();
                    }
                } catch (Throwable th2) {
                }
            } else if (b) {
                try {
                    if (this.a != null) {
                        Toast.makeText(this.a, "Out of Storage: please remove some songs/videos/pictures/files/apps and try again later.", 1).show();
                    }
                } catch (Throwable th3) {
                }
            }
            if (this.a != null && !this.a.c()) {
                a().warning("cannot get logs; retrying...");
                Thread.yield();
                Thread.yield();
                Thread.yield();
                if (!this.a.c()) {
                    a().severe("cannot get logs; giving up sending uncaught now...");
                    return;
                }
            }
            new Thread(new cba(this)).start();
        } catch (Throwable th4) {
            Log.e("thuuz", "problem", th4);
        }
        this.b.uncaughtException(thread, th);
    }

    private boolean a(Thread thread, Throwable th, boolean z, boolean z2) {
        String str = " ";
        if (z) {
            str = " OoM ";
        }
        if (z2) {
            str = " Disk err ";
        }
        String name = thread.getName();
        StringBuilder sb = new StringBuilder("uncaught");
        sb.append(str);
        if (name != null && name.length() > 0) {
            sb.append("on thread: ");
            sb.append(name);
        }
        a().log(Level.SEVERE, sb.toString(), th);
        return z2;
    }

    private boolean b(Throwable th) {
        if (th instanceof SQLiteDiskIOException) {
            return true;
        }
        String str = "";
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            str = stringWriter.toString();
        } catch (Throwable th2) {
        }
        return str.contains("WebViewDatabase");
    }

    public static boolean a(Throwable th) {
        boolean z;
        int i = 0;
        if (th == null) {
            return false;
        }
        boolean z2 = th instanceof OutOfMemoryError;
        if (!z2) {
            while (true) {
                try {
                    Throwable cause = th.getCause();
                    if (cause == null || i >= 100) {
                        break;
                    }
                    if (cause instanceof OutOfMemoryError) {
                        z = true;
                        break;
                    }
                    i++;
                } catch (Throwable th2) {
                    z = z2;
                    bzy.getStaticLogger(caz.class).log(Level.SEVERE, "Cannot handle isOutOfMemory: ", th);
                    return z;
                }
            }
        }
        z = z2;
        if (!z) {
            try {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                return stringWriter.toString().contains("java.lang.OutOfMemoryError");
            } catch (Throwable th3) {
                bzy.getStaticLogger(caz.class).log(Level.SEVERE, "Cannot handle isOutOfMemory: ", th);
                return z;
            }
        }
        return z;
    }

    public Logger a() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }
}
