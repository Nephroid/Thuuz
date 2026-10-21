package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import defpackage.jy;
import defpackage.pw;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class SessionReadRequest implements SafeParcelable {
    public static final Parcelable.Creator<SessionReadRequest> CREATOR = new pw();
    private final int a;
    private final String b;
    private final String c;
    private final long d;
    private final long e;
    private final List<DataType> f;
    private final List<DataSource> g;
    private boolean h;
    private final boolean i;
    private final List<String> j;

    public SessionReadRequest(int i, String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = j;
        this.e = j2;
        this.f = Collections.unmodifiableList(list);
        this.g = Collections.unmodifiableList(list2);
        this.h = z;
        this.i = z2;
        this.j = list3;
    }

    private boolean a(SessionReadRequest sessionReadRequest) {
        return jy.a(this.b, sessionReadRequest.b) && this.c.equals(sessionReadRequest.c) && this.d == sessionReadRequest.d && this.e == sessionReadRequest.e && jy.a(this.f, sessionReadRequest.f) && jy.a(this.g, sessionReadRequest.g) && this.h == sessionReadRequest.h && this.j.equals(sessionReadRequest.j) && this.i == sessionReadRequest.i;
    }

    public String a() {
        return this.b;
    }

    public String b() {
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
        return this == obj || ((obj instanceof SessionReadRequest) && a((SessionReadRequest) obj));
    }

    public List<DataSource> f() {
        return this.g;
    }

    public boolean g() {
        return this.h;
    }

    public List<String> h() {
        return this.j;
    }

    public int hashCode() {
        return jy.a(this.b, this.c, Long.valueOf(this.d), Long.valueOf(this.e));
    }

    public boolean i() {
        return this.i;
    }

    public int j() {
        return this.a;
    }

    public String toString() {
        return jy.a(this).a("sessionName", this.b).a("sessionId", this.c).a("startTimeMillis", Long.valueOf(this.d)).a("endTimeMillis", Long.valueOf(this.e)).a("dataTypes", this.f).a("dataSources", this.g).a("sessionsFromAllApps", Boolean.valueOf(this.h)).a("excludedPackages", this.j).a("useServer", Boolean.valueOf(this.i)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pw.a(this, parcel, i);
    }
}
