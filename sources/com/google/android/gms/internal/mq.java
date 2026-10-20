package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import defpackage.aid;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class mq implements SafeParcelable {
    public static final Parcelable.Creator<mq> CREATOR = new aid();
    public final int a;
    private final String b;
    private final LatLng c;
    private final String d;
    private final List<mo> e;
    private final String f;
    private final String g;

    public mq(int i, String str, LatLng latLng, String str2, List<mo> list, String str3, String str4) {
        this.a = i;
        this.b = str;
        this.c = latLng;
        this.d = str2;
        this.e = new ArrayList(list);
        this.f = str3;
        this.g = str4;
    }

    public String a() {
        return this.b;
    }

    public LatLng b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public List<mo> d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.f;
    }

    public String f() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aid.a(this, parcel, i);
    }
}
