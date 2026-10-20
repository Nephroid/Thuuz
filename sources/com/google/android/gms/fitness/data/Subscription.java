package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.oz;

/* loaded from: classes.dex */
public class Subscription implements SafeParcelable {
    public static final Parcelable.Creator<Subscription> CREATOR = new oz();
    private final int a;
    private final DataSource b;
    private final DataType c;
    private final long d;
    private final int e;

    public Subscription(int i, DataSource dataSource, DataType dataType, long j, int i2) {
        this.a = i;
        this.b = dataSource;
        this.c = dataType;
        this.d = j;
        this.e = i2;
    }

    private boolean a(Subscription subscription) {
        return jy.a(this.b, subscription.b) && jy.a(this.c, subscription.c) && this.d == subscription.d && this.e == subscription.e;
    }

    public DataSource a() {
        return this.b;
    }

    public DataType b() {
        return this.c;
    }

    public int c() {
        return this.e;
    }

    public long d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Subscription) && a((Subscription) obj));
    }

    public int hashCode() {
        return jy.a(this.b, this.b, Long.valueOf(this.d), Integer.valueOf(this.e));
    }

    public String toString() {
        return jy.a(this).a("dataSource", this.b).a("dataType", this.c).a("samplingIntervalMicros", Long.valueOf(this.d)).a("accuracyMode", Integer.valueOf(this.e)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        oz.a(this, parcel, i);
    }
}
