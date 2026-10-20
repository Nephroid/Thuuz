package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ajq;
import defpackage.oe;
import defpackage.og;
import defpackage.uw;
import defpackage.vh;
import defpackage.xp;
import defpackage.xq;
import defpackage.xt;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class dm implements SafeParcelable {
    public static final xp CREATOR = new xp();
    public final int a;
    public final dj b;
    public final ajq c;
    public final xq d;
    public final gv e;
    public final uw f;
    public final String g;
    public final boolean h;
    public final String i;
    public final xt j;
    public final int k;
    public final int l;
    public final String m;
    public final gt n;
    public final vh o;
    public final String p;
    public final x q;

    public dm(int i, dj djVar, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, gt gtVar, IBinder iBinder6, String str4, x xVar) {
        this.a = i;
        this.b = djVar;
        this.c = (ajq) og.a(oe.a(iBinder));
        this.d = (xq) og.a(oe.a(iBinder2));
        this.e = (gv) og.a(oe.a(iBinder3));
        this.f = (uw) og.a(oe.a(iBinder4));
        this.g = str;
        this.h = z;
        this.i = str2;
        this.j = (xt) og.a(oe.a(iBinder5));
        this.k = i2;
        this.l = i3;
        this.m = str3;
        this.n = gtVar;
        this.o = (vh) og.a(oe.a(iBinder6));
        this.p = str4;
        this.q = xVar;
    }

    public dm(ajq ajqVar, xq xqVar, uw uwVar, xt xtVar, gv gvVar, boolean z, int i, String str, gt gtVar, vh vhVar) {
        this.a = 4;
        this.b = null;
        this.c = ajqVar;
        this.d = xqVar;
        this.e = gvVar;
        this.f = uwVar;
        this.g = null;
        this.h = z;
        this.i = null;
        this.j = xtVar;
        this.k = i;
        this.l = 3;
        this.m = str;
        this.n = gtVar;
        this.o = vhVar;
        this.p = null;
        this.q = null;
    }

    public dm(ajq ajqVar, xq xqVar, uw uwVar, xt xtVar, gv gvVar, boolean z, int i, String str, String str2, gt gtVar, vh vhVar) {
        this.a = 4;
        this.b = null;
        this.c = ajqVar;
        this.d = xqVar;
        this.e = gvVar;
        this.f = uwVar;
        this.g = str2;
        this.h = z;
        this.i = str;
        this.j = xtVar;
        this.k = i;
        this.l = 3;
        this.m = null;
        this.n = gtVar;
        this.o = vhVar;
        this.p = null;
        this.q = null;
    }

    public dm(ajq ajqVar, xq xqVar, xt xtVar, gv gvVar, int i, gt gtVar, String str, x xVar) {
        this.a = 4;
        this.b = null;
        this.c = ajqVar;
        this.d = xqVar;
        this.e = gvVar;
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = null;
        this.j = xtVar;
        this.k = i;
        this.l = 1;
        this.m = null;
        this.n = gtVar;
        this.o = null;
        this.p = str;
        this.q = xVar;
    }

    public dm(ajq ajqVar, xq xqVar, xt xtVar, gv gvVar, boolean z, int i, gt gtVar) {
        this.a = 4;
        this.b = null;
        this.c = ajqVar;
        this.d = xqVar;
        this.e = gvVar;
        this.f = null;
        this.g = null;
        this.h = z;
        this.i = null;
        this.j = xtVar;
        this.k = i;
        this.l = 2;
        this.m = null;
        this.n = gtVar;
        this.o = null;
        this.p = null;
        this.q = null;
    }

    public dm(dj djVar, ajq ajqVar, xq xqVar, xt xtVar, gt gtVar) {
        this.a = 4;
        this.b = djVar;
        this.c = ajqVar;
        this.d = xqVar;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = null;
        this.j = xtVar;
        this.k = -1;
        this.l = 4;
        this.m = null;
        this.n = gtVar;
        this.o = null;
        this.p = null;
        this.q = null;
    }

    public static dm a(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(dm.class.getClassLoader());
            return (dm) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public static void a(Intent intent, dm dmVar) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", dmVar);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public IBinder a() {
        return og.a(this.c).asBinder();
    }

    public IBinder b() {
        return og.a(this.d).asBinder();
    }

    public IBinder c() {
        return og.a(this.e).asBinder();
    }

    public IBinder d() {
        return og.a(this.f).asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IBinder e() {
        return og.a(this.o).asBinder();
    }

    public IBinder f() {
        return og.a(this.j).asBinder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        xp.a(this, parcel, i);
    }
}
