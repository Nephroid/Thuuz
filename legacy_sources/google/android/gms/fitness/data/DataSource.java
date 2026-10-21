package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aha;
import defpackage.jy;
import defpackage.on;

/* loaded from: classes.dex */
public class DataSource implements SafeParcelable {
    public static final Parcelable.Creator<DataSource> CREATOR = new on();
    private final int a;
    private final DataType b;
    private final String c;
    private final int d;
    private final Device e;
    private final a f;
    private final String g;
    private final boolean h;
    private final String i = l();

    public DataSource(int i, DataType dataType, String str, int i2, Device device, a aVar, String str2, boolean z) {
        this.a = i;
        this.b = dataType;
        this.d = i2;
        this.c = str;
        this.e = device;
        this.f = aVar;
        this.g = str2;
        this.h = z;
    }

    private boolean a(DataSource dataSource) {
        return this.b.equals(dataSource.b) && this.d == dataSource.d && jy.a(this.c, dataSource.c) && jy.a(this.e, dataSource.e) && jy.a(this.g, dataSource.g) && jy.a(this.f, dataSource.f);
    }

    private String l() {
        StringBuilder sb = new StringBuilder();
        sb.append(m());
        sb.append(":").append(this.b.a());
        if (this.f != null) {
            sb.append(":").append(this.f.a());
        }
        if (this.e != null) {
            sb.append(":").append(this.e.e());
        }
        if (this.g != null) {
            sb.append(":").append(this.g);
        }
        return sb.toString();
    }

    private String m() {
        switch (this.d) {
            case 0:
                return "raw";
            case 1:
                return "derived";
            default:
                throw new IllegalArgumentException("invalid type value");
        }
    }

    public DataType a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        if (this.f == null) {
            return null;
        }
        return this.f.a();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public a e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof DataSource) && a((DataSource) obj));
    }

    public Device f() {
        return this.e;
    }

    public String g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public String i() {
        return (this.d == 0 ? "r" : "d") + ":" + this.b.c() + (this.f == null ? "" : this.f.equals(a.a) ? ":gms" : ":" + this.f.a()) + (this.e != null ? ":" + this.e.b() : "") + (this.g != null ? ":" + this.g : "");
    }

    public DataSource j() {
        return new DataSource(3, this.b, this.c, this.d, this.e == null ? null : this.e.f(), this.f == null ? null : this.f.d(), aha.a(this.g), this.h);
    }

    public int k() {
        return this.a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(m());
        if (this.c != null) {
            sb.append(":").append(this.c);
        }
        if (this.f != null) {
            sb.append(":").append(this.f);
        }
        if (this.e != null) {
            sb.append(":").append(this.e);
        }
        if (this.g != null) {
            sb.append(":").append(this.g);
        }
        sb.append(":").append(this.b);
        return sb.append("}").toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        on.a(aha.a(this), parcel, i);
    }
}
