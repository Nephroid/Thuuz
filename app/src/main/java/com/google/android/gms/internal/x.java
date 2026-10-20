package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.akb;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class x implements SafeParcelable {
    public static final akb CREATOR = new akb();
    public final int a;
    public final boolean b;
    public final boolean c;

    public x(int i, boolean z, boolean z2) {
        this.a = i;
        this.b = z;
        this.c = z2;
    }

    public x(boolean z, boolean z2) {
        this.a = 1;
        this.b = z;
        this.c = z2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        akb.a(this, parcel, i);
    }
}
