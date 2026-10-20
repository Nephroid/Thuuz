package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aha;
import defpackage.jy;
import defpackage.kb;
import defpackage.op;

/* loaded from: classes.dex */
public final class Device implements SafeParcelable {
    public static final Parcelable.Creator<Device> CREATOR = new op();
    private final int a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final int f;

    public Device(int i, String str, String str2, String str3, String str4, int i2) {
        this.a = i;
        this.b = (String) kb.a(str);
        this.c = (String) kb.a(str2);
        this.d = "";
        this.e = (String) kb.a(str4);
        this.f = i2;
    }

    public Device(String str, String str2, String str3, int i) {
        this(1, str, str2, "", str3, i);
    }

    public Device(String str, String str2, String str3, String str4, int i) {
        this(str, str2, str4, i);
    }

    private boolean a(Device device) {
        return jy.a(this.b, device.b) && jy.a(this.c, device.c) && jy.a(this.d, device.d) && jy.a(this.e, device.e) && this.f == device.f;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public int d() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String e() {
        return String.format("%s:%s:%s", this.b, this.c, this.e);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Device) && a((Device) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Device f() {
        return new Device(aha.a(this.b), aha.a(this.c), aha.a(this.d), this.e, this.f);
    }

    public String g() {
        return aha.a() ? this.e : aha.a(this.e);
    }

    public int h() {
        return this.a;
    }

    public int hashCode() {
        return jy.a(this.b, this.c, this.d, this.e, Integer.valueOf(this.f));
    }

    public String toString() {
        return String.format("Device{%s:%s:%s}", e(), this.d, Integer.valueOf(this.f));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        op.a(this, parcel, i);
    }
}
