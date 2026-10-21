package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.oe;
import defpackage.og;
import defpackage.ya;
import defpackage.ym;
import defpackage.yo;
import defpackage.zd;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class dv implements SafeParcelable {
    public static final ya CREATOR = new ya();
    public final int a;
    public final zd b;
    public final ym c;
    public final yo d;
    public final Context e;

    public dv(int i, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4) {
        this.a = i;
        this.b = (zd) og.a(oe.a(iBinder));
        this.c = (ym) og.a(oe.a(iBinder2));
        this.d = (yo) og.a(oe.a(iBinder3));
        this.e = (Context) og.a(oe.a(iBinder4));
    }

    public dv(yo yoVar, zd zdVar, ym ymVar, Context context) {
        this.a = 1;
        this.d = yoVar;
        this.b = zdVar;
        this.c = ymVar;
        this.e = context;
    }

    public static dv a(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(dv.class.getClassLoader());
            return (dv) bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public static void a(Intent intent, dv dvVar) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", dvVar);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", bundle);
    }

    public IBinder a() {
        return og.a(this.b).asBinder();
    }

    public IBinder b() {
        return og.a(this.c).asBinder();
    }

    public IBinder c() {
        return og.a(this.d).asBinder();
    }

    public IBinder d() {
        return og.a(this.e).asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ya.a(this, parcel, i);
    }
}
