package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.ol;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class DataPoint implements SafeParcelable {
    public static final Parcelable.Creator<DataPoint> CREATOR = new ol();
    private final int a;
    private final DataSource b;
    private long c;
    private long d;
    private final Value[] e;
    private DataSource f;
    private long g;
    private long h;

    public DataPoint(int i, DataSource dataSource, long j, long j2, Value[] valueArr, DataSource dataSource2, long j3, long j4) {
        this.a = i;
        this.b = dataSource;
        this.f = dataSource2;
        this.c = j;
        this.d = j2;
        this.e = valueArr;
        this.g = j3;
        this.h = j4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataPoint(List<DataSource> list, RawDataPoint rawDataPoint) {
        this(4, a(list, rawDataPoint.e), rawDataPoint.b, rawDataPoint.c, rawDataPoint.d, a(list, rawDataPoint.f), rawDataPoint.g, rawDataPoint.h);
    }

    private static DataSource a(List<DataSource> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    private boolean a(DataPoint dataPoint) {
        return jy.a(this.b, dataPoint.b) && this.c == dataPoint.c && this.d == dataPoint.d && Arrays.equals(this.e, dataPoint.e) && jy.a(this.f, dataPoint.f);
    }

    public Value[] a() {
        return this.e;
    }

    public DataSource b() {
        return this.b;
    }

    public DataSource c() {
        return this.f;
    }

    public long d() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof DataPoint) && a((DataPoint) obj));
    }

    public long f() {
        return this.h;
    }

    public long g() {
        return this.d;
    }

    public int h() {
        return this.a;
    }

    public int hashCode() {
        return jy.a(this.b, Long.valueOf(this.c), Long.valueOf(this.d));
    }

    public String toString() {
        return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", Arrays.toString(this.e), Long.valueOf(this.d), Long.valueOf(this.c), Long.valueOf(this.g), Long.valueOf(this.h), this.b, this.f);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ol.a(this, parcel, i);
    }
}
