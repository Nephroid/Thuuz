package com.flurry.sdk;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.flurry.sdk.bx;
import com.flurry.sdk.cn;
import com.flurry.sdk.ei;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class cl implements cn.b, ei.a {
    private boolean B;
    private String C;
    private byte D;
    private long E;
    private long F;
    private boolean I;
    private int J;
    private int L;
    private int M;
    private Map<String, List<String>> O;
    private final Handler P;
    private cn Q;
    private a R;
    private int S;
    private File k;
    private String m;
    private String n;
    private List<cj> o;
    private boolean q;
    private long r;
    private String t;
    private long u;
    private long v;
    private long w;
    private long x;
    private String y;
    private String z;
    private static final String g = cl.class.getSimpleName();
    static int a = 100;
    static int b = 10;
    static int c = 1000;
    static int d = 160000;
    static int e = 50;
    static int f = 20;
    private final AtomicInteger h = new AtomicInteger(0);
    private final AtomicInteger i = new AtomicInteger(0);
    private final AtomicInteger j = new AtomicInteger(0);
    private volatile boolean l = false;
    private final Map<ej, ByteBuffer> p = new HashMap();
    private List<cj> s = new ArrayList();
    private byte A = -1;
    private final Map<String, bx.a> G = new HashMap();
    private final List<cb> H = new ArrayList();
    private final List<ca> K = new ArrayList();
    private final by N = new by();
    private boolean T = false;

    /* loaded from: classes.dex */
    public interface a {
        void d(String str);
    }

    public void a(Map<String, List<String>> map) {
        this.O = map;
    }

    Map<String, List<String>> a() {
        return this.O;
    }

    public void a(fi fiVar) {
        this.P.post(fiVar);
    }

    public void b() {
        this.q = true;
    }

    public cl(Context context, String str, a aVar) {
        this.k = null;
        this.y = "";
        this.z = "";
        ex.a(4, g, "Initializing new Flurry session");
        HandlerThread handlerThread = new HandlerThread("FlurryAgentSession_" + str);
        handlerThread.start();
        this.P = new Handler(handlerThread.getLooper());
        u();
        this.Q = new cn(this);
        this.R = aVar;
        this.m = str;
        this.k = context.getFileStreamPath(A());
        this.n = ep.a();
        this.w = -1L;
        this.L = 0;
        this.z = TimeZone.getDefault().getID();
        this.y = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
        this.I = true;
        this.J = 0;
        this.M = 0;
        r();
    }

    private void r() {
        ei a2 = eh.a();
        this.D = ((Byte) a2.a("Gender")).byteValue();
        a2.a("Gender", (ei.a) this);
        ex.a(4, g, "initSettings, Gender = " + ((int) this.D));
        this.C = (String) a2.a("UserId");
        a2.a("UserId", (ei.a) this);
        ex.a(4, g, "initSettings, UserId = " + this.C);
        this.B = ((Boolean) a2.a("LogEvents")).booleanValue();
        a2.a("LogEvents", (ei.a) this);
        ex.a(4, g, "initSettings, LogEvents = " + this.B);
        this.E = ((Long) a2.a("Age")).longValue();
        a2.a("Age", (ei.a) this);
        ex.a(4, g, "initSettings, BirthDate = " + this.E);
        this.F = ((Long) a2.a("ContinueSessionMillis")).longValue();
        a2.a("ContinueSessionMillis", (ei.a) this);
        ex.a(4, g, "initSettings, ContinueSessionMillis = " + this.F);
    }

    @Override // com.flurry.sdk.ei.a
    public void a(String str, Object obj) {
        if (str.equals("Gender")) {
            this.D = ((Byte) obj).byteValue();
            ex.a(4, g, "onSettingUpdate, Gender = " + ((int) this.D));
            return;
        }
        if (str.equals("UserId")) {
            this.C = (String) obj;
            ex.a(4, g, "onSettingUpdate, UserId = " + this.C);
            return;
        }
        if (str.equals("LogEvents")) {
            this.B = ((Boolean) obj).booleanValue();
            ex.a(4, g, "onSettingUpdate, LogEvents = " + this.B);
        } else if (str.equals("Age")) {
            this.E = ((Long) obj).longValue();
            ex.a(4, g, "onSettingUpdate, Birthdate = " + this.E);
        } else if (str.equals("ContinueSessionMillis")) {
            this.F = ((Long) obj).longValue();
            ex.a(4, g, "onSettingUpdate, ContinueSessionMillis = " + this.F);
        } else {
            ex.a(6, g, "onSettingUpdate internal error!");
        }
    }

    public synchronized void c() {
        if (this.Q.b()) {
            this.Q.a();
        }
        y();
        if (!this.T) {
            s();
            this.T = true;
        } else {
            t();
        }
    }

    private void s() {
        ex.e(g, "Start session");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.u = System.currentTimeMillis();
        this.v = elapsedRealtime;
        a(new fi() { // from class: com.flurry.sdk.cl.1
            @Override // com.flurry.sdk.fi
            public void a() {
                if (!cl.this.l) {
                    cl.this.d(eg.a().b());
                }
                cl.this.a(eg.a().b());
            }
        });
    }

    private void t() {
        ex.e(g, "Continuing previous session");
    }

    public synchronized void d() {
        ex.e(g, "Trying to end session");
        if (this.T) {
            this.w = SystemClock.elapsedRealtime() - this.v;
            a(this.w);
            v();
            if (i() > 0) {
                z();
            }
            if (i() == 0) {
                this.Q.a(this.F);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        try {
            byte[] a2 = em.a();
            if (a2 != null) {
                ex.a(3, g, "Fetched hashed IMEI");
                this.p.put(ej.Sha1Imei, ByteBuffer.wrap(a2));
            }
            c(context);
        } catch (Throwable th) {
            ex.a(6, g, "", th);
        }
    }

    public synchronized void e() {
        if (i() != 0) {
            ex.a(6, g, "Error! Session with apiKey = " + k() + " was ended while getSessionCount() is not 0");
        }
        if (this.T) {
            ex.e(g, "Ending session");
            this.S = 0;
            if (this.Q.b()) {
                this.Q.a();
            }
            w();
            if (this.R != null) {
                this.R.d(k());
            }
            eh.a().b("Gender", (ei.a) this);
            eh.a().b("UserId", (ei.a) this);
            eh.a().b("Age", (ei.a) this);
            eh.a().b("LogEvents", (ei.a) this);
            eh.a().b("ContinueSessionMillis", (ei.a) this);
            this.P.getLooper().quit();
        }
    }

    private void u() {
        if (TextUtils.isEmpty(this.t)) {
            a(new fi() { // from class: com.flurry.sdk.cl.2
                @Override // com.flurry.sdk.fi
                public void a() {
                    cl.this.t = eo.a();
                }
            });
        }
    }

    private void a(long j) {
        for (cb cbVar : this.H) {
            if (cbVar.a() && !cbVar.b()) {
                cbVar.a(j);
            }
        }
    }

    private void v() {
        a(new fi() { // from class: com.flurry.sdk.cl.3
            @Override // com.flurry.sdk.fi
            public void a() {
                cj f2 = cl.this.f();
                cl.this.s.clear();
                cl.this.s.add(f2);
                cl.this.x();
            }
        });
    }

    private void w() {
        a(new fi() { // from class: com.flurry.sdk.cl.4
            @Override // com.flurry.sdk.fi
            public void a() {
                cl.this.b(eg.a().b());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        boolean z;
        try {
            synchronized (this) {
                z = this.s.size() > 0;
            }
            if (z) {
                c(context);
            }
        } catch (Throwable th) {
            ex.a(6, g, "", th);
        }
    }

    synchronized cj f() {
        cj cjVar;
        ck ckVar = new ck();
        ckVar.a(this.n);
        ckVar.a(this.u);
        ckVar.b(this.w);
        ckVar.c(this.x);
        ckVar.b(l());
        ckVar.c(m());
        ckVar.a((int) this.A);
        ckVar.d(j());
        ckVar.a(E());
        ckVar.b(h());
        ckVar.a(this.D);
        ckVar.a(Long.valueOf(this.E));
        ckVar.a(q());
        ckVar.a(o());
        ckVar.a(this.I);
        ckVar.b(p());
        ckVar.c(this.L);
        try {
            cjVar = new cj(ckVar);
        } catch (IOException e2) {
            e2.printStackTrace();
            cjVar = null;
        }
        if (cjVar == null) {
            ex.e(g, "New session report wasn't created");
        }
        return cjVar;
    }

    public synchronized void g() {
        this.M++;
    }

    int h() {
        return this.M;
    }

    public synchronized void a(String str, Map<String, String> map, boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.v;
        String a2 = fh.a(str);
        if (a2.length() != 0) {
            bx.a aVar = this.G.get(a2);
            if (aVar == null) {
                if (this.G.size() < a) {
                    bx.a aVar2 = new bx.a();
                    aVar2.a = 1;
                    this.G.put(a2, aVar2);
                    ex.e(g, "Event count started: " + a2);
                } else {
                    ex.e(g, "Too many different events. Event not counted: " + a2);
                }
            } else {
                aVar.a++;
                ex.e(g, "Event count incremented: " + a2);
            }
            if (this.B && this.H.size() < c && this.J < d) {
                Map<String, String> emptyMap = map == null ? Collections.emptyMap() : map;
                if (emptyMap.size() > b) {
                    ex.e(g, "MaxEventParams exceeded: " + emptyMap.size());
                } else {
                    cb cbVar = new cb(B(), a2, emptyMap, elapsedRealtime, z);
                    if (cbVar.d() + this.J <= d) {
                        this.H.add(cbVar);
                        this.J = cbVar.d() + this.J;
                    } else {
                        this.J = d;
                        this.I = false;
                        ex.e(g, "Event Log size exceeded. No more event details logged.");
                    }
                }
            } else {
                this.I = false;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
    
        if (r9 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0026, code lost:
    
        if (r9.size() <= 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
    
        if (r7.J >= com.flurry.sdk.cl.d) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
    
        r1 = r7.J - r0.d();
        r4 = new java.util.HashMap(r0.c());
        r0.a(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
    
        if ((r0.d() + r1) > com.flurry.sdk.cl.d) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0054, code lost:
    
        if (r0.c().size() <= com.flurry.sdk.cl.b) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
    
        com.flurry.sdk.ex.e(com.flurry.sdk.cl.g, "MaxEventParams exceeded on endEvent: " + r0.c().size());
        r0.b(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
    
        r7.J = r1 + r0.d();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0089, code lost:
    
        r0.b(r4);
        r7.I = false;
        r7.J = com.flurry.sdk.cl.d;
        com.flurry.sdk.ex.e(com.flurry.sdk.cl.g, "Event Log size exceeded. No more event details logged.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0079, code lost:
    
        r0.a(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
    
        r2 = android.os.SystemClock.elapsedRealtime() - r7.v;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void a(java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.util.List<com.flurry.sdk.cb> r0 = r7.H     // Catch: java.lang.Throwable -> L86
            java.util.Iterator r1 = r0.iterator()     // Catch: java.lang.Throwable -> L86
        L7:
            boolean r0 = r1.hasNext()     // Catch: java.lang.Throwable -> L86
            if (r0 == 0) goto L7c
            java.lang.Object r0 = r1.next()     // Catch: java.lang.Throwable -> L86
            com.flurry.sdk.cb r0 = (com.flurry.sdk.cb) r0     // Catch: java.lang.Throwable -> L86
            boolean r2 = r0.a(r8)     // Catch: java.lang.Throwable -> L86
            if (r2 == 0) goto L7
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L86
            long r4 = r7.v     // Catch: java.lang.Throwable -> L86
            long r2 = r2 - r4
            if (r9 == 0) goto L79
            int r1 = r9.size()     // Catch: java.lang.Throwable -> L86
            if (r1 <= 0) goto L79
            int r1 = r7.J     // Catch: java.lang.Throwable -> L86
            int r4 = com.flurry.sdk.cl.d     // Catch: java.lang.Throwable -> L86
            if (r1 >= r4) goto L79
            int r1 = r7.J     // Catch: java.lang.Throwable -> L86
            int r4 = r0.d()     // Catch: java.lang.Throwable -> L86
            int r1 = r1 - r4
            java.util.HashMap r4 = new java.util.HashMap     // Catch: java.lang.Throwable -> L86
            java.util.Map r5 = r0.c()     // Catch: java.lang.Throwable -> L86
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L86
            r0.a(r9)     // Catch: java.lang.Throwable -> L86
            int r5 = r0.d()     // Catch: java.lang.Throwable -> L86
            int r5 = r5 + r1
            int r6 = com.flurry.sdk.cl.d     // Catch: java.lang.Throwable -> L86
            if (r5 > r6) goto L89
            java.util.Map r5 = r0.c()     // Catch: java.lang.Throwable -> L86
            int r5 = r5.size()     // Catch: java.lang.Throwable -> L86
            int r6 = com.flurry.sdk.cl.b     // Catch: java.lang.Throwable -> L86
            if (r5 <= r6) goto L7e
            java.lang.String r1 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L86
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86
            r5.<init>()     // Catch: java.lang.Throwable -> L86
            java.lang.String r6 = "MaxEventParams exceeded on endEvent: "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> L86
            java.util.Map r6 = r0.c()     // Catch: java.lang.Throwable -> L86
            int r6 = r6.size()     // Catch: java.lang.Throwable -> L86
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L86
            com.flurry.sdk.ex.e(r1, r5)     // Catch: java.lang.Throwable -> L86
            r0.b(r4)     // Catch: java.lang.Throwable -> L86
        L79:
            r0.a(r2)     // Catch: java.lang.Throwable -> L86
        L7c:
            monitor-exit(r7)
            return
        L7e:
            int r4 = r0.d()     // Catch: java.lang.Throwable -> L86
            int r1 = r1 + r4
            r7.J = r1     // Catch: java.lang.Throwable -> L86
            goto L79
        L86:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L89:
            r0.b(r4)     // Catch: java.lang.Throwable -> L86
            r1 = 0
            r7.I = r1     // Catch: java.lang.Throwable -> L86
            int r1 = com.flurry.sdk.cl.d     // Catch: java.lang.Throwable -> L86
            r7.J = r1     // Catch: java.lang.Throwable -> L86
            java.lang.String r1 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L86
            java.lang.String r4 = "Event Log size exceeded. No more event details logged."
            com.flurry.sdk.ex.e(r1, r4)     // Catch: java.lang.Throwable -> L86
            goto L79
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.cl.a(java.lang.String, java.util.Map):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d A[Catch: all -> 0x009f, TRY_LEAVE, TryCatch #0 {, blocks: (B:26:0x0004, B:5:0x000d, B:7:0x001d, B:13:0x005e, B:15:0x0066, B:17:0x0074, B:20:0x0080, B:12:0x00a6), top: B:25:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void a(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.Throwable r13) {
        /*
            r9 = this;
            r0 = 0
            monitor-enter(r9)
            if (r10 == 0) goto L59
            java.lang.String r1 = "uncaught"
            boolean r1 = r1.equals(r10)     // Catch: java.lang.Throwable -> L9f
            if (r1 == 0) goto L59
            r1 = 1
        Ld:
            int r2 = r9.L     // Catch: java.lang.Throwable -> L9f
            int r2 = r2 + 1
            r9.L = r2     // Catch: java.lang.Throwable -> L9f
            java.util.List<com.flurry.sdk.ca> r2 = r9.K     // Catch: java.lang.Throwable -> L9f
            int r2 = r2.size()     // Catch: java.lang.Throwable -> L9f
            int r3 = com.flurry.sdk.cl.e     // Catch: java.lang.Throwable -> L9f
            if (r2 >= r3) goto L5b
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L9f
            java.lang.Long r2 = java.lang.Long.valueOf(r0)     // Catch: java.lang.Throwable -> L9f
            com.flurry.sdk.ca r0 = new com.flurry.sdk.ca     // Catch: java.lang.Throwable -> L9f
            int r1 = r9.C()     // Catch: java.lang.Throwable -> L9f
            long r2 = r2.longValue()     // Catch: java.lang.Throwable -> L9f
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r0.<init>(r1, r2, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L9f
            java.util.List<com.flurry.sdk.ca> r1 = r9.K     // Catch: java.lang.Throwable -> L9f
            r1.add(r0)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r1 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L9f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9f
            r2.<init>()     // Catch: java.lang.Throwable -> L9f
            java.lang.String r3 = "Error logged: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r0 = r0.c()     // Catch: java.lang.Throwable -> L9f
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L9f
            com.flurry.sdk.ex.e(r1, r0)     // Catch: java.lang.Throwable -> L9f
        L57:
            monitor-exit(r9)
            return
        L59:
            r1 = r0
            goto Ld
        L5b:
            if (r1 == 0) goto La6
            r8 = r0
        L5e:
            java.util.List<com.flurry.sdk.ca> r0 = r9.K     // Catch: java.lang.Throwable -> L9f
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L9f
            if (r8 >= r0) goto L57
            java.util.List<com.flurry.sdk.ca> r0 = r9.K     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r0 = r0.get(r8)     // Catch: java.lang.Throwable -> L9f
            com.flurry.sdk.ca r0 = (com.flurry.sdk.ca) r0     // Catch: java.lang.Throwable -> L9f
            java.lang.String r1 = r0.c()     // Catch: java.lang.Throwable -> L9f
            if (r1 == 0) goto La2
            java.lang.String r1 = "uncaught"
            java.lang.String r0 = r0.c()     // Catch: java.lang.Throwable -> L9f
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Throwable -> L9f
            if (r0 != 0) goto La2
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L9f
            java.lang.Long r2 = java.lang.Long.valueOf(r0)     // Catch: java.lang.Throwable -> L9f
            com.flurry.sdk.ca r0 = new com.flurry.sdk.ca     // Catch: java.lang.Throwable -> L9f
            int r1 = r9.C()     // Catch: java.lang.Throwable -> L9f
            long r2 = r2.longValue()     // Catch: java.lang.Throwable -> L9f
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r0.<init>(r1, r2, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L9f
            java.util.List<com.flurry.sdk.ca> r1 = r9.K     // Catch: java.lang.Throwable -> L9f
            r1.set(r8, r0)     // Catch: java.lang.Throwable -> L9f
            goto L57
        L9f:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        La2:
            int r0 = r8 + 1
            r8 = r0
            goto L5e
        La6:
            java.lang.String r0 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L9f
            java.lang.String r1 = "Max errors logged. No more errors logged."
            com.flurry.sdk.ex.e(r0, r1)     // Catch: java.lang.Throwable -> L9f
            goto L57
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.cl.a(java.lang.String, java.lang.String, java.lang.String, java.lang.Throwable):void");
    }

    private void c(Context context) {
        try {
            ex.a(3, g, "generating agent report");
            cc ccVar = new cc(this.m, this.n, D(), this.q, this.r, this.u, this.s, this.p, this.N.a(false), a(), System.currentTimeMillis());
            this.o = new ArrayList(this.s);
            if (ccVar != null && ccVar.a() != null) {
                ex.a(3, g, "generated report of size " + ccVar.a().length + " with " + this.s.size() + " reports.");
                a(ccVar.a());
                this.s.removeAll(this.o);
                this.o = null;
                x();
            } else {
                ex.e(g, "Error generating report");
            }
        } catch (Throwable th) {
            ex.a(6, g, "", th);
        }
    }

    private void a(byte[] bArr) {
        bx.a().o().b(bArr, this.m, "" + bx.a().b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void x() {
        if (!et.a(this.k)) {
            ex.e(g, "Error persisting report: could not create directory");
        } else {
            try {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(this.k));
                    cm cmVar = new cm();
                    cmVar.a(this.q);
                    cmVar.a(this.r);
                    cmVar.a(this.s);
                    cmVar.a(dataOutputStream, this.m, D());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (FileNotFoundException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005f A[Catch: all -> 0x0090, Throwable -> 0x00a2, TryCatch #3 {Throwable -> 0x00a2, blocks: (B:16:0x005b, B:18:0x005f, B:20:0x0067, B:22:0x009a), top: B:15:0x005b, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0073 A[Catch: all -> 0x0090, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0009, B:14:0x0058, B:16:0x005b, B:18:0x005f, B:20:0x0067, B:22:0x009a, B:24:0x006f, B:26:0x0073, B:33:0x00a4, B:39:0x008c, B:42:0x0095, B:43:0x0098, B:48:0x00ac), top: B:2:0x0001, inners: #3 }] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Closeable] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00a4 -> B:23:0x006f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void d(android.content.Context r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.io.File r0 = r4.k     // Catch: java.lang.Throwable -> L90
            boolean r0 = r0.exists()     // Catch: java.lang.Throwable -> L90
            if (r0 == 0) goto Lac
            r0 = 4
            java.lang.String r1 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L90
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L90
            r2.<init>()     // Catch: java.lang.Throwable -> L90
            java.lang.String r3 = "loading persistent data: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L90
            java.io.File r3 = r4.k     // Catch: java.lang.Throwable -> L90
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.Throwable -> L90
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L90
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L90
            com.flurry.sdk.ex.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L90
            r2 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            java.io.File r1 = r4.k     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            com.flurry.sdk.cm r0 = new com.flurry.sdk.cm     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            r0.<init>()     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            java.lang.String r2 = r4.m     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            boolean r2 = r0.a(r1, r2)     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            r4.l = r2     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            boolean r2 = r4.l     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            if (r2 == 0) goto L58
            boolean r2 = r0.a()     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            r4.q = r2     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            long r2 = r0.c()     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            r4.r = r2     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            java.util.List r0 = r0.b()     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            r4.s = r0     // Catch: java.io.IOException -> L7f java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
        L58:
            com.flurry.sdk.fh.a(r1)     // Catch: java.lang.Throwable -> L90
        L5b:
            boolean r0 = r4.l     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> La2
            if (r0 != 0) goto L6f
            java.io.File r0 = r4.k     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> La2
            boolean r0 = r0.delete()     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> La2
            if (r0 == 0) goto L99
            r0 = 3
            java.lang.String r1 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> La2
            java.lang.String r2 = "Deleted persistence file"
            com.flurry.sdk.ex.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> La2
        L6f:
            boolean r0 = r4.l     // Catch: java.lang.Throwable -> L90
            if (r0 != 0) goto L7d
            r0 = 0
            r4.q = r0     // Catch: java.lang.Throwable -> L90
            long r0 = r4.u     // Catch: java.lang.Throwable -> L90
            r4.r = r0     // Catch: java.lang.Throwable -> L90
            r0 = 1
            r4.l = r0     // Catch: java.lang.Throwable -> L90
        L7d:
            monitor-exit(r4)
            return
        L7f:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb5
            goto L58
        L84:
            r0 = move-exception
        L85:
            java.lang.String r2 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r3 = "Error when loading persistent file"
            com.flurry.sdk.ex.c(r2, r3, r0)     // Catch: java.lang.Throwable -> Lb5
            com.flurry.sdk.fh.a(r1)     // Catch: java.lang.Throwable -> L90
            goto L5b
        L90:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L93:
            r0 = move-exception
            r1 = r2
        L95:
            com.flurry.sdk.fh.a(r1)     // Catch: java.lang.Throwable -> L90
            throw r0     // Catch: java.lang.Throwable -> L90
        L99:
            r0 = 6
            java.lang.String r1 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> La2
            java.lang.String r2 = "Cannot delete persistence file"
            com.flurry.sdk.ex.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> La2
            goto L6f
        La2:
            r0 = move-exception
            r1 = 6
            java.lang.String r2 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L90
            java.lang.String r3 = ""
            com.flurry.sdk.ex.a(r1, r2, r3, r0)     // Catch: java.lang.Throwable -> L90
            goto L6f
        Lac:
            r0 = 4
            java.lang.String r1 = com.flurry.sdk.cl.g     // Catch: java.lang.Throwable -> L90
            java.lang.String r2 = "Agent cache file doesn't exist."
            com.flurry.sdk.ex.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L90
            goto L6f
        Lb5:
            r0 = move-exception
            goto L95
        Lb7:
            r0 = move-exception
            r1 = r2
            goto L85
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.cl.d(android.content.Context):void");
    }

    private void y() {
        this.S++;
    }

    private void z() {
        this.S--;
    }

    int i() {
        return this.S;
    }

    private String A() {
        return ".flurryagent." + Integer.toString(this.m.hashCode(), 16);
    }

    private int B() {
        return this.h.incrementAndGet();
    }

    private int C() {
        return this.i.incrementAndGet();
    }

    String j() {
        return this.C == null ? "" : this.C;
    }

    private String D() {
        return this.t;
    }

    public String k() {
        return this.m;
    }

    public String l() {
        return this.y;
    }

    public String m() {
        return this.z;
    }

    private Location E() {
        return bx.a().n();
    }

    @Override // com.flurry.sdk.cn.b
    public void n() {
        e();
    }

    List<cb> o() {
        return this.H;
    }

    List<ca> p() {
        return this.K;
    }

    Map<String, bx.a> q() {
        return this.G;
    }
}
