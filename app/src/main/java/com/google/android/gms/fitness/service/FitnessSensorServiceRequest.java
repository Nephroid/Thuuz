package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import defpackage.jy;
import defpackage.or;
import defpackage.os;
import defpackage.qh;

/* loaded from: classes.dex */
public class FitnessSensorServiceRequest implements SafeParcelable {
    public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new qh();
    private final int a;
    private final DataSource b;
    private final or c;
    private final long d;
    private final long e;

    public FitnessSensorServiceRequest(int i, DataSource dataSource, IBinder iBinder, long j, long j2) {
        this.a = i;
        this.b = dataSource;
        this.c = os.a(iBinder);
        this.d = j;
        this.e = j2;
    }

    private boolean a(FitnessSensorServiceRequest fitnessSensorServiceRequest) {
        return jy.a(this.b, fitnessSensorServiceRequest.b) && this.d == fitnessSensorServiceRequest.d && this.e == fitnessSensorServiceRequest.e;
    }

    public DataSource a() {
        return this.b;
    }

    public long b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }

    public int d() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IBinder e() {
        return this.c.asBinder();
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof FitnessSensorServiceRequest) && a((FitnessSensorServiceRequest) obj));
    }

    public int hashCode() {
        return jy.a(this.b, Long.valueOf(this.d), Long.valueOf(this.e));
    }

    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qh.a(this, parcel, i);
    }
}
