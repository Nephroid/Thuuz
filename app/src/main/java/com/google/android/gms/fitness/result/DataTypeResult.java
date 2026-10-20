package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import defpackage.jy;
import defpackage.qd;

/* loaded from: classes.dex */
public class DataTypeResult implements SafeParcelable {
    public static final Parcelable.Creator<DataTypeResult> CREATOR = new qd();
    private final int a;
    private final Status b;
    private final DataType c;

    public DataTypeResult(int i, Status status, DataType dataType) {
        this.a = i;
        this.b = status;
        this.c = dataType;
    }

    private boolean a(DataTypeResult dataTypeResult) {
        return this.b.equals(dataTypeResult.b) && jy.a(this.c, dataTypeResult.c);
    }

    public Status a() {
        return this.b;
    }

    public DataType b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof DataTypeResult) && a((DataTypeResult) obj));
    }

    public int hashCode() {
        return jy.a(this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a("status", this.b).a("dataType", this.c).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qd.a(this, parcel, i);
    }
}
