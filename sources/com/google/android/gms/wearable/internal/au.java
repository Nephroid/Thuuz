package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avv;
import java.util.List;

/* loaded from: classes.dex */
public class au implements SafeParcelable {
    public static final Parcelable.Creator<au> CREATOR = new avv();
    public final int a;
    public final int b;
    public final long c;
    public final List<am> d;

    public au(int i, int i2, long j, List<am> list) {
        this.a = i;
        this.b = i2;
        this.c = j;
        this.d = list;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avv.a(this, parcel, i);
    }
}
