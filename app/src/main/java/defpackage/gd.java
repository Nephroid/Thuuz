package defpackage;

import android.content.Context;
import android.os.Process;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
// [removed legacy tracker import]
import com.google.android.gms.analytics.r;
import com.google.android.gms.internal.hb;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class gd extends Thread implements fh {
    private static gd f;
    private final LinkedBlockingQueue<Runnable> a;
    private volatile boolean b;
    private volatile boolean c;
    private volatile List<hb> d;
    private volatile String e;
    private volatile et g;
    private final Context h;

    private gd(Context context) {
        super("GAThread");
        this.a = new LinkedBlockingQueue<>();
        this.b = false;
        this.c = false;
        if (context != null) {
            this.h = context.getApplicationContext();
        } else {
            this.h = context;
        }
        start();
    }

    static int a(String str) {
        int i = 1;
        if (!TextUtils.isEmpty(str)) {
            i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                i = ((i << 6) & 268435455) + charAt + (charAt << 14);
                int i2 = 266338304 & i;
                if (i2 != 0) {
                    i ^= i2 >> 21;
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static gd a(Context context) {
        if (f == null) {
            f = new gd(context);
        }
        return f;
    }

    private String a(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    static String b(Context context) {
        String str = null;
        try {
            FileInputStream openFileInput = context.openFileInput("gaInstallData");
            byte[] bArr = new byte[8192];
            int read = openFileInput.read(bArr, 0, 8192);
            if (openFileInput.available() > 0) {
                gn.a("Too much campaign data, ignoring it.");
                openFileInput.close();
                context.deleteFile("gaInstallData");
            } else {
                openFileInput.close();
                context.deleteFile("gaInstallData");
                if (read <= 0) {
                    gn.d("Campaign file is empty.");
                } else {
                    String str2 = new String(bArr, 0, read);
                    gn.b("Campaign found: " + str2);
                    str = str2;
                }
            }
        } catch (FileNotFoundException e) {
            gn.b("No campaign data found.");
        } catch (IOException e2) {
            gn.a("Error reading campaign data.");
            context.deleteFile("gaInstallData");
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(Map<String, String> map) {
        return (!map.containsKey("useSecure") || ew.a(map.get("useSecure"), true)) ? "https:" : "http:";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Map<String, String> map) {
        if (map.get("&sf") == null) {
            return false;
        }
        double a = ew.a(map.get("&sf"), 100.0d);
        if (a < 100.0d && a(map.get("&cid")) % 10000 >= a * 100.0d) {
            gn.c(String.format("%s hit sampled out", map.get("&t") == null ? EnvironmentCompat.MEDIA_UNKNOWN : map.get("&t")));
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<String, String> map) {
        fp a = em.a(this.h);
        ew.a(map, "&adid", a);
        ew.a(map, "&ate", a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Map<String, String> map) {
        fi a = fi.a();
        ew.a(map, "&an", a);
        ew.a(map, "&av", a);
        ew.a(map, "&aid", a);
        ew.a(map, "&aiid", a);
        map.put("&v", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    @Override // defpackage.fh
    public void a() {
        a(new gf(this));
    }

    void a(Runnable runnable) {
        this.a.add(runnable);
    }

    @Override // defpackage.fh
    public void a(Map<String, String> map) {
        HashMap hashMap = new HashMap(map);
        String str = map.get("&ht");
        if (str != null) {
            try {
                Long.valueOf(str);
            } catch (NumberFormatException e) {
                str = null;
            }
        }
        if (str == null) {
            hashMap.put("&ht", Long.toString(System.currentTimeMillis()));
        }
        a(new ge(this, hashMap));
    }

    @Override // defpackage.fh
    public void b() {
        a(new gg(this));
    }

    @Override // defpackage.fh
    public LinkedBlockingQueue<Runnable> c() {
        return this.a;
    }

    @Override // defpackage.fh
    public Thread d() {
        return this;
    }

    protected void e() {
        this.g.c();
        this.d = new ArrayList();
        this.d.add(new hb("appendVersion", "&_v".substring(1), "ma4.0.3"));
        this.d.add(new hb("appendQueueTime", "&qt".substring(1), null));
        this.d.add(new hb("appendCacheBuster", "&z".substring(1), null));
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            gn.d("sleep interrupted in GAThread initialize");
        }
        try {
            if (this.g == null) {
                this.g = new r(this.h, this);
            }
            e();
            this.e = b(this.h);
            gn.c("Initialized GA Thread");
        } catch (Throwable th) {
            gn.a("Error initializing the GAThread: " + a(th));
            gn.a("Google Analytics will not start up.");
            this.b = true;
        }
        while (!this.c) {
            try {
                try {
                    Runnable take = this.a.take();
                    if (!this.b) {
                        take.run();
                    }
                } catch (InterruptedException e2) {
                    gn.b(e2.toString());
                }
            } catch (Throwable th2) {
                gn.a("Error on GAThread: " + a(th2));
                gn.a("Google Analytics is shutting down.");
                this.b = true;
            }
        }
    }
}
