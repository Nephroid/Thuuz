package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aog;
import defpackage.apz;
import defpackage.aqa;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class PolygonOptions implements SafeParcelable {
    public static final apz CREATOR = new apz();
    private final int a;
    private final List<LatLng> b;
    private final List<List<LatLng>> c;
    private float d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private boolean i;

    public PolygonOptions() {
        this.d = 10.0f;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = 0;
        this.g = 0.0f;
        this.h = true;
        this.i = false;
        this.a = 1;
        this.b = new ArrayList();
        this.c = new ArrayList();
    }

    public PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2) {
        this.d = 10.0f;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = 0;
        this.g = 0.0f;
        this.h = true;
        this.i = false;
        this.a = i;
        this.b = list;
        this.c = list2;
        this.d = f;
        this.e = i2;
        this.f = i3;
        this.g = f2;
        this.h = z;
        this.i = z2;
    }

    public int a() {
        return this.a;
    }

    public List b() {
        return this.c;
    }

    public List<LatLng> c() {
        return this.b;
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

    public boolean i() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            aqa.a(this, parcel, i);
        } else {
            apz.a(this, parcel, i);
        }
    }
}
