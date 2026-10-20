package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aev;
import defpackage.aew;
import defpackage.jy;

/* loaded from: classes.dex */
public class il implements SafeParcelable {
    public static final Parcelable.Creator<il> CREATOR = new aew();
    private final int a;
    private double b;
    private boolean c;
    private int d;
    private ApplicationMetadata e;
    private int f;

    public il() {
        this(3, Double.NaN, false, -1, null, -1);
    }

    public il(int i, double d, boolean z, int i2, ApplicationMetadata applicationMetadata, int i3) {
        this.a = i;
        this.b = d;
        this.c = z;
        this.d = i2;
        this.e = applicationMetadata;
        this.f = i3;
    }

    public int a() {
        return this.a;
    }

    public double b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof il)) {
            return false;
        }
        il ilVar = (il) obj;
        return this.b == ilVar.b && this.c == ilVar.c && this.d == ilVar.d && aev.a(this.e, ilVar.e) && this.f == ilVar.f;
    }

    public ApplicationMetadata f() {
        return this.e;
    }

    public int hashCode() {
        return jy.a(Double.valueOf(this.b), Boolean.valueOf(this.c), Integer.valueOf(this.d), this.e, Integer.valueOf(this.f));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aew.a(this, parcel, i);
    }
}
