package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avz;

/* loaded from: classes.dex */
public class p implements SafeParcelable {
    public static final Parcelable.Creator<p> CREATOR = new avz();
    public final int a;
    public final int b;
    public final int c;

    public p(int i, int i2, int i3) {
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
        avz.a(this, parcel, i);
    }
}
