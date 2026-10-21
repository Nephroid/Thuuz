package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avr;

/* loaded from: classes.dex */
public class am implements SafeParcelable {
    public static final Parcelable.Creator<am> CREATOR = new avr();
    public final int a;
    public final String b;
    public final String c;
    public final long d;

    public am(int i, String str, String str2, long j) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avr.a(this, parcel, i);
    }
}
