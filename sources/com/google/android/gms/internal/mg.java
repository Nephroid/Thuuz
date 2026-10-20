package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ahy;
import defpackage.jy;

/* loaded from: classes.dex */
public final class mg implements SafeParcelable {
    public static final ahy CREATOR = new ahy();
    private final int a;
    private final int b;
    private final int c;
    private final mi d;

    public mg(int i, int i2, int i3, mi miVar) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = miVar;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public mi d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        ahy ahyVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mg)) {
            return false;
        }
        mg mgVar = (mg) obj;
        return this.b == mgVar.b && this.c == mgVar.c && this.d.equals(mgVar.d);
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.b), Integer.valueOf(this.c));
    }

    public String toString() {
        return jy.a(this).a("transitionTypes", Integer.valueOf(this.b)).a("loiteringTimeMillis", Integer.valueOf(this.c)).a("placeFilter", this.d).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ahy ahyVar = CREATOR;
        ahy.a(this, parcel, i);
    }
}
