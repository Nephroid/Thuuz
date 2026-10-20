package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ServerChoice;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class bya extends bzy {
    private static final int[] a = {-10, -21, -22};
    private String b;
    private App c;
    private boolean d;
    private int e;
    private String f;
    private JSONObject g;
    private final StringBuilder h;
    private final List<NameValuePair> i;
    private boolean j;
    private boolean k;
    private IService l;
    private String m;
    private boolean n;
    private String o;

    public bya(String str, App app, boolean z) {
        this.d = false;
        this.e = -1;
        this.h = new StringBuilder();
        this.i = new ArrayList();
        this.k = true;
        this.o = getClass().getSimpleName() + ":thuuz";
        if (str == null) {
            throw new IllegalArgumentException("null call");
        }
        this.j = z;
        if (z) {
            this.h.insert(0, ServerChoice.o);
            this.b = app.bj();
        } else {
            this.h.insert(0, ServerChoice.p);
        }
        this.f = str;
        this.c = app;
        b();
        this.e = 0;
    }

    public bya(String str, App app, IService iService, boolean z) {
        this(str, app, z);
        this.l = iService;
    }

    private void b() {
        if (this.c != null) {
            this.h.append(this.f);
            this.h.append("?");
            a("device_id", k().ad());
            a("device_type", k().ao());
            a("device_size", k().a((AbstractActivity) null));
            a("thuuz_vers", k().ai());
            a(new BasicNameValuePair("auth_code", "ec30a1d911e08264"));
        }
    }

    @Override // defpackage.bzy
    public Logger getLogger() {
        Logger logger = Logger.getLogger(this.o);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public App k() {
        return this.c;
    }

    public int l() {
        try {
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, w() + " problem: ", th);
            a(-1);
        } finally {
            m();
        }
        if (o() != 0) {
            return this.e;
        }
        this.e = t();
        n();
        return this.e;
    }

    protected void m() {
    }

    public int n() {
        try {
            c();
            if (d()) {
                a();
            }
            if (!y() && this.g != null && this.g.has("msg")) {
                this.m = this.g.getString("msg");
                getLogger().warning("'" + w() + "' error " + v() + ": " + this.m);
            }
            if (s()) {
                try {
                    this.e = b(this.g);
                } catch (Throwable th) {
                    getLogger().log(Level.INFO, "problem parsing json: " + th);
                    this.e = -1;
                }
            } else {
                a(this.e, this.g);
            }
            q();
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "problem: ", th2);
            a(-1);
        }
        return v();
    }

    protected void a() {
        if (this.b != null && this.b.equals(k().bj())) {
            k().bo();
        }
        this.d = true;
        getLogger().info("calling task retry for cookie");
        l();
        c();
    }

    private void c() {
        if (this.g != null) {
            a(this.g.getInt("result"));
        } else {
            a(-1);
        }
    }

    private boolean d() {
        if (r()) {
            if (!A()) {
                getLogger().severe("got login error yet cookie not required??");
            } else if (!this.d) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0076 A[Catch: Throwable -> 0x01b2, TryCatch #0 {Throwable -> 0x01b2, blocks: (B:3:0x0003, B:5:0x0007, B:8:0x000a, B:10:0x000e, B:12:0x0019, B:14:0x002d, B:16:0x003c, B:18:0x0042, B:20:0x004c, B:22:0x0050, B:24:0x0060, B:26:0x0064, B:27:0x006c, B:29:0x0076, B:32:0x0082, B:34:0x0099, B:36:0x00aa, B:38:0x00ae, B:40:0x00b8, B:45:0x00c4, B:47:0x00ca, B:49:0x00d4, B:52:0x00e6, B:54:0x00ec, B:56:0x00f2, B:58:0x00f8, B:59:0x011f, B:61:0x012c, B:62:0x0148, B:64:0x017e, B:66:0x0182, B:67:0x018d, B:69:0x0193, B:71:0x01c7), top: B:2:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int o() {
        /*
            Method dump skipped, instructions count: 491
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bya.o():int");
    }

    protected void p() {
    }

    protected boolean e() {
        return false;
    }

    protected void q() {
    }

    public boolean r() {
        for (int i : a) {
            if (i == this.e) {
                return true;
            }
        }
        return false;
    }

    private void f() {
        Notification build;
        try {
            NotificationManager notificationManager = (NotificationManager) k().getSystemService("notification");
            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel notificationChannel = new NotificationChannel("default", "Thuuz Sports", 3);
                    notificationChannel.setDescription("App Notifications");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                int bA = k().bA();
                PendingIntent activity = PendingIntent.getActivity(k(), 0, new Intent("android.intent.action.VIEW", Uri.parse("thuuz://auth_fail")), 0);
                if (Build.VERSION.SDK_INT >= 26) {
                    Notification.Builder builder = new Notification.Builder(k(), "default");
                    builder.setContentIntent(activity).setTicker("Please sign-in again").setWhen(System.currentTimeMillis()).setAutoCancel(true).setOnlyAlertOnce(true).setContentTitle("Thuuz Authentication Failed").setContentText("Please sign-in again").setSmallIcon(bA);
                    build = builder.build();
                } else {
                    NotificationCompat.Builder builder2 = new NotificationCompat.Builder(k());
                    builder2.setContentIntent(activity).setTicker("Please sign-in again").setWhen(System.currentTimeMillis()).setAutoCancel(true).setOnlyAlertOnce(true).setContentTitle("Thuuz Authentication Failed").setContentText("Please sign-in again").setDefaults(-1).setSmallIcon(bA);
                    ThuuzService.a(k(), builder2);
                    build = builder2.build();
                }
                try {
                    notificationManager.notify(5, build);
                } catch (Throwable th) {
                    getLogger().warning("Cannot handle: " + th);
                }
            }
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "problem: ", th2);
        }
    }

    protected boolean s() {
        return this.e == 0;
    }

    protected void a(int i, JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int t() {
        HttpResponse execute;
        try {
            DefaultHttpClient aS = k().aS();
            HttpPost httpPost = new HttpPost(this.h.toString());
            httpPost.setEntity(new UrlEncodedFormEntity(this.i, HTTP.UTF_8));
            execute = aS.execute(httpPost);
        } catch (IOException e) {
            getLogger().warning("comm problem with '" + this.f + "': " + e + " for url: " + C());
            a(-1);
        } catch (OutOfMemoryError e2) {
            Runtime.getRuntime().gc();
            getLogger().severe("OoM with '" + this.f + "': " + e2);
            k().bX();
            a(-1);
        } catch (JSONException e3) {
            getLogger().warning("json parse problem with '" + this.f + "': " + e3);
            a(-1);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem with '" + this.f + "': ", th);
            a(-1);
        }
        if (execute.getStatusLine().getStatusCode() != 200) {
            getLogger().warning("cannot call " + w() + ": " + execute.getStatusLine().getStatusCode() + ": " + execute.getStatusLine().getReasonPhrase());
            return -1;
        }
        c(new JSONObject(EntityUtils.toString(execute.getEntity())));
        return v();
    }

    public void u() {
        System.gc();
        System.gc();
        getLogger().severe("handling oom in task: " + w());
        k().bX();
    }

    public static byb a(App app, Logger logger, String str) {
        ConnectivityManager connectivityManager = (ConnectivityManager) app.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                long j = 0;
                while (j < 3000) {
                    try {
                        Thread.sleep(10L);
                        try {
                            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                                if (Build.VERSION.SDK_INT < 11) {
                                    Thread.sleep(30000L);
                                }
                                logger.info("'" + str + "' got connection after looping");
                                return new byb(true);
                            }
                            j += 10;
                            if (j % 1000 == 0) {
                                logger.info("'" + str + "' loop during isConnecting");
                            }
                        } catch (Throwable th) {
                            logger.info("broke out of network-connection wait loop: " + th);
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
            if (activeNetworkInfo != null) {
                if (!activeNetworkInfo.isConnectedOrConnecting()) {
                    logger.info("'" + str + "' no connection");
                    return new byb(false);
                }
                return new byb(true);
            }
            return new byb(false);
        }
        return new byb(false);
    }

    public void a(int i) {
        this.e = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, int i) {
        this.h.append(str);
        this.h.append("=");
        this.h.append(i);
        this.h.append("&");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, String str2) {
        try {
            this.h.append(str);
            this.h.append("=");
            this.h.append(URLEncoder.encode(str2, "utf-8"));
            this.h.append("&");
        } catch (UnsupportedEncodingException e) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
        }
    }

    public int v() {
        return this.e;
    }

    private static synchronized int b(App app) {
        int l;
        synchronized (bya.class) {
            if (app == null) {
                l = -1;
            } else if (app.bn()) {
                l = 0;
            } else {
                l = new bzg(app, app.bk()).l();
            }
        }
        return l;
    }

    public String w() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(NameValuePair nameValuePair) {
        this.i.add(nameValuePair);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(String str, String str2) {
        this.i.add(new BasicNameValuePair(str, str2));
    }

    public int b(JSONObject jSONObject) {
        return this.e;
    }

    public JSONObject x() {
        return this.g;
    }

    public boolean y() {
        return v() == 0;
    }

    public void c(JSONObject jSONObject) {
        this.g = jSONObject;
    }

    public IService z() {
        return this.l;
    }

    public boolean A() {
        return this.j;
    }

    public void b(boolean z) {
        this.k = z;
    }

    public String B() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(boolean z) {
        this.n = z;
    }

    public String C() {
        return this.h.toString();
    }

    public void d(boolean z) {
        this.d = z;
    }

    public Map<String, String> D() {
        HashMap hashMap = new HashMap(this.i.size());
        for (NameValuePair nameValuePair : this.i) {
            hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
        }
        return hashMap;
    }

    public static boolean a(App app) {
        ConnectivityManager connectivityManager = (ConnectivityManager) app.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
