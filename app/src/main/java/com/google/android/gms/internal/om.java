package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aiz;

/* loaded from: classes.dex */
public final class om implements SafeParcelable {
    public static final Parcelable.Creator<om> CREATOR = new aiz();
    public int[] a;
    private final int b;

    om() {
        this(1, null);
    }

    public om(int i, int[] iArr) {
        this.b = i;
        this.a = iArr;
    }

    public int a() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aiz.a(this, parcel, i);
    }
}
