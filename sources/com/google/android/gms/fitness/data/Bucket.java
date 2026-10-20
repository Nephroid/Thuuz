package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.EnvironmentCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.ok;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class Bucket implements SafeParcelable {
    public static final Parcelable.Creator<Bucket> CREATOR = new ok();
    private final int a;
    private final long b;
    private final long c;
    private final Session d;
    private final int e;
    private final List<DataSet> f;
    private final int g;
    private boolean h;

    public Bucket(int i, long j, long j2, Session session, int i2, List<DataSet> list, int i3, boolean z) {
        this.h = false;
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = session;
        this.e = i2;
        this.f = list;
        this.g = i3;
        this.h = z;
    }

    public Bucket(RawBucket rawBucket, List<DataSource> list, List<DataType> list2) {
        this(2, rawBucket.b, rawBucket.c, rawBucket.d, rawBucket.e, a(rawBucket.f, list, list2), rawBucket.g, rawBucket.h);
    }

    public static String a(int i) {
        switch (i) {
            case 0:
                return EnvironmentCompat.MEDIA_UNKNOWN;
            case 1:
                return "time";
            case 2:
                return "session";
            case 3:
                return "type";
            case 4:
                return "segment";
            default:
                return "bug";
        }
    }

    private static List<DataSet> a(List<RawDataSet> list, List<DataSource> list2, List<DataType> list3) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<RawDataSet> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new DataSet(it.next(), list2, list3));
        }
        return arrayList;
    }

    private boolean a(Bucket bucket) {
        return this.b == bucket.b && this.c == bucket.c && this.e == bucket.e && jy.a(this.f, bucket.f) && this.g == bucket.g && this.h == bucket.h;
    }

    public long a() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public Session c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<DataSet> e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Bucket) && a((Bucket) obj));
    }

    public int f() {
        return this.g;
    }

    public boolean g() {
        if (this.h) {
            return true;
        }
        Iterator<DataSet> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next().c()) {
                return true;
            }
        }
        return false;
    }

    public int h() {
        return this.a;
    }

    public int hashCode() {
        return jy.a(Long.valueOf(this.b), Long.valueOf(this.c), Integer.valueOf(this.e), Integer.valueOf(this.g));
    }

    public String toString() {
        return jy.a(this).a("startTime", Long.valueOf(this.b)).a("endTime", Long.valueOf(this.c)).a("activity", Integer.valueOf(this.e)).a("dataSets", this.f).a("bucketType", a(this.g)).a("serverHasMoreData", Boolean.valueOf(this.h)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ok.a(this, parcel, i);
    }
}
