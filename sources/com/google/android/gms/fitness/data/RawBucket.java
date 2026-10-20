package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.ou;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class RawBucket implements SafeParcelable {
    public static final Parcelable.Creator<RawBucket> CREATOR = new ou();
    public final int a;
    public final long b;
    public final long c;
    public final Session d;
    public final int e;
    public final List<RawDataSet> f;
    public final int g;
    public final boolean h;

    public RawBucket(int i, long j, long j2, Session session, int i2, List<RawDataSet> list, int i3, boolean z) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = session;
        this.e = i2;
        this.f = list;
        this.g = i3;
        this.h = z;
    }

    public RawBucket(Bucket bucket, List<DataSource> list, List<DataType> list2) {
        this.a = 2;
        this.b = bucket.a();
        this.c = bucket.b();
        this.d = bucket.c();
        this.e = bucket.d();
        this.g = bucket.f();
        this.h = bucket.g();
        List<DataSet> e = bucket.e();
        this.f = new ArrayList(e.size());
        Iterator<DataSet> it = e.iterator();
        while (it.hasNext()) {
            this.f.add(new RawDataSet(it.next(), list, list2));
        }
    }

    private boolean a(RawBucket rawBucket) {
        return this.b == rawBucket.b && this.c == rawBucket.c && this.e == rawBucket.e && jy.a(this.f, rawBucket.f) && this.g == rawBucket.g && this.h == rawBucket.h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof RawBucket) && a((RawBucket) obj));
    }

    public int hashCode() {
        return jy.a(Long.valueOf(this.b), Long.valueOf(this.c), Integer.valueOf(this.g));
    }

    public String toString() {
        return jy.a(this).a("startTime", Long.valueOf(this.b)).a("endTime", Long.valueOf(this.c)).a("activity", Integer.valueOf(this.e)).a("dataSets", this.f).a("bucketType", Integer.valueOf(this.g)).a("serverHasMoreData", Boolean.valueOf(this.h)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ou.a(this, parcel, i);
    }
}
