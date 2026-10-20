package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import defpackage.jy;
import defpackage.or;
import defpackage.os;
import defpackage.pt;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class n implements SafeParcelable {
    public static final Parcelable.Creator<n> CREATOR = new pt();
    public int a;
    public int b;
    private final int c;
    private final DataSource d;
    private final DataType e;
    private or f;
    private final long g;
    private final long h;
    private final PendingIntent i;
    private final long j;
    private final int k;
    private final List<LocationRequest> l;
    private final long m;
    private final List n;

    public n(int i, DataSource dataSource, DataType dataType, IBinder iBinder, int i2, int i3, long j, long j2, PendingIntent pendingIntent, long j3, int i4, List<LocationRequest> list, long j4) {
        this.c = i;
        this.d = dataSource;
        this.e = dataType;
        this.f = iBinder == null ? null : os.a(iBinder);
        this.g = j == 0 ? i2 : j;
        this.j = j3;
        this.h = j2 == 0 ? i3 : j2;
        this.l = list;
        this.i = pendingIntent;
        this.k = i4;
        this.n = Collections.emptyList();
        this.m = j4;
    }

    private boolean a(n nVar) {
        return jy.a(this.d, nVar.d) && jy.a(this.e, nVar.e) && this.g == nVar.g && this.j == nVar.j && this.h == nVar.h && this.k == nVar.k && jy.a(this.l, nVar.l);
    }

    public DataSource a() {
        return this.d;
    }

    public DataType b() {
        return this.e;
    }

    public PendingIntent c() {
        return this.i;
    }

    public long d() {
        return this.j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof n) && a((n) obj));
    }

    public long f() {
        return this.h;
    }

    public List<LocationRequest> g() {
        return this.l;
    }

    public int h() {
        return this.k;
    }

    public int hashCode() {
        return jy.a(this.d, this.e, this.f, Long.valueOf(this.g), Long.valueOf(this.j), Long.valueOf(this.h), Integer.valueOf(this.k), this.l);
    }

    public long i() {
        return this.m;
    }

    public int j() {
        return this.c;
    }

    public IBinder k() {
        if (this.f == null) {
            return null;
        }
        return this.f.asBinder();
    }

    public String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", this.e, this.d, Long.valueOf(this.g), Long.valueOf(this.j), Long.valueOf(this.h));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pt.a(this, parcel, i);
    }
}
