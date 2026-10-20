package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avl;

/* loaded from: classes.dex */
public class ab implements SafeParcelable {
    public static final Parcelable.Creator<ab> CREATOR = new avl();
    public final int a;
    public final int b;
    public final ak c;

    public ab(int i, int i2, ak akVar) {
        this.a = i;
        this.b = i2;
        this.c = akVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avl.a(this, parcel, i);
    }
}
