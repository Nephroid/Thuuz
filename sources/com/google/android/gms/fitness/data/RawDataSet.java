package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.ow;
import defpackage.pa;
import java.util.List;

/* loaded from: classes.dex */
public final class RawDataSet implements SafeParcelable {
    public static final Parcelable.Creator<RawDataSet> CREATOR = new ow();
    public final int a;
    public final int b;
    public final int c;
    public final List<RawDataPoint> d;
    public final boolean e;

    public RawDataSet(int i, int i2, int i3, List<RawDataPoint> list, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = list;
        this.e = z;
    }

    public RawDataSet(DataSet dataSet, List<DataSource> list, List<DataType> list2) {
        this.a = 2;
        this.d = dataSet.a(list);
        this.e = dataSet.c();
        this.b = pa.a(dataSet.a(), list);
        this.c = pa.a(dataSet.b(), list2);
    }

    private boolean a(RawDataSet rawDataSet) {
        return this.b == rawDataSet.b && this.c == rawDataSet.c && this.e == rawDataSet.e && jy.a(this.d, rawDataSet.d);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof RawDataSet) && a((RawDataSet) obj));
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.b), Integer.valueOf(this.c));
    }

    public String toString() {
        return String.format("RawDataSet{%s@[%s, %s]}", Integer.valueOf(this.b), Integer.valueOf(this.c), this.d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ow.a(this, parcel, i);
    }
}
