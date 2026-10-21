package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.c;
import defpackage.awb;

/* loaded from: classes.dex */
public class t implements SafeParcelable {
    public static final Parcelable.Creator<t> CREATOR = new awb();
    public final int a;
    public final int b;
    public final c[] c;

    public t(int i, int i2, c[] cVarArr) {
        this.a = i;
        this.b = i2;
        this.c = cVarArr;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        awb.a(this, parcel, i);
    }
}
