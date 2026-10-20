package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.om;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class DataSet implements SafeParcelable {
    public static final Parcelable.Creator<DataSet> CREATOR = new om();
    private final int a;
    private final DataSource b;
    private final DataType c;
    private final List<DataPoint> d;
    private final List<DataSource> e;
    private boolean f;

    public DataSet(int i, DataSource dataSource, DataType dataType, List<RawDataPoint> list, List<DataSource> list2, boolean z) {
        this.f = false;
        this.a = i;
        this.b = dataSource;
        this.c = dataType;
        this.f = z;
        this.d = new ArrayList(list.size());
        this.e = i < 2 ? Collections.singletonList(dataSource) : list2;
        Iterator<RawDataPoint> it = list.iterator();
        while (it.hasNext()) {
            this.d.add(new DataPoint(this.e, it.next()));
        }
    }

    public DataSet(RawDataSet rawDataSet, List<DataSource> list, List<DataType> list2) {
        this(3, (DataSource) a(list, rawDataSet.b), (DataType) a(list2, rawDataSet.c), rawDataSet.d, list, rawDataSet.e);
    }

    private static <T> T a(List<T> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    private boolean a(DataSet dataSet) {
        return jy.a(this.c, dataSet.c) && jy.a(this.b, dataSet.b) && jy.a(this.d, dataSet.d) && this.f == dataSet.f;
    }

    public DataSource a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<RawDataPoint> a(List<DataSource> list) {
        ArrayList arrayList = new ArrayList(this.d.size());
        Iterator<DataPoint> it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataPoint(it.next(), list));
        }
        return arrayList;
    }

    public DataType b() {
        return this.c;
    }

    public boolean c() {
        return this.f;
    }

    public int d() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<RawDataPoint> e() {
        return a(this.e);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof DataSet) && a((DataSet) obj));
    }

    public List<DataSource> f() {
        return this.e;
    }

    public int hashCode() {
        return jy.a(this.c, this.b);
    }

    public String toString() {
        List<RawDataPoint> e = e();
        Object[] objArr = new Object[2];
        objArr[0] = this.b.i();
        Object obj = e;
        if (this.d.size() >= 10) {
            obj = String.format("%d data points, first 5: %s", Integer.valueOf(this.d.size()), e.subList(0, 5));
        }
        objArr[1] = obj;
        return String.format("DataSet{%s %s}", objArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        om.a(this, parcel, i);
    }
}
