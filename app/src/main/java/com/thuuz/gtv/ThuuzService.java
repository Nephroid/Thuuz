package com.thuuz.gtv;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.richnotification.Srn;
import com.samsung.android.sdk.richnotification.SrnAction;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.SrnRichNotification;
import com.samsung.android.sdk.richnotification.SrnRichNotificationManager;
import com.samsung.android.sdk.richnotification.actions.SrnHostAction;
import com.samsung.android.sdk.richnotification.templates.SrnStandardTemplate;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.UserProfileActivity;
import com.thuuz.gtv.activity.nobar.InitialActivity;
import com.thuuz.gtv.data.AlertSettings;
import com.thuuz.gtv.data.FantasySettingsCustomizations;
import com.thuuz.gtv.data.FantasySite;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.data.OnlineResourceCategory;
import com.thuuz.gtv.listeners.LocationChangedReceiver;
import com.thuuz.gtv.listeners.NetworkChangedReceiver;
import com.thuuz.gtv.settings.SettingsSoundsActivity;
import com.thuuz.gtv.view.GecCircleView;
import com.thuuz.gtv.widget.WidgetRemoteViewService;
import defpackage.bbl;
import defpackage.bbp;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbw;
import defpackage.bbx;
import defpackage.bby;
import defpackage.bbz;
import defpackage.bca;
import defpackage.bcb;
import defpackage.bcd;
import defpackage.bce;
import defpackage.bcf;
import defpackage.bcg;
import defpackage.bch;
import defpackage.bci;
import defpackage.bck;
import defpackage.bcm;
import defpackage.bcn;
import defpackage.bco;
import defpackage.bcp;
import defpackage.bcq;
import defpackage.bcr;
import defpackage.bcs;
import defpackage.bct;
import defpackage.bcu;
import defpackage.bcv;
import defpackage.boe;
import defpackage.boz;
import defpackage.bpb;
import defpackage.bpe;
import defpackage.bpf;
import defpackage.bpg;
import defpackage.bpn;
import defpackage.bpo;
import defpackage.bpr;
import defpackage.bps;
import defpackage.bpv;
import defpackage.bpx;
import defpackage.bpz;
import defpackage.bqr;
import defpackage.byc;
import defpackage.byf;
import defpackage.byg;
import defpackage.byi;
import defpackage.byj;
import defpackage.byl;
import defpackage.byp;
import defpackage.byr;
import defpackage.byw;
import defpackage.byy;
import defpackage.byz;
import defpackage.bzc;
import defpackage.bze;
import defpackage.bzh;
import defpackage.bzi;
import defpackage.bzj;
import defpackage.bzk;
import defpackage.bzl;
import defpackage.bzu;
import defpackage.bzw;
import defpackage.bzy;
import defpackage.cak;
import defpackage.car;
import defpackage.caz;
import defpackage.cbd;
import defpackage.cbe;
import defpackage.cr;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.auth.RequestToken;

/* loaded from: classes.dex */
public class ThuuzService extends Service implements IService {
    private static NotificationChannel G;
    private PowerManager.WakeLock C;
    private bqr D;
    private bpv E;
    private cbe F;
    private RequestToken M;
    private cbd O;
    private Thread P;
    private boolean Q;
    private LocationChangedReceiver S;
    private boolean U;
    private long V;
    private boolean W;
    private int X;
    private boolean Y;
    private boolean aa;
    private long p;
    private Timer q;
    private boolean r;
    private boolean s;
    private Thread u;
    private static int c = 3;
    private static final int[] d = {bbq.gec_dull, bbq.gec_ok, bbq.gec_good, bbq.gec_great};
    public static final Integer a = 0;
    public static final Integer b = 1;
    private static final String[][] ac = {new String[]{"GameReminder", "Game Reminder"}, new String[]{"ScoreEvent", "Score"}, new String[]{"ScoreUpdate", "Score Update"}, new String[]{"Invite", "Friend Invite"}, new String[]{"Share", "Friend Checkin"}, new String[]{"Follow", "Friend Follow"}, new String[]{"GameAlert", "Exciting Game"}, new String[]{"FSPlayer", "Fantasy Update"}, new String[]{"FSShare", "Smack Talk"}, new String[]{"FSTeam", "Fantasy Update"}};
    private final IBinder e = new bcv(this);
    private final Map<Integer, GameData> f = new ConcurrentHashMap();
    private long g = 0;
    private final Handler h = new Handler();
    private final Map<Integer, bpv> i = Collections.synchronizedMap(new TreeMap());
    private SparseArray<bpx> j = new SparseArray<>();
    private Map<String, bpr> k = new HashMap();
    private Map<Integer, bpr> l = new HashMap();
    private Map<Integer, bps> m = new HashMap();
    private Map<Integer, OnlineResourceCategory> n = new HashMap();
    private Map<Integer, AlertSettings> o = new HashMap();
    private final Map<Integer, bpz> t = new ConcurrentHashMap();
    private final ArrayList<bct> v = new ArrayList<>();
    private final Semaphore w = new Semaphore(1);
    private final Object x = new Object();
    private final Object y = new Object();
    private final Object z = new Object();
    private final Object A = new Object();
    private final Object B = new Object();
    private Map<Integer, bpe> H = new ConcurrentHashMap();
    private final Map<Integer, Map<Integer, FantasySite>> I = new ConcurrentHashMap();
    private final Map<Integer, Map<Integer, bpg>> J = new ConcurrentHashMap();
    private final Map<Integer, bpf> K = new ConcurrentHashMap();
    private Map<Integer, Integer> L = new ConcurrentHashMap();
    private Map<Integer, List<boz>> N = new HashMap();
    private Map<Integer, FantasySettingsCustomizations> R = new HashMap();
    private final Object T = new Object();
    private AtomicInteger Z = new AtomicInteger(0);
    private String ab = getClass().getSimpleName() + ":thuuz";

