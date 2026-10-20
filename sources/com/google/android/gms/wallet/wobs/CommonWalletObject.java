package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import defpackage.afu;
import defpackage.auv;
import defpackage.auw;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CommonWalletObject implements SafeParcelable {
    public static final Parcelable.Creator<CommonWalletObject> CREATOR = new auw();
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public int i;
    public ArrayList<p> j;
    public l k;
    public ArrayList<LatLng> l;
    public String m;
    public String n;
    public ArrayList<d> o;
    public boolean p;
    public ArrayList<n> q;
    public ArrayList<j> r;
    public ArrayList<n> s;
    private final int t;

    CommonWalletObject() {
        this.t = 1;
        this.j = afu.a();
        this.l = afu.a();
        this.o = afu.a();
        this.q = afu.a();
        this.r = afu.a();
        this.s = afu.a();
    }

    public CommonWalletObject(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, ArrayList<p> arrayList, l lVar, ArrayList<LatLng> arrayList2, String str9, String str10, ArrayList<d> arrayList3, boolean z, ArrayList<n> arrayList4, ArrayList<j> arrayList5, ArrayList<n> arrayList6) {
        this.t = i;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = i2;
        this.j = arrayList;
        this.k = lVar;
        this.l = arrayList2;
        this.m = str9;
        this.n = str10;
        this.o = arrayList3;
        this.p = z;
        this.q = arrayList4;
        this.r = arrayList5;
        this.s = arrayList6;
    }

    public static auv a() {
        CommonWalletObject commonWalletObject = new CommonWalletObject();
        commonWalletObject.getClass();
        return new auv(commonWalletObject);
    }

    public int b() {
        return this.t;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        auw.a(this, parcel, i);
    }
}
