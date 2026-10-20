package com.google.android.gms.analytics;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.hb;
import defpackage.afx;
import defpackage.afy;
import defpackage.dz;
import defpackage.et;
import defpackage.ex;
import defpackage.ey;
import defpackage.fa;
import defpackage.fb;
import defpackage.fc;
import defpackage.fh;
import defpackage.fu;
import defpackage.fx;
import defpackage.fy;
import defpackage.fz;
import defpackage.ga;
import defpackage.gb;
import defpackage.gc;
import defpackage.gn;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public class r implements et, fa, fb {
    private volatile long a;
    private volatile a b;
    private volatile ex c;
    private fc d;
    private fc e;
    private final dz f;
    private final fh g;
    private final Context h;
    private final Queue<gb> i;
    private volatile int j;
    private volatile Timer k;
    private volatile Timer l;
    private volatile Timer m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private afx r;
    private long s;

    /* loaded from: classes.dex */
    public enum a {
        CONNECTING,
        CONNECTED_SERVICE,
        CONNECTED_LOCAL,
        BLOCKED,
        PENDING_CONNECTION,
        PENDING_DISCONNECT,
        DISCONNECTED
    }

    public r(Context context, fh fhVar) {
        this(context, fhVar, null, dz.a(context));
    }

    r(Context context, fh fhVar, fc fcVar, dz dzVar) {
        this.i = new ConcurrentLinkedQueue();
        this.s = 300000L;
        this.e = fcVar;
        this.h = context;
        this.g = fhVar;
        this.f = dzVar;
        this.r = afy.c();
        this.j = 0;
        this.b = a.DISCONNECTED;
    }

    private Timer a(Timer timer) {
        if (timer == null) {
            return null;
        }
        timer.cancel();
        return null;
    }

    private void g() {
        this.k = a(this.k);
        this.l = a(this.l);
        this.m = a(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public synchronized void h() {
        if (Thread.currentThread().equals(this.g.d())) {
            if (this.o) {
                f();
            }
            switch (fy.a[this.b.ordinal()]) {
                case 1:
                    while (!this.i.isEmpty()) {
                        gb poll = this.i.poll();
                        gn.c("Sending hit to store  " + poll);
                        this.d.a(poll.a(), poll.b(), poll.c(), poll.d());
                    }
                    if (this.n) {
                        i();
                        break;
                    }
                    break;
                case 2:
                    while (!this.i.isEmpty()) {
                        gb peek = this.i.peek();
                        gn.c("Sending hit to service   " + peek);
                        if (this.f.b()) {
                            gn.c("Dry run enabled. Hit not actually sent to service.");
                        } else {
                            this.c.a(peek.a(), peek.b(), peek.c(), peek.d());
                        }
                        this.i.poll();
                    }
                    this.a = this.r.b();
                    break;
                case 6:
                    gn.c("Need to reconnect");
                    if (!this.i.isEmpty()) {
                        k();
                        break;
                    }
                    break;
                case 7:
                    gn.c("Blocked. Dropping hits.");
                    this.i.clear();
                    break;
            }
        } else {
            this.g.c().add(new fx(this));
        }
    }

    private void i() {
        this.d.c();
        this.n = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() {
        if (this.b != a.CONNECTED_LOCAL) {
            if (this.h == null || !"com.google.android.gms".equals(this.h.getPackageName())) {
                g();
                gn.c("falling back to local store");
                if (this.e != null) {
                    this.d = this.e;
                } else {
                    fu c = fu.c();
                    c.a(this.h, this.g);
                    this.d = c.d();
                }
                this.b = a.CONNECTED_LOCAL;
                h();
            } else {
                this.b = a.BLOCKED;
                this.c.c();
                gn.d("Attempted to fall back to local store from service.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void k() {
        if (this.q || this.c == null || this.b == a.CONNECTED_LOCAL) {
            gn.d("client not initialized.");
            j();
        } else {
            try {
                this.j++;
                a(this.l);
                this.b = a.CONNECTING;
                this.l = new Timer("Failed Connect");
                this.l.schedule(new ga(this, null), 3000L);
                gn.c("connecting to Analytics service");
                this.c.b();
            } catch (SecurityException e) {
                gn.d("security exception on connectToService");
                j();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void l() {
        if (this.c != null && this.b == a.CONNECTED_SERVICE) {
            this.b = a.PENDING_DISCONNECT;
            this.c.c();
        }
    }

    private void m() {
        this.k = a(this.k);
        this.k = new Timer("Service Reconnect");
        this.k.schedule(new gc(this, null), 5000L);
    }

    @Override // defpackage.et
    public synchronized void a() {
        if (!this.q) {
            gn.c("setForceLocalDispatch called.");
            this.q = true;
            switch (fy.a[this.b.ordinal()]) {
                case 2:
                    l();
                    break;
                case 3:
                    this.p = true;
                    break;
            }
        }
    }

    @Override // defpackage.fb
    public synchronized void a(int i, Intent intent) {
        this.b = a.PENDING_CONNECTION;
        if (this.j < 2) {
            gn.d("Service unavailable (code=" + i + "), will retry.");
            m();
        } else {
            gn.d("Service unavailable (code=" + i + "), using local store.");
            j();
        }
    }

    @Override // defpackage.et
    public void a(Map<String, String> map, long j, String str, List<hb> list) {
        gn.c("putHit called");
        this.i.add(new gb(map, j, str, list));
        h();
    }

    @Override // defpackage.et
    public void b() {
        switch (fy.a[this.b.ordinal()]) {
            case 1:
                i();
                return;
            case 2:
                return;
            default:
                this.n = true;
                return;
        }
    }

    @Override // defpackage.et
    public void c() {
        if (this.c != null) {
            return;
        }
        this.c = new ey(this.h, this, this);
        k();
    }

    @Override // defpackage.fa
    public synchronized void d() {
        this.l = a(this.l);
        this.j = 0;
        gn.c("Connected to service");
        this.b = a.CONNECTED_SERVICE;
        if (this.p) {
            l();
            this.p = false;
        } else {
            h();
            this.m = a(this.m);
            this.m = new Timer("disconnect check");
            this.m.schedule(new fz(this, null), this.s);
        }
    }

    @Override // defpackage.fa
    public synchronized void e() {
        if (this.b == a.BLOCKED) {
            gn.c("Service blocked.");
            g();
        } else if (this.b == a.PENDING_DISCONNECT) {
            gn.c("Disconnected from service");
            g();
            this.b = a.DISCONNECTED;
        } else {
            gn.c("Unexpected disconnect.");
            this.b = a.PENDING_CONNECTION;
            if (this.j < 2) {
                m();
            } else {
                j();
            }
        }
    }

    public void f() {
        gn.c("clearHits called");
        this.i.clear();
        switch (fy.a[this.b.ordinal()]) {
            case 1:
                this.d.a(0L);
                this.o = false;
                return;
            case 2:
                this.c.a();
                this.o = false;
                return;
            default:
                this.o = true;
                return;
        }
    }
}
