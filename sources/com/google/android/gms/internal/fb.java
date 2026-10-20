package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import defpackage.aap;
import defpackage.abb;
import defpackage.abg;
import defpackage.acl;
import defpackage.acq;
import defpackage.adn;
import defpackage.agc;
import defpackage.sy;
import defpackage.vz;
import defpackage.zy;
import org.json.JSONException;

@zy
/* loaded from: classes.dex */
public class fb extends acq implements abb {
    private final aap a;
    private final Context c;
    private final abg e;
    private final agc f;
    private acq g;
    private fk h;
    private vz i;
    private final Object b = new Object();
    private final Object d = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    @zy
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

    public fb(Context context, abg abgVar, agc agcVar, aap aapVar) {
        this.a = aapVar;
        this.c = context;
        this.e = abgVar;
        this.f = agcVar;
    }

    private ay a(fi fiVar) {
        if (this.h.m == null) {
            throw new a("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.h.m.split("x");
        if (split.length != 2) {
            throw new a("Could not parse the ad size from the ad response: " + this.h.m, 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (ay ayVar : fiVar.d.h) {
                float f = this.c.getResources().getDisplayMetrics().density;
                int i = ayVar.f == -1 ? (int) (ayVar.g / f) : ayVar.f;
                int i2 = ayVar.c == -2 ? (int) (ayVar.d / f) : ayVar.c;
                if (parseInt == i && parseInt2 == i2) {
                    return new ay(ayVar, fiVar.d.h);
                }
            }
            throw new a("The ad size from the ad response was not one of the requested sizes: " + this.h.m, 0);
        } catch (NumberFormatException e) {
            throw new a("Could not parse the ad size from the ad response: " + this.h.m, 0);
        }
    }

    private void a(long j) {
        while (b(j)) {
            if (this.h != null) {
                synchronized (this.b) {
                    this.g = null;
                }
                if (this.h.e != -2 && this.h.e != -3) {
                    throw new a("There was a problem getting an ad response. ErrorCode: " + this.h.e, this.h.e);
                }
                return;
            }
        }
        throw new a("Timed out waiting for ad response.", 2);
    }

    private void a(boolean z) {
        acl.a().a(z);
        sy a2 = acl.a().a(this.c);
        if (a2 == null || a2.isAlive()) {
            return;
        }
        adn.a("start fetching content...");
        a2.a();
    }

    private boolean b(long j) {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.d.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            throw new a("Ad request cancelled.", -1);
        }
    }

    private void c() {
        if (this.h.e == -3) {
            return;
        }
        if (TextUtils.isEmpty(this.h.c)) {
            throw new a("No fill from ad server.", 3);
        }
        acl.a(this.c, this.h.u);
        if (this.h.h) {
            try {
                this.i = new vz(this.h.c);
            } catch (JSONException e) {
                throw new a("Could not parse mediation config: " + this.h.c, 0);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // defpackage.acq
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a() {
        /*
            Method dump skipped, instructions count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fb.a():void");
    }

    @Override // defpackage.abb
    public void a(fk fkVar) {
        synchronized (this.d) {
            adn.a("Received ad response.");
            this.h = fkVar;
            this.d.notify();
        }
    }

    @Override // defpackage.acq
    public void b() {
        synchronized (this.b) {
            if (this.g != null) {
                this.g.f();
            }
        }
    }
}
