package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avk;

/* loaded from: classes.dex */
public class z implements SafeParcelable {
    public static final Parcelable.Creator<z> CREATOR = new avk();
    public final int a;
    public final int b;
    public final ParcelFileDescriptor c;

    public z(int i, int i2, ParcelFileDescriptor parcelFileDescriptor) {
        this.a = i;
        this.b = i2;
        this.c = parcelFileDescriptor;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avk.a(this, parcel, i | 1);
    }
}
