package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aog;
import defpackage.aqb;
import defpackage.aqc;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class PolylineOptions implements SafeParcelable {
    public static final aqb CREATOR = new aqb();
    private final int a;
    private final List<LatLng> b;
    private float c;
    private int d;
    private float e;
    private boolean f;
    private boolean g;

    public PolylineOptions() {
        this.c = 10.0f;
        this.d = ViewCompat.MEASURED_STATE_MASK;
        this.e = 0.0f;
        this.f = true;
        this.g = false;
        this.a = 1;
        this.b = new ArrayList();
    }

    public PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2) {
        this.c = 10.0f;
        this.d = ViewCompat.MEASURED_STATE_MASK;
        this.e = 0.0f;
        this.f = true;
        this.g = false;
        this.a = i;
        this.b = list;
        this.c = f;
        this.d = i2;
        this.e = f2;
        this.f = z;
        this.g = z2;
    }

    public int a() {
        return this.a;
    }

    public List<LatLng> b() {
        return this.b;
    }

    public float c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            aqc.a(this, parcel, i);
        } else {
            aqb.a(this, parcel, i);
        }
    }
}
