package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import defpackage.jy;
import defpackage.pk;

/* loaded from: classes.dex */
public class DataInsertRequest implements SafeParcelable {
    public static final Parcelable.Creator<DataInsertRequest> CREATOR = new pk();
    private final int a;
    private final DataSet b;

    public DataInsertRequest(int i, DataSet dataSet) {
        this.a = i;
        this.b = dataSet;
    }

    private boolean a(DataInsertRequest dataInsertRequest) {
        return jy.a(this.b, dataInsertRequest.b);
    }

    public DataSet a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof DataInsertRequest) && a((DataInsertRequest) obj));
    }

    public int hashCode() {
        return jy.a(this.b);
    }

    public String toString() {
        return jy.a(this).a("dataSet", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pk.a(this, parcel, i);
    }
}
