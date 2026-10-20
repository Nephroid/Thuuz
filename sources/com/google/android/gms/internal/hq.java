package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aer;

/* loaded from: classes.dex */
public class hq implements SafeParcelable {
    public static final aer CREATOR = new aer();
    public final int a;
    public final String b;
    public final String c;
    public final boolean d;
    public final int e;
    public final boolean f;
    public final String g;
    public final hk[] h;
    public final int[] i;
    public final String j;

    public hq(int i, String str, String str2, boolean z, int i2, boolean z2, String str3, hk[] hkVarArr, int[] iArr, String str4) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = z;
        this.e = i2;
        this.f = z2;
        this.g = str3;
        this.h = hkVarArr;
        this.i = iArr;
        this.j = str4;
    }

    public hq(String str, String str2, boolean z, int i, boolean z2, String str3, hk[] hkVarArr, int[] iArr, String str4) {
        this(2, str, str2, z, i, z2, str3, hkVarArr, iArr, str4);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aer aerVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof hq)) {
            return false;
        }
        hq hqVar = (hq) obj;
        return this.b.equals(hqVar.b) && this.c.equals(hqVar.c) && this.d == hqVar.d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aer aerVar = CREATOR;
        aer.a(this, parcel, i);
    }
}
