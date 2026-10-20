package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import defpackage.aas;
import defpackage.aav;
import defpackage.aaw;
import defpackage.aax;
import defpackage.aay;
import defpackage.aaz;
import defpackage.acg;
import defpackage.ach;
import defpackage.acq;
import defpackage.acx;
import defpackage.adm;
import defpackage.adn;
import defpackage.ads;
import defpackage.co;
import defpackage.vw;
import defpackage.vz;
import defpackage.we;
import defpackage.wi;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public class fe extends acq implements ads {
    private final wi a;
    private final aav b;
    private final gv c;
    private final Context e;
    private final ach g;
    private fk h;
    private vw j;
    private vz k;
    private we l;
    private final Object d = new Object();
    private final Object f = new Object();
    private boolean i = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class a extends Exception {
        private final int tc;

        public a(String str, int i) {
            super(str);
            this.tc = i;
        }

        public int a() {
            return this.tc;
        }
    }

    public fe(Context context, ach achVar, gv gvVar, wi wiVar, aav aavVar) {
        this.e = context;
        this.g = achVar;
        this.h = achVar.b;
        this.c = gvVar;
        this.a = wiVar;
        this.b = aavVar;
        this.k = achVar.c;
    }

    private void a(fi fiVar, long j) {
        synchronized (this.d) {
            this.j = new vw(this.e, fiVar, this.a, this.k);
        }
        this.l = this.j.a(j, 60000L);
        switch (this.l.a) {
            case 0:
                return;
            case 1:
                throw new a("No fill from any mediation ad networks.", 3);
            default:
                throw new a("Unexpected mediation result: " + this.l.a, 0);
        }
    }

    private void b(long j) {
        adm.a.post(new aay(this));
        c(j);
    }

    private void c(long j) {
        while (d(j)) {
            if (this.i) {
                return;
            }
        }
        throw new a("Timed out waiting for WebView to finish loading.", 2);
    }

    private boolean d(long j) {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.f.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            throw new a("Ad request cancelled.", -1);
        }
    }

    @Override // defpackage.acq
    public void a() {
        synchronized (this.f) {
            adn.a("AdRendererBackgroundTask started.");
            fi fiVar = this.g.a;
            int i = this.g.e;
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (this.h.h) {
                    a(fiVar, elapsedRealtime);
                } else if (this.h.p) {
                    a(elapsedRealtime);
                } else {
                    b(elapsedRealtime);
                }
            } catch (a e) {
                i = e.a();
                if (i == 3 || i == -1) {
                    adn.c(e.getMessage());
                } else {
                    adn.e(e.getMessage());
                }
                if (this.h == null) {
                    this.h = new fk(i);
                } else {
                    this.h = new fk(i, this.h.k);
                }
                adm.a.post(new aaw(this));
            }
            adm.a.post(new aax(this, new acg(fiVar.c, this.c, this.h.d, i, this.h.f, this.h.j, this.h.l, this.h.k, fiVar.i, this.h.h, this.l != null ? this.l.b : null, this.l != null ? this.l.c : null, this.l != null ? this.l.d : co.class.getName(), this.k, this.l != null ? this.l.e : null, this.h.i, this.g.d, this.h.g, this.g.f, this.h.n, this.h.o, this.g.h, null)));
        }
    }

    protected void a(long j) {
        int i;
        int i2;
        ay e = this.c.e();
        if (e.e) {
            i = this.e.getResources().getDisplayMetrics().widthPixels;
            i2 = this.e.getResources().getDisplayMetrics().heightPixels;
        } else {
            i = e.g;
            i2 = e.d;
        }
        aas aasVar = new aas(this, this.c, i, i2);
        adm.a.post(new aaz(this, aasVar));
        c(j);
        if (aasVar.c()) {
            adn.a("Ad-Network indicated no fill with passback URL.");
            throw new a("AdNetwork sent passback url", 3);
        }
        if (!aasVar.d()) {
            throw new a("AdNetwork timed out", 2);
        }
    }

    @Override // defpackage.ads
    public void a(gv gvVar) {
        synchronized (this.f) {
            adn.a("WebView finished loading.");
            this.i = true;
            this.f.notify();
        }
    }

    @Override // defpackage.acq
    public void b() {
        synchronized (this.d) {
            this.c.stopLoading();
            acx.a(this.c);
            if (this.j != null) {
                this.j.a();
            }
        }
    }
}
