package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aog;
import defpackage.aoo;
import defpackage.aop;

/* loaded from: classes.dex */
public final class CircleOptions implements SafeParcelable {
    public static final aoo CREATOR = new aoo();
    private final int a;
    private LatLng b;
    private double c;
    private float d;
    private int e;
    private int f;
    private float g;
    private boolean h;

    public CircleOptions() {
        this.b = null;
        this.c = 0.0d;
        this.d = 10.0f;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = 0;
        this.g = 0.0f;
        this.h = true;
        this.a = 1;
    }

    public CircleOptions(int i, LatLng latLng, double d, float f, int i2, int i3, float f2, boolean z) {
        this.b = null;
        this.c = 0.0d;
        this.d = 10.0f;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = 0;
        this.g = 0.0f;
        this.h = true;
        this.a = i;
        this.b = latLng;
        this.c = d;
        this.d = f;
        this.e = i2;
        this.f = i3;
        this.g = f2;
        this.h = z;
    }

    public int a() {
        return this.a;
    }

    public LatLng b() {
        return this.b;
    }

    public double c() {
        return this.c;
    }

    public float d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public float g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            aop.a(this, parcel, i);
        } else {
            aoo.a(this, parcel, i);
        }
    }
}
