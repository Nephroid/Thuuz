package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import defpackage.jy;
import defpackage.qb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DataReadResult implements SafeParcelable {
    public static final Parcelable.Creator<DataReadResult> CREATOR = new qb();
    private final int a;
    private final List<DataSet> b;
    private final Status c;
    private final List<Bucket> d;
    private int e;
    private final List<DataSource> f;
    private final List<DataType> g;

    public DataReadResult(int i, List<RawDataSet> list, Status status, List<RawBucket> list2, int i2, List<DataSource> list3, List<DataType> list4) {
        this.a = i;
        this.c = status;
        this.e = i2;
        this.f = list3;
        this.g = list4;
        this.b = new ArrayList(list.size());
        Iterator<RawDataSet> it = list.iterator();
        while (it.hasNext()) {
            this.b.add(new DataSet(it.next(), list3, list4));
        }
        this.d = new ArrayList(list2.size());
        Iterator<RawBucket> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.d.add(new Bucket(it2.next(), list3, list4));
        }
    }

    private boolean a(DataReadResult dataReadResult) {
        return this.c.equals(dataReadResult.c) && jy.a(this.b, dataReadResult.b) && jy.a(this.d, dataReadResult.d);
    }

    public int a() {
        return this.e;
    }

    public Status b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    public List<RawBucket> d() {
        ArrayList arrayList = new ArrayList(this.d.size());
        Iterator<Bucket> it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawBucket(it.next(), this.f, this.g));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<RawDataSet> e() {
        ArrayList arrayList = new ArrayList(this.b.size());
        Iterator<DataSet> it = this.b.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataSet(it.next(), this.f, this.g));
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof DataReadResult) && a((DataReadResult) obj));
    }

    public List<DataSource> f() {
        return this.f;
    }

    public List<DataType> g() {
        return this.g;
    }

    public int hashCode() {
        return jy.a(this.c, this.b, this.d);
    }

    public String toString() {
        return jy.a(this).a("status", this.c).a("dataSets", this.b.size() > 5 ? this.b.size() + " data sets" : this.b).a("buckets", this.d.size() > 5 ? this.d.size() + " buckets" : this.d).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qb.a(this, parcel, i);
    }
}
