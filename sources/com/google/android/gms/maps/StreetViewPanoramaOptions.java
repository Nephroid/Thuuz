package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import defpackage.akx;
import defpackage.amc;

/* loaded from: classes.dex */
public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final akx CREATOR = new akx();
    private final int a;
    private StreetViewPanoramaCamera b;
    private String c;
    private LatLng d;
    private Integer e;
    private Boolean f;
    private Boolean g;
    private Boolean h;
    private Boolean i;
    private Boolean j;

    public StreetViewPanoramaOptions() {
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.a = 1;
    }

    public StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.a = i;
        this.b = streetViewPanoramaCamera;
        this.d = latLng;
        this.e = num;
        this.c = str;
        this.f = amc.a(b);
        this.g = amc.a(b2);
        this.h = amc.a(b3);
        this.i = amc.a(b4);
        this.j = amc.a(b5);
    }

    public int a() {
        return this.a;
    }

    public byte b() {
        return amc.a(this.f);
    }

    public byte c() {
        return amc.a(this.g);
    }

    public byte d() {
        return amc.a(this.h);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte e() {
        return amc.a(this.i);
    }

    public byte f() {
        return amc.a(this.j);
    }

    public StreetViewPanoramaCamera g() {
        return this.b;
    }

    public LatLng h() {
        return this.d;
    }

    public Integer i() {
        return this.e;
    }

    public String j() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        akx.a(this, parcel, i);
    }
}
