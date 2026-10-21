package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import defpackage.ahc;

/* loaded from: classes.dex */
public class lh implements SafeParcelable {
    public static final Parcelable.Creator<lh> CREATOR = new ahc();
    private final int a;
    private final DataSource b;

    public lh(int i, DataSource dataSource) {
        this.a = i;
        this.b = dataSource;
    }

    public DataSource a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ahc.a(this, parcel, i);
    }
}