    private void O() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            NotificationChannel a2 = a(m(), new NotificationChannel("default", "Thuuz Sports", 3));
            a2.setDescription("App Notifications");
            notificationManager.createNotificationChannel(a2);
            G = a2;
        }
    }

    public NotificationChannel D() {
        if (G == null) {
            O();
        }
        Log.e("Thuuz", "default notification channel: " + G);
        return G;
    }

    @Override // android.app.Service
    public void onCreate() {
        try {
            super.onCreate();
            this.p = System.currentTimeMillis();
            H().info("Service created at " + new Date());
            this.C = ((PowerManager) getSystemService("power")).newWakeLock(1, "ThuuzWakeLock");
            at();
            if (Build.VERSION.SDK_INT >= 26) {
                c = 10;
            }
        } catch (OutOfMemoryError e) {
            z();
        } catch (Throwable th) {
            if (caz.a(th)) {
                z();
            } else {
                H().log(Level.SEVERE, "service onCreate: ", th);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        W();
        return this.e;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            try {
                m().c(true);
                if (intent == null) {
                    H().info("service start with empty intent");
                } else {
                    String action = intent.getAction();
                    if ("wake".equals(action)) {
                        H().info("got wakeup");
                        stopForeground(true);
                    } else if ("com.thuuz.game_alert".equals(action)) {
                        if (c(intent)) {
                            a(intent);
                        }
                    } else if ("TYPED_ALERT".equals(action)) {
                        if (c(intent)) {
                            b(intent);
                        }
                    } else if ("com.thuuz.unregister_push".equals(action)) {
                        V();
                    } else if ("com.thuuz.register_push".equals(action)) {
                        U();
                    } else if ("UPLOAD_PREFS".equals(action)) {
                        T();
                    } else if ("UPLOAD_FANTASY_PREFS".equals(action)) {
                        int intExtra = intent.getIntExtra("team_id", -1);
                        if (intExtra == -1) {
                            H().log(Level.SEVERE, "Trying to upload fantasy prefs without team_id");
                        } else {
                            p(intExtra);
                        }
                    } else if ("DELETE_FANTASY_TEAM".equals(action)) {
                        int intExtra2 = intent.getIntExtra("team_id", -1);
                        if (intExtra2 == -1) {
                            H().log(Level.SEVERE, "Trying to delete fantasy team without team_id");
                        } else {
                            q(intExtra2);
                        }
                    } else if ("UPDATE_THUUZ_PUSH".equals(action)) {
                        S();
                    } else if ("CLEAR_TV_SCHEDULES".equals(action)) {
                        try {
                            Iterator it = new ArrayList(this.f.values()).iterator();
                            while (it.hasNext()) {
                                ((GameData) it.next()).J();
                            }
                        } catch (Throwable th) {
                            H().log(Level.SEVERE, "cannot clear tv schedules: ", th);
                        }
                    } else if ("updateTeams".equals(action)) {
                        R();
                        stopForeground(true);
                    } else if ("upd_teams".equals(action)) {
                        Q();
                        stopForeground(true);
                    } else if ("SYNC_FANTASY_TEAMS".equals(action)) {
                        E();
                    } else if ("ethernet_conn".equals(action)) {
                        this.U = true;
                        stopForeground(true);
                    } else if ("network_disconn".equals(action)) {
                        P();
                        stopForeground(true);
                    } else if ("ToastOom".equals(action)) {
                        aA();
                        stopForeground(true);
                    } else if ("read_widget".equals(action)) {
                        aB();
                        stopForeground(true);
                    } else if ("widget_enabled".equals(action)) {
                        m().a("miscellaneous", "widget_installed", "", 0L);
                        stopForeground(true);
                    } else if ("widget_deleted".equals(action)) {
                        m().a("miscellaneous", "widget_deleted", "", 0L);
                        stopForeground(true);
                    } else if ("widget_notif_killed".equals(action)) {
                        h("Updater for Thuuz Widget");
                        stopForeground(true);
                    }
                }
            } catch (Throwable th2) {
                stopForeground(true);
                H().log(Level.SEVERE, "Error in service start: ", th2);
            }
        } catch (OutOfMemoryError e) {
            stopForeground(true);
            Runtime.getRuntime().gc();
            H().severe("OoM with service start: " + e);
            z();
        }
        return 2;
    }

    private void P() {
        try {
            boolean z = this.U;
            this.U = false;
            this.C.acquire();
            if (z) {
                Toast.makeText(this, "Network disconnected", 1).show();
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.C.release();
        }
    }

    private void Q() {
        try {
            this.C.acquire();
            H().info("received team update");
            TreeSet<Integer> x = m().x();
            for (Integer num : new HashSet(this.t.keySet())) {
                if (!x.contains(num)) {
                    bpz bpzVar = this.t.get(num);
                    if (bpzVar != null) {
                        H().info("removing team from cache: " + bpzVar);
                    }
                    this.t.remove(num);
                }
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.C.release();
        }
    }

    public void E() {
        try {
            this.C.acquire();
            H().info("received fantasy team sync");
            new Thread(new bbw(this)).start();
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
            v(-1);
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void R() {
        try {
            this.C.acquire();
            new Thread(new bci(this), "handleUpdateTeams").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void S() {
        try {
            this.C.acquire();
            new Thread(new bcm(this), "handleThuuzPush").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void T() {
        try {
            this.C.acquire();
            new Thread(new bcn(this), "handleUploadPrefs").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void p(int i) {
        try {
            this.C.acquire();
            new Thread(new bco(this, i), "handleUploadFantasyPrefs").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void q(int i) {
        try {
            this.C.acquire();
            new Thread(new bcp(this, i), "handleDeleteFantasyTeam").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void U() {
        try {
            this.C.acquire();
            new Thread(new bcq(this), "handleRegGooglePush").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void V() {
        try {
            this.C.acquire();
            new Thread(new bcr(this), "handleUnregisterGooglePush").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void a(Intent intent) {
        try {
            this.C.acquire();
            new Thread(new bcs(this, intent), "handleGameAlert").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    private void b(Intent intent) {
        try {
            this.C.acquire();
            new Thread(new bbx(this, intent), "handleTypedAlert").start();
        } catch (Throwable th) {
            stopForeground(true);
        } finally {
            this.C.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        H().info("received share alert: " + str2);
        a(str2, str3, str, "Share");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        Intent aq;
        if (str2 != null) {
            aq = t(Integer.parseInt(str2));
            aq.setAction("sender" + str3);
            aq.putExtra("sender", str3);
        } else if (str3 != null) {
            aq = g(str3);
        } else {
            H().info("incoming typed notification lacks click info, giving home page click: " + str);
            aq = aq();
        }
        a(str, aq, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Intent intent, String str2) {
        RemoteViews remoteViews;
        Notification build;
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                remoteViews = new RemoteViews(getPackageName(), bbr.notification_typed);
            } else {
                remoteViews = new RemoteViews(getPackageName(), bbr.notification_typed_sdk8);
            }
            String e = e(str);
            remoteViews.setTextViewText(bbq.line1, e);
            String str3 = m().e() ? null : e;
            int bA = m().bA();
            int ap = ap();
            if (Build.VERSION.SDK_INT >= 26) {
                Notification.Builder builder = new Notification.Builder(this, "default");
                builder.setTicker(str3).setWhen(System.currentTimeMillis()).setShowWhen(true).setAutoCancel(true).setOnlyAlertOnce(true).setSmallIcon(bA).setCustomContentView(remoteViews).setContentText(str3);
                if (intent != null) {
                    builder.setContentIntent(PendingIntent.getActivity(this, ap, intent, 134217728));
                } else {
                    H().severe("notify without intent: " + e);
                }
                build = builder.build();
            } else {
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);
                builder2.setTicker(str3).setWhen(System.currentTimeMillis()).setDefaults(-1).setAutoCancel(true).setOnlyAlertOnce(true).setSmallIcon(bA).setContent(remoteViews);
                a(m(), builder2);
                if (intent != null) {
                    builder2.setContentIntent(PendingIntent.getActivity(this, ap, intent, 134217728));
                } else {
                    H().severe("notify without intent: " + e);
                }
                if (!m().e()) {
                    a(m(), builder2);
                }
                build = builder2.build();
            }
            if (Build.VERSION.SDK_INT < 11) {
                build.contentView = remoteViews;
            }
            a(str2, e, intent);
            try {
                NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
                if (Build.VERSION.SDK_INT >= 26) {
                    D();
                }
                notificationManager.notify(ap, build);
                m().r(ap);
                H().info("posted typed notification with ticker: " + e);
            } catch (SecurityException e2) {
                H().warning("Cannot notify: " + e2);
            }
            if (SettingsSoundsActivity.TOAST_TYPE.NONE != m().I()) {
                h(e);
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
    }

    private String e(String str) {
        return str.replaceAll(":\n", ":").replaceAll(":\r\n", ":").replaceAll(".\n\r", ". ").replaceAll(".\r\n", ". ").replaceAll(".\n", ". ").replaceAll(".\r", ". ").replaceAll("\n\r", ". ").replaceAll("\r\n", ". ").replaceAll("\n", ". ").replaceAll("\r", ". ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2) {
        H().info("received invite alert: " + str2);
        a(str2, (String) null, str, "Invite");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        int parseInt = Integer.parseInt(str);
        H().info("received follow alert: " + str2);
        Intent intent = new Intent(this, (Class<?>) UserProfileActivity.class);
        intent.setAction(parseInt + "");
        intent.setFlags(402653184);
        intent.putExtra("userId", parseInt);
        intent.putExtra("isNotification", Boolean.TRUE);
        a(str2, intent, "Follow");
    }

    private synchronized void W() {
        if (!this.Y) {
            H().info("doOnetimeStartupFromUser");
            try {
                m().U();
                new Thread(new bby(this)).start();
                if (m().e()) {
                    L();
                }
                X();
                IntentFilter intentFilter = new IntentFilter("loc_set");
                this.S = new LocationChangedReceiver(this);
                registerReceiver(this.S, intentFilter, "com.thuuz.permission.local.broadcast", null);
                if (m().e()) {
                    a(new NetworkChangedReceiver().a(this));
                }
                this.Y = true;
            } catch (Throwable th) {
                H().log(Level.SEVERE, "startup error: ", th);
            }
        }
    }

    private void X() {
        m().k("/launch");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y() {
        boolean z;
        AbstractActivity abstractActivity;
        try {
            PlatformOperations platformOperationsInstance = PlatformOperations.getPlatformOperationsInstance(getPackageName());
            if (platformOperationsInstance != null) {
                Iterator it = new ArrayList(this.v).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    bct bctVar = (bct) it.next();
                    if (bct.a(bctVar) != null && (abstractActivity = (AbstractActivity) bct.a(bctVar).get()) != null) {
                        a(platformOperationsInstance, abstractActivity);
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    int i = this.X;
                    this.X = i + 1;
                    if (i > 6) {
                        H().info("no activities with which to initialize platform after " + this.X + " loops");
                    } else {
                        I().postDelayed(new bbz(this), 5000L);
                    }
                }
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem initializing platform: ", th);
        }
    }

    private void a(PlatformOperations platformOperations, AbstractActivity abstractActivity) {
        try {
            abstractActivity.runOnUiThread(new bca(this, platformOperations, abstractActivity));
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem initializing platform: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z() {
        try {
            this.h.postDelayed(new bcb(this), 43200000L);
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem starting endgame alarms: ", th);
        }
    }

    private void aa() {
        synchronized (this.x) {
            m().ap();
        }
    }

    private void ab() {
        synchronized (this.x) {
            m().ap();
        }
    }

    public synchronized void F() {
        try {
            if (!g()) {
                String ad = ad();
                if (ad.length() == 0) {
                    H().info("cannot read sportinfo cache");
                } else {
                    H().info("reading sportinfo cache");
                    bzu bzuVar = new bzu(m());
                    if (bzuVar.a(ad)) {
                        a(bzuVar);
                    }
                }
            }
        } catch (Throwable th) {
            H().info("sportinfo cache unreadable");
        }
    }

    private synchronized boolean ac() {
        boolean z;
        boolean z2 = true;
        synchronized (this) {
            try {
            } catch (Throwable th) {
                H().log(Level.WARNING, "Error using cached teams: " + th);
                z = false;
            }
            if (m().aB()) {
                String ae = ae();
                try {
                    bzw bzwVar = new bzw(m());
                    bzwVar.a(0);
                    List<bpz> b2 = bzwVar.b(ae);
                    if (bzwVar.y()) {
                        this.t.clear();
                        for (bpz bpzVar : b2) {
                            this.t.put(Integer.valueOf(bpzVar.b()), bpzVar);
                        }
                    }
                    H().info("from cache found teams: " + b2.size());
                    z = true;
                } catch (Throwable th2) {
                    H().info("must replace teams");
                    z = false;
                }
                z2 = z;
            }
        }
        return z2;
    }

    private String ad() {
        return cak.a(m().br());
    }

    private String ae() {
        return cak.a(m().bs());
    }

    private boolean af() {
        return false;
    }

    @Override // com.thuuz.gtv.IService
    public int h() {
        return b(false);
    }

    private int b(boolean z) {
        int i = -1;
        try {
            this.w.acquire(1);
            if (!z) {
                try {
                    if (g()) {
                        return 0;
                    }
                } catch (Throwable th) {
                    H().log(Level.SEVERE, "Error contacting server for sportsinfo: ", th);
                    return i;
                } finally {
                    this.w.release();
                }
            }
            bzu bzuVar = new bzu(m());
            i = bzuVar.l();
            if (i == 0) {
                a(bzuVar);
            }
            return i;
        } catch (InterruptedException e) {
            H().log(Level.WARNING, "fetch sportinfo acquire interruption: ");
            return -1;
        }
    }

    private void a(bzu bzuVar) {
        this.j = bzuVar.g();
        this.k = bzuVar.d();
        this.l = bzuVar.c();
        this.m = bzuVar.b();
        this.n = bzuVar.f();
        this.o = bzuVar.h();
        this.N = bzuVar.i();
        this.R = bzuVar.j();
        this.r = true;
        bzuVar.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        int incrementAndGet = this.Z.incrementAndGet();
        synchronized (this.y) {
            try {
            } catch (Throwable th) {
                H().log(Level.SEVERE, "problem with prefs up: ", th);
            }
            if (this.Z.intValue() > incrementAndGet) {
                H().info("skipping upload because superseded");
            } else {
                new bzk(m()).l();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i) {
        synchronized (this.z) {
            try {
                new byp(m(), i, this.h).l();
            } catch (Throwable th) {
                H().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(int i) {
        synchronized (this.A) {
            try {
                new byj(m(), i).l();
            } catch (Throwable th) {
                H().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    public int G() {
        int i;
        synchronized (this.x) {
            try {
                i = new byg(m()).l();
            } catch (Throwable th) {
                m().aL();
                H().log(Level.SEVERE, "problem: ", th);
                i = -1;
            }
        }
        return i;
    }

    public String b(String[] strArr) {
        try {
            return strArr[0] + "_" + strArr[1];
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
            return "";
        }
    }

    private void ah() {
        new bzh(m(), this).l();
    }

    @Override // com.thuuz.gtv.IService
    public void a(int i, int i2, String str) {
        this.i.put(Integer.valueOf(i), new bpv(str, i, Integer.valueOf(i2)));
        H().info("added message id: " + i);
    }

    @Override // com.thuuz.gtv.IService
    public synchronized Date a(JSONObject jSONObject) {
        Date date;
        date = null;
        try {
            if (jSONObject.has("timestamp")) {
                date = GameData.a(jSONObject.getString("timestamp"));
                this.g = date.getTime() - new Date().getTime();
            } else {
                H().severe("no server timestamp!");
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
        return date;
    }

    private void ai() {
        if (aj() && m().c()) {
            m().au();
            m().af();
        }
    }

    private boolean aj() {
        return true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            ((NotificationManager) getSystemService("notification")).cancel(99);
            if (this.F != null) {
                this.F.a();
                this.F = null;
            }
            if (this.D != null) {
                getContentResolver().unregisterContentObserver(this.D);
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Error unregistering providerListener: ", th);
        }
        try {
            if (this.S != null) {
                unregisterReceiver(this.S);
            }
        } catch (Throwable th2) {
            H().log(Level.SEVERE, "Error unregistering loc: ", th2);
        }
        m().c(false);
        super.onDestroy();
        this.aa = true;
        H().log(Level.INFO, "Service destroyed at " + new Date());
    }

    private boolean ak() {
        return false;
    }

    private Set<Integer> al() {
        HashSet hashSet = new HashSet();
        try {
            Iterator<String> it = f("notificationMem").iterator();
            while (it.hasNext()) {
                hashSet.add(Integer.valueOf(Integer.parseInt(it.next())));
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return hashSet;
    }

    private Set<String> am() {
        return f("notifyUnique");
    }

    private Set<String> f(String str) {
        HashSet hashSet = new HashSet();
        try {
            if (ak()) {
                m().b(str, "");
            }
            for (String str2 : m().X().getString(str, "").split(",")) {
                if (str2.length() > 0) {
                    hashSet.add(str2);
                }
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        try {
            H().info("doing housekeeping");
            if (m().i()) {
                if (m().bu()) {
                    T();
                } else {
                    A();
                }
            }
            if (System.currentTimeMillis() - m().R() > 86400000) {
                av();
            }
            try {
                Thread.sleep(new Random().nextInt(1500));
            } catch (Throwable th) {
                H().log(Level.SEVERE, "Cannot refresh teams: ", th);
            }
            ah();
            ai();
            File br = m().br();
            if (br != null && br.exists() && br.lastModified() < System.currentTimeMillis() - 10800000 && this.w.availablePermits() > 0) {
                H().info("refreshing old sportinfo...");
                b(true);
            }
            if (m().x().size() > 0) {
                File bs = m().bs();
                if (bs.exists() && bs.lastModified() < System.currentTimeMillis() - 172800000) {
                    H().info("refreshing old team info...");
                    p();
                }
            }
            if (System.currentTimeMillis() - this.p > 43200000) {
                ao();
            }
        } catch (Throwable th2) {
            H().log(Level.SEVERE, "Error: ", th2);
        }
    }

    private void ao() {
        try {
            ArrayList<Integer> arrayList = new ArrayList();
            Date n = n();
            Iterator it = new ArrayList(this.f.keySet()).iterator();
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                if (a(this.f.get(num), 7, n)) {
                    arrayList.add(num);
                }
            }
            for (Integer num2 : arrayList) {
                GameData remove = this.f.remove(num2);
                H().info("removed old game " + num2 + ", " + remove.o() + ", " + remove.q());
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
    }

    private boolean a(GameData gameData, int i, Date date) {
        try {
            Date o = gameData.o();
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(o);
            Calendar bh = m().bh();
            bh.setTime(date);
            return Math.abs(GameData.a(bh, gregorianCalendar)) > i;
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot handle: ", th);
            return true;
        }
    }

    @Override // com.thuuz.gtv.IService
    public void z() {
        try {
            this.f.clear();
            m().bX();
        } catch (Throwable th) {
            H().severe("cannot handle oom");
        }
    }

    public Logger H() {
        Logger logger = Logger.getLogger(this.ab);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    private void a(JSONObject jSONObject, boolean z, String str) {
        Notification build;
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                GameData gameData = new GameData(jSONObject, m());
                TreeSet<Integer> x = m().x();
                int h = gameData.h(x);
                String valueOf = String.valueOf(h);
                int i = gameData.i();
                if (z || a(h, gameData)) {
                    RemoteViews remoteViews = new RemoteViews(getPackageName(), bbr.notification_game_eq);
                    remoteViews.setTextViewText(bbq.line1, str);
                    a(gameData, remoteViews, valueOf, this.t);
                    String str2 = !m().e() ? str : null;
                    String str3 = h >= 0 ? str + " Rating: " + valueOf : str;
                    int ap = ap();
                    PendingIntent activity = PendingIntent.getActivity(this, ap, t(i), 134217728);
                    int bA = m().bA();
                    if (Build.VERSION.SDK_INT >= 26) {
                        Notification.Builder builder = new Notification.Builder(this, "default");
                        builder.setContentIntent(activity).setTicker(str2).setWhen(System.currentTimeMillis()).setAutoCancel(true).setCustomContentView(remoteViews).setOnlyAlertOnce(true).setShowWhen(true).setSmallIcon(bA).setContentText(str3);
                        build = builder.build();
                    } else {
                        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);
                        builder2.setContentIntent(activity).setTicker(str2).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContent(remoteViews).setOnlyAlertOnce(true).setDefaults(-1).setSmallIcon(bA).setContentText(str3);
                        a(m(), builder2);
                        build = builder2.build();
                    }
                    if (Build.VERSION.SDK_INT < 11) {
                        build.contentView = remoteViews;
                    }
                    try {
                        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
                        if (Build.VERSION.SDK_INT >= 26) {
                            D();
                        }
                        notificationManager.notify(ap, build);
                        m().r(ap);
                        H().info("posted notification with ticker: " + str);
                    } catch (Throwable th) {
                        H().warning("Cannot notify: " + th);
                    }
                    if (SettingsSoundsActivity.TOAST_TYPE.NONE != m().I()) {
                        a(str, valueOf, gameData, x);
                    }
                    b(str, i, gameData, h);
                }
            } catch (Throwable th2) {
                H().log(Level.SEVERE, "Cannot handle notify: ", th2);
            }
        }
    }

    private void b(String str, int i, GameData gameData, int i2) {
        try {
            new Srn().initialize(this);
            SrnRichNotificationManager srnRichNotificationManager = new SrnRichNotificationManager(this);
            if (srnRichNotificationManager.isConnected()) {
                SrnRichNotification a2 = a(str, i, gameData, i2);
                try {
                    srnRichNotificationManager.start();
                    srnRichNotificationManager.notify(a2);
                    return;
                } finally {
                    srnRichNotificationManager.stop();
                }
            }
            H().log(Level.INFO, "Cannot notify Samsung watch because it is disconnected.");
        } catch (SsdkUnsupportedException e) {
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot handle Samsung notify: ", th);
        }
    }

    public SrnRichNotification a(String str, int i, GameData gameData, int i2) {
        String str2;
        bpr bprVar;
        String str3 = gameData.r() + (" " + i2 + "/100");
        if (!g() || (bprVar = this.k.get(gameData.H())) == null) {
            str2 = "Game";
        } else {
            str2 = bprVar.c().e();
        }
        String str4 = "Exciting " + str2;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("thuuz://game/" + i));
        intent.putExtra("game", i);
        return a(str, str3, str4, intent);
    }

    private SrnRichNotification a(String str, String str2, String str3, Intent intent) {
        SrnImageAsset srnImageAsset = new SrnImageAsset(this, "app_icon", BitmapFactory.decodeResource(getResources(), bbp.header_image));
        SrnStandardTemplate srnStandardTemplate = new SrnStandardTemplate();
        srnStandardTemplate.setBackgroundImage(srnImageAsset);
        srnStandardTemplate.setSubHeader(str3);
        srnStandardTemplate.setBody(str);
        SrnRichNotification srnRichNotification = new SrnRichNotification(this);
        srnRichNotification.setReadout(str2, str);
        srnRichNotification.setPrimaryTemplate(srnStandardTemplate);
        a(srnRichNotification, intent);
        return srnRichNotification;
    }

    private void a(SrnRichNotification srnRichNotification, Intent intent) {
        SrnImageAsset srnImageAsset = new SrnImageAsset(this, "web_icon", BitmapFactory.decodeResource(getResources(), bbp.action_icon));
        SrnHostAction srnHostAction = new SrnHostAction("Launch Thuuz");
        srnHostAction.setIcon(srnImageAsset);
        srnHostAction.setToast("Opened on phone...");
        srnHostAction.setCallbackIntent(SrnAction.CallbackIntent.getActivityCallback(intent));
        ArrayList arrayList = new ArrayList();
        arrayList.add(srnHostAction);
        srnRichNotification.addActions(arrayList);
        srnRichNotification.setAlertType(SrnRichNotification.AlertType.SOUND_AND_VIBRATION, SrnRichNotification.PopupType.NORMAL);
    }

    private void a(String str, String str2, Intent intent) {
        int i = 0;
        try {
            new Srn().initialize(this);
            SrnRichNotificationManager srnRichNotificationManager = new SrnRichNotificationManager(this);
            if (srnRichNotificationManager.isConnected()) {
                String str3 = "";
                String[][] strArr = ac;
                int length = strArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String[] strArr2 = strArr[i];
                    if (!strArr2[0].equals(str)) {
                        i++;
                    } else {
                        str3 = strArr2[1];
                        break;
                    }
                }
                SrnRichNotification a2 = a(str2, str3, str3, intent);
                try {
                    srnRichNotificationManager.start();
                    srnRichNotificationManager.notify(a2);
                    return;
                } finally {
                    srnRichNotificationManager.stop();
                }
            }
            H().log(Level.INFO, "Cannot notify Samsung watch because it is disconnected.");
        } catch (SsdkUnsupportedException e) {
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot handle Samsung setRouteCondition: ", th);
        }
    }

    public static void a(App app, NotificationCompat.Builder builder) {
        boolean z;
        boolean z2;
        if (app.e()) {
            builder.setDefaults(0);
            return;
        }
        String G2 = app.G();
        if (G2 == null) {
            z = true;
        } else {
            if (!(!G2.equals(""))) {
                z = false;
            } else if (G2.equals("content://settings/system/notification_sound")) {
                z = true;
            } else {
                try {
                    builder.setSound(Uri.parse(G2), 5);
                    z = false;
                } catch (Throwable th) {
                    z = true;
                }
            }
        }
        SettingsSoundsActivity.VIB_TYPE H = app.H();
        if (H == SettingsSoundsActivity.VIB_TYPE.USE_DEFAULT) {
            z2 = true;
        } else if (H == SettingsSoundsActivity.VIB_TYPE.NO_VIB) {
            z2 = false;
        } else {
            if (app.e("android.permission.VIBRATE")) {
                builder.setVibrate(H.b());
            }
            z2 = false;
        }
        if (!app.e("android.permission.VIBRATE")) {
            z2 = false;
        }
        builder.setDefaults((z ? 1 : 0) | (z2 ? 2 : 0) | 4);
    }

    public static NotificationChannel a(App app, NotificationChannel notificationChannel) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 26) {
            if (app.e()) {
                notificationChannel.setSound(null, null);
                notificationChannel.enableVibration(false);
                notificationChannel.enableLights(false);
            } else {
                String G2 = app.G();
                if (G2 != null && !G2.equals("") && !G2.equals("content://settings/system/notification_sound")) {
                    Uri parse = Uri.parse(G2);
                    AudioAttributes.Builder builder = new AudioAttributes.Builder();
                    builder.setUsage(5);
                    notificationChannel.setSound(parse, builder.build());
                }
                SettingsSoundsActivity.VIB_TYPE H = app.H();
                if (H == SettingsSoundsActivity.VIB_TYPE.USE_DEFAULT) {
                    z = true;
                } else if (H == SettingsSoundsActivity.VIB_TYPE.NO_VIB) {
                    z = false;
                } else {
                    if (app.e("android.permission.VIBRATE")) {
                        notificationChannel.setVibrationPattern(H.b());
                    }
                    z = false;
                }
                if (app.e("android.permission.VIBRATE") ? z : false) {
                    notificationChannel.enableVibration(true);
                }
            }
        }
        return notificationChannel;
    }

    private int ap() {
        int aP = m().aP() + 1;
        if (aP > c) {
            return 1;
        }
        return aP;
    }

    private boolean a(int i, GameData gameData) {
        GameData b2;
        try {
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot check notification: ", th);
        }
        if (!m().h()) {
            H().info("ignoring alert because game alerts are off");
            m().bt();
            return false;
        }
        if (i < 100) {
            synchronized (this.B) {
                if (al().contains(Integer.valueOf(gameData.i()))) {
                    H().info("ignoring pushed game that we already notified: " + gameData.i());
                    return false;
                }
                u(gameData.i());
            }
        }
        Set<Integer> o = m().o();
        Set<Integer> r = m().r();
        TreeSet<Integer> x = m().x();
        if (o.isEmpty() && r.isEmpty() && x.isEmpty()) {
            m().bt();
            H().info(("pushed game " + gameData.i() + " " + gameData.q()) + " should not be received since NO leagues, groups, or teams selected. Ignoring and flagging for re-registering");
            return false;
        }
        int j = m().j();
        if (i < j && !m().i()) {
            m().bt();
            H().warning(("pushed game " + gameData.i() + " " + gameData.q()) + " below min gec: " + j + ", flagging for re-registering");
        }
        if (gameData.V() && (x.contains(gameData.t()) || x.contains(gameData.s()))) {
            return true;
        }
        if (App.ALERTS_FILTER.ALERTS_FILTER_ALL == m().k()) {
            if (!g()) {
                return true;
            }
            GameData b3 = b(Integer.valueOf(gameData.i()));
            if (b3 != null) {
                if (!(b3.i(new HashSet(d())) || b3.p(m().r()) || x.contains(b3.t()) || x.contains(b3.s()))) {
                    m().bt();
                    H().warning(("pushed game " + gameData.i() + " " + gameData.q()) + " with splg: " + gameData.H() + " for game outside of 'my sport': " + m().B() + " or my league-groups: " + m().C() + " , flagging for re-registering");
                }
            }
        } else if (App.ALERTS_FILTER.ALERTS_FILTER_TEAM == m().k()) {
            if (x.size() == 0) {
                H().info("ignoring alert, flagging for re-reg because using team-only filtering and have NO favorite teams");
                m().bt();
                return false;
            }
            if (gameData.V()) {
                r0 = x.contains(gameData.t()) || x.contains(gameData.s());
                b2 = gameData;
            } else {
                b2 = b(Integer.valueOf(gameData.i()));
                if (b2 == null) {
                    b2 = null;
                    r0 = true;
                } else if (x.contains(b2.s()) || x.contains(b2.t())) {
                    r0 = true;
                }
            }
            if (!r0) {
                m().bt();
                String str = "";
                if (b2 != null) {
                    str = " with h_id: " + b2.t() + " and a_id: " + b2.s();
                }
                H().warning(("pushed game " + gameData.i() + " " + gameData.q() + str) + " for game outside of 'my teams': " + m().D() + ", flagging for re-registering");
            }
        }
        return true;
    }

    private Intent t(int i) {
        Intent intent = new Intent(this, (Class<?>) InitialActivity.class);
        intent.setAction(i + "");
        intent.putExtra("game", i);
        intent.putExtra("isNotification", Boolean.TRUE);
        intent.setFlags(402653184);
        return intent;
    }

    private Intent g(String str) {
        Intent intent = new Intent(this, (Class<?>) InitialActivity.class);
        intent.setAction("friend" + str);
        intent.putExtra("sender", str);
        intent.putExtra("isNotification", Boolean.TRUE);
        intent.setFlags(402653184);
        return intent;
    }

    private Intent aq() {
        Intent intent = new Intent(this, (Class<?>) InitialActivity.class);
        intent.putExtra("isNotification", Boolean.TRUE);
        intent.setFlags(402653184);
        return intent;
    }

    public static int a(GameData gameData, RemoteViews remoteViews, String str, Map<Integer, bpz> map) {
        int a2 = gameData.a(false, map.keySet());
        int i = bbq.gec_dull;
        if (a2 == bbp.gray_dot_small) {
            i = bbq.gec_dull;
            a(remoteViews, i);
        } else if (a2 == bbp.yellow_dot_small) {
            i = bbq.gec_ok;
            a(remoteViews, i);
        } else if (a2 == bbp.orange_dot_small) {
            i = bbq.gec_good;
            a(remoteViews, i);
        } else if (a2 == bbp.red_dot_small) {
            i = bbq.gec_great;
            a(remoteViews, i);
        }
        remoteViews.setViewVisibility(i, 0);
        remoteViews.setTextViewText(i, str);
        return a2;
    }

    private static void a(RemoteViews remoteViews, int i) {
        for (int i2 : d) {
            if (i2 != i) {
                remoteViews.setViewVisibility(i2, 8);
            }
        }
    }

    private void a(String str, String str2, GameData gameData, Set<Integer> set) {
        try {
            View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(bbr.toast_game_eq, (ViewGroup) null);
            if (inflate != null) {
                ((TextView) inflate.findViewById(bbq.line1)).setText(str);
                GecCircleView gecCircleView = (GecCircleView) inflate.findViewById(bbq.game_gec);
                gecCircleView.setCircleColor(gameData.k(set));
                gecCircleView.setText(str2);
                a(inflate);
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem toasting: ", th);
        }
    }

    private void h(String str) {
        try {
            View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(bbr.toast_msg, (ViewGroup) null);
            if (inflate != null) {
                TextView textView = (TextView) inflate.findViewById(bbq.msg);
                if (str != null) {
                    str = str.trim();
                }
                textView.setText(str);
                a(inflate);
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem toasting: ", th);
        }
    }

    private void a(View view) {
        this.h.post(new bcd(this, view));
    }

    private void u(int i) {
        d(String.valueOf(i), "notificationMem");
    }

    private void d(String str, String str2) {
        try {
            String string = m().X().getString(str2, "");
            if (string.length() != 0) {
                while (string.split(",").length >= 100) {
                    string = string.substring(string.indexOf(",") + 1);
                }
                str = string + "," + str;
            }
            m().b(str2, str);
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    private void i(String str) {
        d(str, "notifyUnique");
    }

    @Override // com.thuuz.gtv.IService
    public void a(int i) {
    }

    public Handler I() {
        return this.h;
    }

    @Override // com.thuuz.gtv.IService
    public bpo a(FetchRequest fetchRequest) {
        bpo bpoVar = new bpo(fetchRequest);
        try {
            try {
            } catch (Throwable th) {
                H().log(Level.SEVERE, "cannot get games: ", th);
                try {
                    bpoVar.a(-1);
                    bbl c2 = fetchRequest.c();
                    if (c2 != null) {
                        c2.onConcludeFetch(bpoVar);
                    }
                } catch (Throwable th2) {
                    H().log(Level.SEVERE, "problem w/ callback after prev games failure: ", th2);
                }
            }
        } catch (OutOfMemoryError e) {
            Runtime.getRuntime().gc();
            H().severe("OoM with 'games': " + e);
            z();
            bbl c3 = fetchRequest.c();
            if (c3 != null) {
                c3.onConcludeFetch(bpoVar);
            }
        }
        if (fetchRequest.f().equals(FetchRequest.FORCING.CACHED_ONLY)) {
            a(bpoVar);
            bbl c4 = fetchRequest.c();
            if (c4 != null) {
                try {
                    c4.onConcludeFetch(bpoVar);
                } catch (Throwable th3) {
                    H().log(Level.SEVERE, "Cannot handle: ", th3);
                }
            }
            return bpoVar;
        }
        String e2 = fetchRequest.e();
        if (e2 != null) {
            fetchRequest.a(b(e2));
        }
        bce bceVar = new bce(this, m(), fetchRequest, this, true);
        m().bW().a(new bzc(bceVar, new byz(bceVar), new byy(bceVar)));
        return bpoVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x008a, code lost:
    
        if (r1.ao() == false) goto L70;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0067. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0070 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0084 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(defpackage.bpo r10) {
        /*
            r9 = this;
            r0 = 1
            r10.a(r0)     // Catch: java.lang.Throwable -> Lac
            r0 = 0
            r10.a(r0)     // Catch: java.lang.Throwable -> Lac
            java.util.List r0 = r10.c()     // Catch: java.lang.Throwable -> Lac
            r0.clear()     // Catch: java.lang.Throwable -> Lac
            com.thuuz.gtv.data.FetchRequest r0 = r10.i()     // Catch: java.lang.Throwable -> Lac
            java.util.Date r3 = r0.j()     // Catch: java.lang.Throwable -> Lac
            com.thuuz.gtv.data.FetchRequest r0 = r10.i()     // Catch: java.lang.Throwable -> Lac
            java.util.Date r4 = r0.i()     // Catch: java.lang.Throwable -> Lac
            com.thuuz.gtv.data.FetchRequest r0 = r10.i()     // Catch: java.lang.Throwable -> Lac
            com.thuuz.gtv.data.FetchRequest$REQ_TYPE r0 = r0.q()     // Catch: java.lang.Throwable -> Lac
            com.thuuz.gtv.data.FetchRequest$REQ_TYPE r1 = com.thuuz.gtv.data.FetchRequest.REQ_TYPE.SHARED     // Catch: java.lang.Throwable -> Lac
            boolean r5 = r0.equals(r1)     // Catch: java.lang.Throwable -> Lac
            java.util.HashSet r2 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lac
            r2.<init>()     // Catch: java.lang.Throwable -> Lac
            java.util.HashSet r0 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lac
            java.util.Map<java.lang.Integer, com.thuuz.gtv.data.GameData> r1 = r9.f     // Catch: java.lang.Throwable -> Lac
            java.util.Set r1 = r1.keySet()     // Catch: java.lang.Throwable -> Lac
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lac
            java.util.Iterator r6 = r0.iterator()     // Catch: java.lang.Throwable -> Lac
        L41:
            boolean r0 = r6.hasNext()     // Catch: java.lang.Throwable -> Lac
            if (r0 == 0) goto Ld0
            java.lang.Object r0 = r6.next()     // Catch: java.lang.Throwable -> Lac
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Throwable -> Lac
            java.util.Map<java.lang.Integer, com.thuuz.gtv.data.GameData> r1 = r9.f     // Catch: java.lang.Throwable -> Lac
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> Lac
            com.thuuz.gtv.data.GameData r1 = (com.thuuz.gtv.data.GameData) r1     // Catch: java.lang.Throwable -> Lac
            if (r1 == 0) goto L41
            com.thuuz.gtv.data.FetchRequest r7 = r10.i()     // Catch: java.lang.Throwable -> L90
            com.thuuz.gtv.IService$API_REQUEST_GAME_STATUS r7 = r7.d()     // Catch: java.lang.Throwable -> L90
            int[] r8 = defpackage.bcl.a     // Catch: java.lang.Throwable -> L90
            int r7 = r7.ordinal()     // Catch: java.lang.Throwable -> L90
            r7 = r8[r7]     // Catch: java.lang.Throwable -> L90
            switch(r7) {
                case 1: goto L6a;
                case 2: goto Lb9;
                case 3: goto Lc0;
                case 4: goto Lc8;
                default: goto L6a;
            }     // Catch: java.lang.Throwable -> L90
        L6a:
            boolean r7 = r1.al()     // Catch: java.lang.Throwable -> L90
            if (r7 != 0) goto L84
            if (r3 == 0) goto L84
            if (r4 == 0) goto L84
            java.util.Date r7 = r1.o()     // Catch: java.lang.Throwable -> L90
            boolean r8 = r7.before(r3)     // Catch: java.lang.Throwable -> L90
            if (r8 != 0) goto L41
            boolean r7 = r7.after(r4)     // Catch: java.lang.Throwable -> L90
            if (r7 != 0) goto L41
        L84:
            if (r5 == 0) goto L8c
            boolean r1 = r1.ao()     // Catch: java.lang.Throwable -> L90
            if (r1 == 0) goto L41
        L8c:
            r2.add(r0)     // Catch: java.lang.Throwable -> L90
            goto L41
        L90:
            r0 = move-exception
            java.util.logging.Logger r1 = r9.H()     // Catch: java.lang.Throwable -> Lac
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lac
            r7.<init>()     // Catch: java.lang.Throwable -> Lac
            java.lang.String r8 = "skipping game, problem: "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> Lac
            java.lang.StringBuilder r0 = r7.append(r0)     // Catch: java.lang.Throwable -> Lac
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lac
            r1.info(r0)     // Catch: java.lang.Throwable -> Lac
            goto L41
        Lac:
            r0 = move-exception
            java.util.logging.Logger r1 = r9.H()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "Problem filling from cache: "
            r1.log(r2, r3, r0)
        Lb8:
            return
        Lb9:
            boolean r7 = r1.am()     // Catch: java.lang.Throwable -> L90
            if (r7 != 0) goto L6a
            goto L41
        Lc0:
            boolean r7 = r1.al()     // Catch: java.lang.Throwable -> L90
            if (r7 != 0) goto L6a
            goto L41
        Lc8:
            boolean r7 = r1.ak()     // Catch: java.lang.Throwable -> L90
            if (r7 != 0) goto L6a
            goto L41
        Ld0:
            java.lang.String r0 = com.thuuz.gtv.App.e     // Catch: java.lang.Throwable -> Lac
            com.thuuz.gtv.data.FetchRequest r1 = r10.i()     // Catch: java.lang.Throwable -> Lac
            java.lang.String r1 = r1.e()     // Catch: java.lang.Throwable -> Lac
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Throwable -> Lac
            if (r0 != 0) goto Lf0
            java.util.HashSet r0 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lac
            r0.<init>()     // Catch: java.lang.Throwable -> Lac
            r9.a(r2, r0)     // Catch: java.lang.Throwable -> Lac
        Le8:
            java.util.List r1 = r10.c()     // Catch: java.lang.Throwable -> Lac
            r1.addAll(r0)     // Catch: java.lang.Throwable -> Lac
            goto Lb8
        Lf0:
            r0 = r2
            goto Le8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.ThuuzService.a(bpo):void");
    }

    @Override // com.thuuz.gtv.IService
    public String b(String str) {
        if ("all".equals(str) || str == null) {
            str = App.e;
        }
        if (App.b.equals(str)) {
            StringBuilder a2 = a(d());
            a(a2, e());
            return a2.toString();
        }
        if (App.c.equals(str)) {
            return App.c;
        }
        if (App.d.equals(str)) {
            return App.d;
        }
        if (App.e.equals(str)) {
            return App.e;
        }
        String[] j = m().j(str);
        if (j == null) {
            return null;
        }
        return m().a(j);
    }

    public StringBuilder a(List<bpr> list) {
        HashSet<bpr> hashSet = new HashSet(list);
        StringBuilder sb = new StringBuilder();
        for (bpr bprVar : hashSet) {
            sb.append(m().a(new String[]{bprVar.c().b(), bprVar.e()}));
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb;
    }

    public void a(StringBuilder sb, List<bps> list) {
        boolean z = list.size() > 0;
        if (z && sb.length() > 0) {
            sb.append(",");
        }
        for (bps bpsVar : list) {
            bpr d2 = bpsVar.d();
            sb.append(m().a(new String[]{d2.c().b(), d2.e()}));
            sb.append(".");
            sb.append(bpsVar.a());
            sb.append(",");
        }
        if (z) {
            sb.setLength(sb.length() - 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0127, code lost:
    
        r9 = r0.N();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012b, code lost:
    
        if (r9 == null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x012d, code lost:
    
        r10 = r9.length;
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x012f, code lost:
    
        if (r1 >= r10) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x013b, code lost:
    
        if (r6.contains(java.lang.Integer.valueOf(r9[r1])) == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x015e, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x013d, code lost:
    
        r0.O();
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0141, code lost:
    
        if (r1 != false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0161, code lost:
    
        r1 = false;
     */
    @Override // com.thuuz.gtv.IService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.util.Set<java.lang.Integer> r13, com.thuuz.gtv.data.FetchRequest r14) {
        /*
            Method dump skipped, instructions count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.ThuuzService.a(java.util.Set, com.thuuz.gtv.data.FetchRequest):void");
    }

    @Override // com.thuuz.gtv.IService
    public App m() {
        return (App) getApplication();
    }

    @Override // com.thuuz.gtv.IService
    public GameData b(Integer num) {
        return this.f.get(num);
    }

    @Override // com.thuuz.gtv.IService
    public boolean i() {
        if (!g()) {
            F();
            if (!g()) {
                h();
            }
            if (!g()) {
                H().warning("sportinfo problem; retrying");
                h();
            }
        }
        boolean g = g();
        if (!g) {
            H().warning("sportinfo problem; giving up");
        }
        return g;
    }

    @Override // com.thuuz.gtv.IService
    public boolean j() {
        String str;
        try {
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
        if (this.s) {
            return true;
        }
        this.s = ac();
        if (this.s) {
            Iterator<Integer> it = m().x().iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = "";
                    break;
                }
                if (a(it.next()) == null) {
                    this.s = false;
                    str = "getting teams missing from cache";
                    break;
                }
            }
        } else {
            this.s = p();
            str = "my team fetch problem; retrying";
        }
        if (!this.s) {
            H().info(str);
            this.s = p();
        }
        if (!this.s) {
            H().warning("my team fetch problem; giving up");
        }
        return this.s;
    }

    @Override // com.thuuz.gtv.IService
    public boolean p() {
        try {
            synchronized (this.T) {
                String D = m().D();
                if (D == null || D.length() == 0) {
                    this.t.clear();
                    return true;
                }
                bzw bzwVar = new bzw(m(), D);
                bzwVar.l();
                boolean y = bzwVar.y();
                if (bzwVar.y()) {
                    H().info("downloaded teams: " + bzwVar.b().size());
                    this.t.clear();
                    for (bpz bpzVar : bzwVar.b()) {
                        this.t.put(Integer.valueOf(bpzVar.b()), bpzVar);
                    }
                    bzwVar.a(bzwVar.x().toString());
                    for (Integer num : new HashSet(m().x())) {
                        if (this.t.get(num) == null) {
                            H().info("favorite team id: " + num + " not found in RAM cache after fetch");
                        }
                    }
                }
                return y;
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    @Override // com.thuuz.gtv.IService
    public Date n() {
        return new Date(System.currentTimeMillis() + this.g);
    }

    @Override // com.thuuz.gtv.IService
    public Map<Integer, Integer> o() {
        return new HashMap(this.L);
    }

    @Override // com.thuuz.gtv.IService
    public int a(String[] strArr) {
        Integer num;
        bpr bprVar;
        Integer num2;
        Integer num3;
        Integer num4 = 0;
        if (strArr == null || strArr.length == 0 || App.e.equals(strArr[0])) {
            num = o().get(-7);
        } else if (App.b.equals(strArr[0])) {
            num = Integer.valueOf(N());
        } else if (String.valueOf(-9).equals(strArr[0])) {
            Map<Integer, Integer> o = o();
            Set<Integer> o2 = m().o();
            Iterator<bps> it = e().iterator();
            while (it.hasNext()) {
                o2.add(Integer.valueOf(it.next().d().g()));
            }
            for (bpr bprVar2 : y()) {
                if (o2.contains(Integer.valueOf(bprVar2.g()))) {
                    num3 = num4;
                } else {
                    Integer num5 = o.get(Integer.valueOf(bprVar2.g()));
                    num3 = Integer.valueOf((num5 == null ? 0 : num5.intValue()) + num4.intValue());
                }
                num4 = num3;
            }
            num = num4;
        } else if (App.d.equals(strArr[0])) {
            num = Integer.valueOf(M());
        } else if (App.c.equals(strArr[0])) {
            num = ar();
        } else if (String.valueOf(-8).equals(strArr[0])) {
            List<bpr> y = y();
            Date n = n();
            for (bpr bprVar3 : y) {
                if (!bprVar3.a(n) && (num2 = this.L.get(Integer.valueOf(bprVar3.g()))) != null) {
                    num4 = Integer.valueOf(num2.intValue() + num4.intValue());
                }
            }
            num = num4;
        } else {
            String b2 = b(strArr);
            num = (b2 == null || (bprVar = this.k.get(b2)) == null) ? num4 : o().get(Integer.valueOf(bprVar.g()));
        }
        if (num == null) {
            num = 0;
        }
        return num.intValue();
    }

    private Integer ar() {
        Integer num = this.L.get(-2);
        return Integer.valueOf(num == null ? 0 : num.intValue());
    }

    public boolean J() {
        return this.E != null || this.i.size() > 0;
    }

    public bpv K() {
        bpv bpvVar;
        try {
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot handle: ", th);
            bpvVar = null;
        }
        if (this.E != null) {
            return this.E;
        }
        if (this.i.isEmpty()) {
            return null;
        }
        long j = m().X().getLong("lastMessageViewed", -1L);
        if (j != -1 && System.currentTimeMillis() - 43200000 < j && !af()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.i.values());
        Collections.sort(arrayList);
        bpvVar = (bpv) arrayList.get(0);
        return bpvVar;
    }

    @Override // com.thuuz.gtv.IService
    public void i(int i) {
        if (i != 0) {
            this.i.remove(Integer.valueOf(i));
            if (!af()) {
                m().a("lastMessageViewed", System.currentTimeMillis());
                m().b("MSGS_DISPLAYED", i);
                H().info("setting last msg displayed to: " + i);
                m().b("last_msg", i + "");
            }
        }
    }

    @Override // com.thuuz.gtv.IService
    public String a(String str) {
        bpr bprVar = this.k.get(str);
        if (bprVar != null) {
            return bprVar.f();
        }
        if (!"my_sports".equals(str)) {
            return "";
        }
        return "My Sports";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00cc A[Catch: Throwable -> 0x00fe, TryCatch #0 {Throwable -> 0x00fe, blocks: (B:3:0x0009, B:5:0x00a9, B:7:0x00b3, B:8:0x00ba, B:10:0x00cc, B:11:0x00dc, B:13:0x00e4), top: B:2:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00e4 A[Catch: Throwable -> 0x00fe, TRY_LEAVE, TryCatch #0 {Throwable -> 0x00fe, blocks: (B:3:0x0009, B:5:0x00a9, B:7:0x00b3, B:8:0x00ba, B:10:0x00cc, B:11:0x00dc, B:13:0x00e4), top: B:2:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.thuuz.gtv.data.GameData a(android.content.Intent r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.ThuuzService.a(android.content.Intent, java.lang.String):com.thuuz.gtv.data.GameData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, GameData gameData, String str) {
        H().info("pushed game id: " + i);
        try {
            if (i == -1) {
                H().info("received validation alert");
            } else {
                a(gameData.ar(), false, str);
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "cannot handle pushed alert: ", th);
        }
    }

    @Override // com.thuuz.gtv.IService
    public boolean g() {
        return this.r;
    }

    @Override // com.thuuz.gtv.IService
    public boolean a() {
        boolean z = this.f.size() > 0 || this.L.size() > 0;
        m().f(z);
        return z;
    }

    @Override // com.thuuz.gtv.IService
    public bpx b(int i) {
        return this.j.get(i);
    }

    @Override // com.thuuz.gtv.IService
    public bpr c(int i) {
        return this.l.get(Integer.valueOf(i));
    }

    @Override // com.thuuz.gtv.IService
    public bps d(int i) {
        return this.m.get(Integer.valueOf(i));
    }

    @Override // com.thuuz.gtv.IService
    public AlertSettings e(int i) {
        return this.o.get(Integer.valueOf(i));
    }

    @Override // com.thuuz.gtv.IService
    public List<bpx> b() {
        TreeSet treeSet = new TreeSet();
        for (int i = 0; i < this.j.size(); i++) {
            treeSet.add(this.j.get(this.j.keyAt(i)));
        }
        return new ArrayList(treeSet);
    }

    @Override // com.thuuz.gtv.IService
    public List<bpr> a(int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            for (bpr bprVar : new ArrayList(this.l.values())) {
                if (bprVar.l() && (!z || bprVar.a(n()))) {
                    if (bprVar.c().a() == i) {
                        arrayList.add(bprVar);
                    }
                }
            }
            Collections.sort(arrayList, new boe());
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public List<bpr> f(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            for (bpr bprVar : new ArrayList(this.l.values())) {
                if (bprVar.l() && bprVar.c().a() == i) {
                    arrayList.add(bprVar);
                }
            }
            Collections.sort(arrayList);
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public List<bpr> y() {
        ArrayList arrayList = new ArrayList();
        try {
            for (bpr bprVar : new ArrayList(this.l.values())) {
                if (bprVar.l()) {
                    arrayList.add(bprVar);
                }
            }
            Collections.sort(arrayList);
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public List<bps> g(int i) {
        ArrayList arrayList = new ArrayList();
        for (bps bpsVar : new ArrayList(this.m.values())) {
            if (bpsVar.d().g() == i) {
                arrayList.add(bpsVar);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public Map<Integer, List<bpr>> c() {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (bpr bprVar : new ArrayList(this.l.values())) {
            if (bprVar.l()) {
                if (bprVar.a(n())) {
                    arrayList.add(bprVar);
                } else {
                    arrayList2.add(bprVar);
                }
            }
        }
        Collections.sort(arrayList, new boe());
        Collections.sort(arrayList2, new boe());
        hashMap.put(a, arrayList);
        hashMap.put(b, arrayList2);
        return hashMap;
    }

    @Override // com.thuuz.gtv.IService
    public bpr a(String str, String str2) {
        return this.k.get(str + "_" + str2);
    }

    @Override // com.thuuz.gtv.IService
    public void h(int i) {
        GameData b2 = b(Integer.valueOf(i));
        a(b2.ar(), true, b2.T() + " " + b2.U() + " " + b2.q(m().x()));
    }

    private void as() {
        if (m().bD()) {
            if (System.currentTimeMillis() - 600000 < m().bf()) {
                H().info("ignoring ping within 10min of last");
                return;
            }
            bzi bziVar = new bzi(m());
            bziVar.l();
            int v = bziVar.v();
            m().bg();
            if (v == -11) {
                H().warning("re-registering with google after ping request finds no reg on Thuuz");
                m().M();
                aa();
            }
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        boolean onUnbind = super.onUnbind(intent);
        if (this.v.size() == 0) {
            at();
        }
        return onUnbind;
    }

    private synchronized void at() {
        try {
            if (this.v.size() > 0) {
                if (this.q != null) {
                    H().info("active binds; clearing kill timer");
                    this.q.cancel();
                    this.q.purge();
                    this.q = null;
                }
            } else {
                m().aw();
                if (this.q != null) {
                    this.q.cancel();
                    this.q.purge();
                    this.q = null;
                }
                this.q = new Timer("killer");
                long j = 15000;
                if (ay()) {
                    j = 60000;
                }
                H().info("starting kill timer for " + (j / 1000) + " seconds");
                this.q.schedule(new bcf(this), j);
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Cannot reset kill timer: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void au() {
        try {
            if (this.v.size() > 0) {
                at();
            } else if (this.F != null && this.F.b()) {
                H().info("resetting kill timer because wakelockTimer active");
                at();
            } else {
                if (System.currentTimeMillis() - m().R() > 86400000) {
                    c(true);
                }
                if (m().c()) {
                    m().au();
                    m().af();
                }
                m().av();
                m().c(false);
                H().info("calling service.stopSelf");
                stopSelf();
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Problem with killer timeout", th);
        }
    }

    private void av() {
        new Thread(new bcg(this)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        boolean z2;
        boolean z3 = true;
        try {
            if (!m().bD()) {
                if (m().aT() != null) {
                    ab();
                    return;
                }
                return;
            }
            if (m().aX() || !cr.g(m())) {
                aa();
                z2 = false;
            } else if (m().aO()) {
                G();
                z2 = false;
            } else {
                z2 = true;
            }
            if (z2) {
                new byf(m()).l();
                if (m().aO() || m().aX()) {
                    z3 = false;
                }
            } else {
                z3 = z2;
            }
            if (z && z3) {
                as();
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.IService
    public List<bpr> d() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = m().o().iterator();
        while (it.hasNext()) {
            bpr bprVar = this.l.get(it.next());
            if (bprVar != null) {
                arrayList.add(bprVar);
            }
        }
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public List<bps> e() {
        HashSet hashSet = new HashSet();
        Iterator<Integer> it = m().r().iterator();
        while (it.hasNext()) {
            bps d2 = d(it.next().intValue());
            if (d2 == null || !d2.f()) {
                if (d2 != null && d2.d() != null && d2.d().e() != null && d2.a() != null) {
                    hashSet.add(d2);
                }
            }
        }
        return new ArrayList(hashSet);
    }

    @Override // com.thuuz.gtv.IService
    public bpz a(Integer num) {
        return this.t.get(num);
    }

    @Override // com.thuuz.gtv.IService
    public List<bpr> f() {
        bps d2;
        ArrayList arrayList = new ArrayList();
        try {
            HashSet hashSet = new HashSet(this.l.keySet());
            Set<Integer> o = m().o();
            if (m().aA()) {
                for (Integer num : m().r()) {
                    if (!o.contains(num) && (d2 = d(num.intValue())) != null) {
                        o.add(Integer.valueOf(d2.d().g()));
                    }
                }
            }
            hashSet.removeAll(o);
            Date n = n();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                bpr c2 = c(((Integer) it.next()).intValue());
                if (c2 != null && c2.l() && c2.a(n)) {
                    arrayList.add(c2);
                }
            }
            Collections.sort(arrayList, new bcu(this));
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public bpn a(Date date, Date date2, String str, boolean z) {
        byw bywVar;
        bpn bpnVar = null;
        try {
            if (!g() && !i()) {
                return null;
            }
            FetchRequest fetchRequest = new FetchRequest(date, date2, z);
            if (App.d.equals(str)) {
                H().info("shared dates request");
                fetchRequest.a(FetchRequest.REQ_TYPE.SHARED);
                bywVar = new byw(m(), fetchRequest, this, true);
            } else {
                bywVar = new byw(m(), fetchRequest, this);
            }
            bywVar.l();
            if (!bywVar.y()) {
                return null;
            }
            bpnVar = bywVar.E();
            return bpnVar;
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
            return bpnVar;
        }
    }

    public void a(AbstractActivity abstractActivity) {
        if (abstractActivity != null) {
            try {
                abstractActivity.c(true);
                this.v.add(new bct(this, abstractActivity));
                at();
            } catch (Throwable th) {
                H().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    @Override // com.thuuz.gtv.IService
    public void a(Activity activity) {
        boolean z;
        try {
            ArrayList arrayList = new ArrayList(this.v);
            Collections.reverse(arrayList);
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                bct bctVar = (bct) it.next();
                AbstractActivity abstractActivity = (AbstractActivity) bct.a(bctVar).get();
                if (abstractActivity == null) {
                    H().warning("unexpected housekeeping: removing bound activity record with expired ref: " + bct.b(bctVar));
                    this.v.remove(bctVar);
                } else if (activity == abstractActivity) {
                    this.v.remove(bctVar);
                    z = true;
                    break;
                }
            }
            if (this.v.size() == 0) {
                at();
            }
            if (!z) {
                H().warning("cannot unbind activity: " + activity.getClass().getSimpleName());
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "Error removing binding: ", th);
        }
    }

    @Override // com.thuuz.gtv.IService
    public boolean a(Class cls) {
        String simpleName = cls.getSimpleName();
        Iterator it = new ArrayList(this.v).iterator();
        while (it.hasNext()) {
            if (bct.b((bct) it.next()).equals(simpleName)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.thuuz.gtv.IService
    public List<OnlineResourceCategory> q() {
        ArrayList arrayList = new ArrayList(this.n.values());
        Collections.sort(arrayList);
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public void a(Integer num, GameData gameData) {
        this.f.put(num, gameData);
    }

    public void L() {
        if (this.D == null && m().e()) {
            try {
                new Thread(new bch(this), "media_provider_read").start();
            } catch (Throwable th) {
                H().log(Level.SEVERE, "problem adding media selection listener: ", th);
            }
        }
    }

    private void aw() {
        if (m().i()) {
            try {
                this.W = true;
                H().info("calling for prefs download because we are syncing");
                new bzj(true, m()).l();
                new bzl(m()).l();
                new byl(m()).l();
            } catch (Throwable th) {
                H().log(Level.SEVERE, "problem attempting prefs download: ", th);
            }
        }
    }

    public int M() {
        Integer num = this.L.get(-5);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public int N() {
        Integer num = this.L.get(-1);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
    
        if (defpackage.bct.a(r0) == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
    
        return (android.app.Activity) defpackage.bct.a(r0).get();
     */
    @Override // com.thuuz.gtv.IService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.app.Activity a(java.lang.Class r5, int r6) {
        /*
            r4 = this;
            java.lang.String r1 = r5.getSimpleName()     // Catch: java.lang.Throwable -> L3c
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L3c
            java.util.ArrayList<bct> r2 = r4.v     // Catch: java.lang.Throwable -> L3c
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L3c
            java.util.Iterator r2 = r0.iterator()     // Catch: java.lang.Throwable -> L3c
        Lf:
            boolean r0 = r2.hasNext()     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L48
            java.lang.Object r0 = r2.next()     // Catch: java.lang.Throwable -> L3c
            bct r0 = (defpackage.bct) r0     // Catch: java.lang.Throwable -> L3c
            java.lang.String r3 = defpackage.bct.b(r0)     // Catch: java.lang.Throwable -> L3c
            boolean r3 = r3.equals(r1)     // Catch: java.lang.Throwable -> L3c
            if (r3 == 0) goto Lf
            int r3 = defpackage.bct.c(r0)     // Catch: java.lang.Throwable -> L3c
            if (r6 != r3) goto Lf
            java.lang.ref.WeakReference r1 = defpackage.bct.a(r0)     // Catch: java.lang.Throwable -> L3c
            if (r1 == 0) goto L48
            java.lang.ref.WeakReference r0 = defpackage.bct.a(r0)     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L3c
            android.app.Activity r0 = (android.app.Activity) r0     // Catch: java.lang.Throwable -> L3c
        L3b:
            return r0
        L3c:
            r0 = move-exception
            java.util.logging.Logger r1 = r4.H()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem: "
            r1.log(r2, r3, r0)
        L48:
            r0 = 0
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.ThuuzService.a(java.lang.Class, int):android.app.Activity");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0035, code lost:
    
        return (com.thuuz.gtv.activity.AbstractActivity) defpackage.bct.a(r0).get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0029, code lost:
    
        if (defpackage.bct.a(r0) == null) goto L13;
     */
    @Override // com.thuuz.gtv.IService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.thuuz.gtv.activity.AbstractActivity b(java.lang.Class r5) {
        /*
            r4 = this;
            java.lang.String r1 = r5.getSimpleName()     // Catch: java.lang.Throwable -> L36
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L36
            java.util.ArrayList<bct> r2 = r4.v     // Catch: java.lang.Throwable -> L36
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L36
            java.util.Iterator r2 = r0.iterator()     // Catch: java.lang.Throwable -> L36
        Lf:
            boolean r0 = r2.hasNext()     // Catch: java.lang.Throwable -> L36
            if (r0 == 0) goto L42
            java.lang.Object r0 = r2.next()     // Catch: java.lang.Throwable -> L36
            bct r0 = (defpackage.bct) r0     // Catch: java.lang.Throwable -> L36
            java.lang.String r3 = defpackage.bct.b(r0)     // Catch: java.lang.Throwable -> L36
            boolean r3 = r3.equals(r1)     // Catch: java.lang.Throwable -> L36
            if (r3 == 0) goto Lf
            java.lang.ref.WeakReference r1 = defpackage.bct.a(r0)     // Catch: java.lang.Throwable -> L36
            if (r1 == 0) goto L42
            java.lang.ref.WeakReference r0 = defpackage.bct.a(r0)     // Catch: java.lang.Throwable -> L36
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L36
            com.thuuz.gtv.activity.AbstractActivity r0 = (com.thuuz.gtv.activity.AbstractActivity) r0     // Catch: java.lang.Throwable -> L36
        L35:
            return r0
        L36:
            r0 = move-exception
            java.util.logging.Logger r1 = r4.H()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem: "
            r1.log(r2, r3, r0)
        L42:
            r0 = 0
            goto L35
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.gtv.ThuuzService.b(java.lang.Class):com.thuuz.gtv.activity.AbstractActivity");
    }

    @Override // com.thuuz.gtv.IService
    public void c(String str) {
        H().info("adding perpetual message: " + str);
        this.E = new bpv(str, 0, 1);
    }

    @Override // com.thuuz.gtv.IService
    public void a(bpz bpzVar) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        try {
            this.t.put(Integer.valueOf(bpzVar.b()), bpzVar);
            String ae = ae();
            if (ae.length() > 0) {
                jSONObject = new JSONObject(ae);
                jSONArray = jSONObject.getJSONArray("teams");
            } else {
                jSONObject = new JSONObject();
                jSONArray = new JSONArray();
                jSONObject.put("teams", jSONArray);
            }
            jSONArray.put(bpzVar.b(), bpzVar.ar());
            new bzw(m()).a(jSONObject.toString());
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem caching fav team: ", th);
        }
    }

    @Override // com.thuuz.gtv.IService
    public Map<Integer, bpz> k() {
        return new HashMap(this.t);
    }

    @Override // com.thuuz.gtv.IService
    public synchronized void l() {
        try {
            this.C.release();
        } catch (Throwable th) {
            H().log(Level.WARNING, "cannot release wake lock: " + th);
        }
    }

    @Override // com.thuuz.gtv.IService
    public bpe j(int i) {
        return this.H.get(Integer.valueOf(i));
    }

    @Override // com.thuuz.gtv.IService
    public void a(Map<Integer, bpe> map) {
        this.H = Collections.synchronizedMap(map);
    }

    @Override // com.thuuz.gtv.IService
    public TreeSet<bpe> r() {
        return new TreeSet<>(this.H.values());
    }

    @Override // com.thuuz.gtv.IService
    public FantasySite b(int i, int i2) {
        Map<Integer, FantasySite> map = this.I.get(Integer.valueOf(i2));
        if (map == null) {
            return null;
        }
        return map.get(Integer.valueOf(i));
    }

    @Override // com.thuuz.gtv.IService
    public void a(Integer num, Map<Integer, FantasySite> map) {
        this.I.put(num, Collections.synchronizedMap(map));
    }

    @Override // com.thuuz.gtv.IService
    public TreeSet<FantasySite> k(int i) {
        Map<Integer, FantasySite> map = this.I.get(Integer.valueOf(i));
        TreeSet<FantasySite> treeSet = new TreeSet<>();
        if (map != null) {
            treeSet.addAll(map.values());
        }
        return treeSet;
    }

    @Override // com.thuuz.gtv.IService
    public bpg l(int i) {
        Iterator<Map<Integer, bpg>> it = this.J.values().iterator();
        while (it.hasNext()) {
            bpg bpgVar = it.next().get(Integer.valueOf(i));
            if (bpgVar != null) {
                return bpgVar;
            }
        }
        return null;
    }

    @Override // com.thuuz.gtv.IService
    public synchronized void b(Integer num, Map<Integer, bpg> map) {
        if (map == null) {
            this.K.clear();
        } else {
            this.J.put(num, map);
        }
    }

    @Override // com.thuuz.gtv.IService
    public Map<Integer, bpg> m(int i) {
        Map<Integer, bpg> map = this.J.get(Integer.valueOf(i));
        return map == null ? new HashMap() : new HashMap(map);
    }

    @Override // com.thuuz.gtv.IService
    public TreeSet<bpg> a(int i, int i2) {
        TreeSet<bpg> treeSet = new TreeSet<>();
        FantasySite b2 = b(i, i2);
        if (b2 == null) {
            return treeSet;
        }
        Map<Integer, bpg> m = m(b2.b());
        Iterator<Integer> it = m.keySet().iterator();
        while (it.hasNext()) {
            bpg bpgVar = m.get(it.next());
            if (bpgVar.c() == i) {
                treeSet.add(bpgVar);
            }
        }
        return treeSet;
    }

    @Override // com.thuuz.gtv.IService
    public bpf n(int i) {
        return this.K.get(Integer.valueOf(i));
    }

    @Override // com.thuuz.gtv.IService
    public void a(int i, bpf bpfVar) {
        this.K.put(Integer.valueOf(i), bpfVar);
    }

    @Override // com.thuuz.gtv.IService
    public void u() {
        this.K.clear();
        this.I.clear();
        this.J.clear();
        v(0);
    }

    @Override // com.thuuz.gtv.IService
    public void c(int i, int i2) {
        Map<Integer, FantasySite> map = this.I.get(Integer.valueOf(i2));
        Iterator it = new ArrayList(map.keySet()).iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            if (num.intValue() == i) {
                map.remove(num);
            }
        }
        Map<Integer, bpg> map2 = this.J.get(Integer.valueOf(i2));
        Iterator it2 = new ArrayList(map2.keySet()).iterator();
        while (it2.hasNext()) {
            Integer num2 = (Integer) it2.next();
            if (map2.get(num2).c() == i) {
                map2.remove(num2);
                this.K.remove(num2);
                if (m().f(num2.intValue()) != null) {
                    m().e(num2.intValue());
                }
            }
        }
        v(0);
    }

    @Override // com.thuuz.gtv.IService
    public void b(Map<Integer, Integer> map) {
        this.L = map;
        this.V = System.currentTimeMillis();
    }

    @Override // com.thuuz.gtv.IService
    public RequestToken s() {
        return this.M;
    }

    @Override // com.thuuz.gtv.IService
    public void a(RequestToken requestToken) {
        this.M = requestToken;
    }

    @Override // com.thuuz.gtv.IService
    public Map<Integer, List<boz>> t() {
        return this.N;
    }

    @Override // com.thuuz.gtv.IService
    public Map<String, String> v() {
        try {
            boolean z = System.currentTimeMillis() > w().f() + 14400000;
            if (w().d() || z) {
                ax();
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
        return w().e();
    }

    private synchronized void ax() {
        if (this.P != null) {
            H().info("channel refresh already in progress; skipping redundant request");
        } else {
            boolean z = System.currentTimeMillis() > w().f() + 14400000;
            if (w().d() || z) {
                H().info("refreshing channel cache.");
                this.P = new Thread(new bck(this));
                this.P.start();
            } else {
                H().info("skipping channel refresh; already filled and current");
            }
        }
    }

    @Override // com.thuuz.gtv.IService
    public cbd w() {
        if (this.O == null) {
            this.O = new cbd(m(), this);
        }
        return this.O;
    }

    @Override // com.thuuz.gtv.IService
    public FantasySettingsCustomizations o(int i) {
        bpe j = j(i);
        if (j == null) {
            return null;
        }
        return this.R.get(Integer.valueOf(j.g()));
    }

    @Override // com.thuuz.gtv.IService
    public Map<Integer, bpb> x() {
        HashMap hashMap = new HashMap();
        Iterator<bpr> it = this.l.values().iterator();
        while (it.hasNext()) {
            for (bpb bpbVar : it.next().a()) {
                hashMap.put(Integer.valueOf(bpbVar.b()), bpbVar);
            }
        }
        return hashMap;
    }

    public void a(boolean z) {
        this.U = z;
    }

    private boolean ay() {
        return this.S != null;
    }

    @Override // com.thuuz.gtv.IService
    public void a(Set<Integer> set, Set<Integer> set2) {
        if (set2 != null) {
            if (set == null) {
                set2.clear();
                return;
            }
            try {
                set2.addAll(set);
            } catch (Throwable th) {
                H().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    @Override // com.thuuz.gtv.IService
    public List<bpr> d(String str) {
        ArrayList arrayList = new ArrayList();
        String[] j = m().j(str);
        if (j == null || j.length < 2) {
            H().severe("bad splg: " + str);
        } else {
            bpr a2 = a(j[0], j[1]);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    @Override // com.thuuz.gtv.IService
    public void A() {
        try {
            if (!this.W) {
                aw();
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.IService
    public int B() {
        int v;
        try {
            if (System.currentTimeMillis() - this.V < 60000) {
                H().info("skipping live fetch since last fetch was within cache lifetime");
                v = 0;
            } else {
                bze bzeVar = new bze(m(), this);
                bzeVar.l();
                v = bzeVar.v();
            }
            return v;
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem counting live: ", th);
            return -1;
        }
    }

    @Override // com.thuuz.gtv.IService
    public boolean C() {
        return this.s;
    }

    private void v(int i) {
        try {
            Intent intent = new Intent("fantasy_sync");
            intent.putExtra("result", i);
            m().sendBroadcast(intent, "com.thuuz.permission.local.broadcast");
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem broadcasting fantasy: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void az() {
        boolean z;
        try {
            byi byiVar = new byi(m(), this);
            byiVar.l();
            if (byiVar.y()) {
                if (!byiVar.b()) {
                    v(-404);
                    return;
                }
                boolean z2 = false;
                car carVar = new car();
                try {
                    try {
                        Iterator it = new ArrayList(this.H.values()).iterator();
                        while (it.hasNext()) {
                            bpe bpeVar = (bpe) it.next();
                            if (bpeVar.f()) {
                                carVar.a(new byr(m(), this, bpeVar.a()));
                                z = true;
                            } else {
                                z = z2;
                            }
                            z2 = z;
                        }
                        carVar.a(60000L);
                    } catch (Throwable th) {
                        H().log(Level.SEVERE, "problem syncing fantasy: ", th);
                        carVar.a();
                    }
                    if (!z2) {
                        v(-404);
                        return;
                    }
                    return;
                } finally {
                    carVar.a();
                }
            }
            v(-1);
        } catch (Throwable th2) {
            H().log(Level.SEVERE, "problem: ", th2);
            v(-1);
        }
    }

    private void aA() {
        try {
            this.C.acquire();
            Toast.makeText(this, "Out of Memory. Please exit via the back button. Either reboot or kill other apps, and try again in a couple minutes.", 1).show();
            Toast.makeText(this, "Out of Memory. Please exit via the back button. Either reboot or kill other apps, and try again in a couple minutes.", 1).show();
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem toasting oom: ", th);
        } finally {
            this.C.release();
        }
    }

    private void aB() {
        int i;
        int i2 = 0;
        try {
            this.C.acquire();
            File a2 = WidgetRemoteViewService.a(getCacheDir());
            if (a2 != null && a2.exists()) {
                byc bycVar = new byc(null, m());
                String a3 = cak.a(a2);
                if (a3.length() > 0) {
                    bycVar.a(false);
                    bycVar.d(new JSONObject(a3));
                    bycVar.a(0);
                    for (Integer num : bycVar.E().keySet()) {
                        GameData b2 = b(num);
                        GameData gameData = bycVar.E().get(num);
                        if (b2 == null) {
                            a(num, gameData);
                            i = i2 + 1;
                        } else {
                            Date f = b2.f();
                            Date f2 = gameData.f();
                            if (f == null || f2 == null || !f.before(f2)) {
                                i = i2;
                            } else {
                                a(num, gameData);
                                i = i2 + 1;
                            }
                        }
                        i2 = i;
                    }
                    H().info("read " + i2 + " games from widget cache");
                }
            }
        } catch (Throwable th) {
            H().log(Level.SEVERE, "problem updating widget: ", th);
        } finally {
            this.C.release();
        }
    }

    private boolean c(Intent intent) {
        boolean z = true;
        String stringExtra = intent.getStringExtra("zone");
        String stringExtra2 = intent.getStringExtra("type");
        if (stringExtra != null) {
            StringBuilder append = new StringBuilder("received push from zone: ").append(stringExtra);
            if (stringExtra2 != null) {
                append.append(", alert with type: ").append(stringExtra2);
            }
            H().warning(append.toString());
        }
        String stringExtra3 = intent.getStringExtra("msg_id");
        if (stringExtra3 != null) {
            synchronized (this.B) {
                if (am().contains(stringExtra3)) {
                    H().warning("dropping dup alert: " + bzy.logAllStringExtras(intent));
                    z = false;
                } else {
                    i(stringExtra3);
                }
            }
        }
        return z;
    }
}
