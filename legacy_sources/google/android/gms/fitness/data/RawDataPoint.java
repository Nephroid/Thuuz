package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.ov;
import defpackage.pa;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class RawDataPoint implements SafeParcelable {
    public static final Parcelable.Creator<RawDataPoint> CREATOR = new ov();
    public final int a;
    public final long b;
    public final long c;
    public final Value[] d;
    public final int e;
    public final int f;
    public final long g;
    public final long h;

    public RawDataPoint(int i, long j, long j2, Value[] valueArr, int i2, int i3, long j3, long j4) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.e = i2;
        this.f = i3;
        this.g = j3;
        this.h = j4;
        this.d = valueArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RawDataPoint(DataPoint dataPoint, List<DataSource> list) {
        this.a = 4;
        this.b = dataPoint.d();
        this.c = dataPoint.g();
        this.d = dataPoint.a();
        this.e = pa.a(dataPoint.b(), list);
        this.f = pa.a(dataPoint.c(), list);
        this.g = dataPoint.e();
        this.h = dataPoint.f();
    }

    private boolean a(RawDataPoint rawDataPoint) {
        return this.b == rawDataPoint.b && this.c == rawDataPoint.c && Arrays.equals(this.d, rawDataPoint.d) && this.e == rawDataPoint.e && this.f == rawDataPoint.f && this.g == rawDataPoint.g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof RawDataPoint) && a((RawDataPoint) obj));
    }

    public int hashCode() {
        return jy.a(Long.valueOf(this.b), Long.valueOf(this.c));
    }

    public String toString() {
        return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", Arrays.toString(this.d), Long.valueOf(this.c), Long.valueOf(this.b), Integer.valueOf(this.e), Integer.valueOf(this.f));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ov.a(this, parcel, i);
    }
}
