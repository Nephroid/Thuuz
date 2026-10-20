package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.awc;
import java.util.List;

/* loaded from: classes.dex */
public class v implements SafeParcelable {
    public static final Parcelable.Creator<v> CREATOR = new awc();
    public final int a;
    public final int b;
    public final List<ak> c;

    public v(int i, int i2, List<ak> list) {
        this.a = i;
        this.b = i2;
        this.c = list;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        awc.a(this, parcel, i);
    }
}
