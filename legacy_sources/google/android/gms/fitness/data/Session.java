package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.jy;
import defpackage.ox;

/* loaded from: classes.dex */
public class Session implements SafeParcelable {
    public static final Parcelable.Creator<Session> CREATOR = new ox();
    private final int a;
    private final long b;
    private final long c;
    private final String d;
    private final String e;
    private final String f;
    private final int g;
    private final a h;

    public Session(int i, long j, long j2, String str, String str2, String str3, int i2, a aVar) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = i2;
        this.h = aVar;
    }

    private boolean a(Session session) {
        return this.b == session.b && this.c == session.c && jy.a(this.d, session.d) && jy.a(this.e, session.e) && jy.a(this.f, session.f) && jy.a(this.h, session.h) && this.g == session.g;
    }

    public long a() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Session) && a((Session) obj));
    }

    public int f() {
        return this.g;
    }

    public a g() {
        return this.h;
    }

    public int h() {
        return this.a;
    }

    public int hashCode() {
        return jy.a(Long.valueOf(this.b), Long.valueOf(this.c), this.d, this.e, Integer.valueOf(this.g), this.h, this.f);
    }

    public String toString() {
        return jy.a(this).a("startTime", Long.valueOf(this.b)).a("endTime", Long.valueOf(this.c)).a(Utilities.DB_KEY_IMAGE_NAME, this.d).a("identifier", this.e).a("description", this.f).a("activity", Integer.valueOf(this.g)).a("application", this.h).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ox.a(this, parcel, i);
    }
}
