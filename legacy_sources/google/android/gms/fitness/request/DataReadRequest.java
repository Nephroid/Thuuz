package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import defpackage.jy;
import defpackage.pl;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DataReadRequest implements SafeParcelable {
    public static final Parcelable.Creator<DataReadRequest> CREATOR = new pl();
    private final int a;
    private final List<DataType> b;
    private final List<DataSource> c;
    private final long d;
    private final long e;
    private final List<DataType> f;
    private final List<DataSource> g;
    private final int h;
    private final long i;
    private final DataSource j;
    private final int k;
    private final boolean l;
    private final boolean m;
    private final boolean n;

    public DataReadRequest(int i, List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i2, long j3, DataSource dataSource, int i3, boolean z, boolean z2, boolean z3) {
        this.a = i;
        this.b = Collections.unmodifiableList(list);
        this.c = Collections.unmodifiableList(list2);
        this.d = j;
        this.e = j2;
        this.f = Collections.unmodifiableList(list3);
        this.g = Collections.unmodifiableList(list4);
        this.h = i2;
        this.i = j3;
        this.j = dataSource;
        this.k = i3;
        this.l = z;
        this.m = z2;
        this.n = z3;
    }

    private boolean a(DataReadRequest dataReadRequest) {
        return this.b.equals(dataReadRequest.b) && this.c.equals(dataReadRequest.c) && this.d == dataReadRequest.d && this.e == dataReadRequest.e && this.h == dataReadRequest.h && this.g.equals(dataReadRequest.g) && this.f.equals(dataReadRequest.f) && jy.a(this.j, dataReadRequest.j) && this.i == dataReadRequest.i && this.n == dataReadRequest.n;
    }

    public List<DataType> a() {
        return this.b;
    }

    public List<DataSource> b() {
        return this.c;
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<DataType> e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof DataReadRequest) && a((DataReadRequest) obj));
    }

    public List<DataSource> f() {
        return this.g;
    }

    public int g() {
        return this.h;
    }

    public long h() {
        return this.i;
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.h), Long.valueOf(this.d), Long.valueOf(this.e));
    }

    public DataSource i() {
        return this.j;
    }

    public int j() {
        return this.k;
    }

    public boolean k() {
        return this.l;
    }

    public boolean l() {
        return this.n;
    }

    public boolean m() {
        return this.m;
    }

    public int n() {
        return this.a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReadDataRequest{");
        if (!this.b.isEmpty()) {
            Iterator<DataType> it = this.b.iterator();
            while (it.hasNext()) {
                sb.append(it.next().c()).append(" ");
            }
        }
        if (!this.c.isEmpty()) {
            Iterator<DataSource> it2 = this.c.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next().i()).append(" ");
            }
        }
        if (this.h != 0) {
            sb.append("bucket by ").append(Bucket.a(this.h));
            if (this.i > 0) {
                sb.append(" >").append(this.i).append("ms");
            }
            sb.append(": ");
        }
        if (!this.f.isEmpty()) {
            Iterator<DataType> it3 = this.f.iterator();
            while (it3.hasNext()) {
                sb.append(it3.next().c()).append(" ");
            }
        }
        if (!this.g.isEmpty()) {
            Iterator<DataSource> it4 = this.g.iterator();
            while (it4.hasNext()) {
                sb.append(it4.next().i()).append(" ");
            }
        }
        sb.append(String.format("(%tF %tT - %tF %tT)", Long.valueOf(this.d), Long.valueOf(this.d), Long.valueOf(this.e), Long.valueOf(this.e)));
        if (this.j != null) {
            sb.append("activities: ").append(this.j.i());
        }
        if (this.n) {
            sb.append(" +server");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pl.a(this, parcel, i);
    }
}
