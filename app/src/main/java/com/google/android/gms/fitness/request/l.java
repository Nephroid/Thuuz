package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import defpackage.ps;

/* loaded from: classes.dex */
public class l implements SafeParcelable {
    public static final Parcelable.Creator<l> CREATOR = new ps();
    private final int a;
    private final DataType b;

    public l(int i, DataType dataType) {
        this.a = i;
        this.b = dataType;
    }

    public DataType a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ps.a(this, parcel, i);
    }
}
