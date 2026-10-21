package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import defpackage.jy;
import defpackage.pj;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class DataDeleteRequest implements SafeParcelable {
    public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new pj();
    private final int a;
    private final long b;
    private final long c;
    private final List<DataSource> d;
    private final List<DataType> e;
    private final List<Session> f;
    private final boolean g;
    private final boolean h;

    public DataDeleteRequest(int i, long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = Collections.unmodifiableList(list);
        this.e = Collections.unmodifiableList(list2);
        this.f = list3;
        this.g = z;
        this.h = z2;
    }

    private boolean a(DataDeleteRequest dataDeleteRequest) {
        return this.b == dataDeleteRequest.b && this.c == dataDeleteRequest.c && jy.a(this.d, dataDeleteRequest.d) && jy.a(this.e, dataDeleteRequest.e) && jy.a(this.f, dataDeleteRequest.f) && this.g == dataDeleteRequest.g && this.h == dataDeleteRequest.h;
    }

    public long a() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public List<DataSource> c() {
        return this.d;
    }

    public List<DataType> d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<Session> e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof DataDeleteRequest) && a((DataDeleteRequest) obj));
    }

    public boolean f() {
        return this.g;
    }

    public boolean g() {
        return this.h;
    }

    public int h() {
        return this.a;
    }

    public int hashCode() {
        return jy.a(Long.valueOf(this.b), Long.valueOf(this.c));
    }

    public String toString() {
        return jy.a(this).a("startTimeMillis", Long.valueOf(this.b)).a("endTimeMillis", Long.valueOf(this.c)).a("dataSources", this.d).a("dateTypes", this.e).a("sessions", this.f).a("deleteAllData", Boolean.valueOf(this.g)).a("deleteAllSessions", Boolean.valueOf(this.h)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pj.a(this, parcel, i);
    }
}
