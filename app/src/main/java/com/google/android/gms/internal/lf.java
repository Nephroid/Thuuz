package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import defpackage.ahb;
import defpackage.jy;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class lf implements SafeParcelable {
    public static final Parcelable.Creator<lf> CREATOR = new ahb();
    private final int a;
    private final List<DataType> b;

    public lf(int i, List<DataType> list) {
        this.a = i;
        this.b = list;
    }

    public List<DataType> a() {
        return Collections.unmodifiableList(this.b);
    }

    public int b() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return jy.a(this).a("dataTypes", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ahb.a(this, parcel, i);
    }
}
