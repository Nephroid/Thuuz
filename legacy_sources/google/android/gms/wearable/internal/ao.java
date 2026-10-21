package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avs;

/* loaded from: classes.dex */
public class ao implements SafeParcelable {
    public static final Parcelable.Creator<ao> CREATOR = new avs();
    public final int a;
    public final int b;
    public final m c;

    public ao(int i, int i2, m mVar) {
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
        avs.a(this, parcel, i);
    }
}
