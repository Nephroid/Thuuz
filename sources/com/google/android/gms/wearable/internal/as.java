package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avu;

/* loaded from: classes.dex */
public class as implements SafeParcelable {
    public static final Parcelable.Creator<as> CREATOR = new avu();
    public final int a;
    public final int b;
    public final int c;

    public as(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avu.a(this, parcel, i);
    }
}
