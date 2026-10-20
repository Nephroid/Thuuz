package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.j;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;
import defpackage.afu;
import defpackage.auo;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class LoyaltyWalletObject implements SafeParcelable {
    public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = new auo();
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public int k;
    public ArrayList<p> l;
    public l m;
    public ArrayList<LatLng> n;
    public String o;
    public String p;
    public ArrayList<com.google.android.gms.wallet.wobs.d> q;
    public boolean r;
    public ArrayList<n> s;
    public ArrayList<j> t;
    public ArrayList<n> u;
    public f v;
    private final int w;

    LoyaltyWalletObject() {
        this.w = 4;
        this.l = afu.a();
        this.n = afu.a();
        this.q = afu.a();
        this.s = afu.a();
        this.t = afu.a();
        this.u = afu.a();
    }

    public LoyaltyWalletObject(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i2, ArrayList<p> arrayList, l lVar, ArrayList<LatLng> arrayList2, String str11, String str12, ArrayList<com.google.android.gms.wallet.wobs.d> arrayList3, boolean z, ArrayList<n> arrayList4, ArrayList<j> arrayList5, ArrayList<n> arrayList6, f fVar) {
        this.w = i;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = str9;
        this.j = str10;
        this.k = i2;
        this.l = arrayList;
        this.m = lVar;
        this.n = arrayList2;
        this.o = str11;
        this.p = str12;
        this.q = arrayList3;
        this.r = z;
        this.s = arrayList4;
        this.t = arrayList5;
        this.u = arrayList6;
        this.v = fVar;
    }

    public int a() {
        return this.w;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        auo.a(this, parcel, i);
    }
}
