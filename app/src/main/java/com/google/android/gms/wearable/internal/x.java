package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.awd;

/* loaded from: classes.dex */
public class x implements SafeParcelable {
    public static final Parcelable.Creator<x> CREATOR = new awd();
    public final int a;
    public final int b;
    public final m c;

    public x(int i, int i2, m mVar) {
        this.a = i;
        this.b = i2;
        this.c = mVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        awd.a(this, parcel, i);
    }
}
