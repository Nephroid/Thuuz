package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.hl;
import defpackage.hr;
import defpackage.jy;

/* loaded from: classes.dex */
public final class Status implements SafeParcelable {
    private final int f;
    private final int g;
    private final String h;
    private final PendingIntent i;
    public static final Status a = new Status(0);
    public static final Status b = new Status(14);
    public static final Status c = new Status(8);
    public static final Status d = new Status(15);
    public static final Status e = new Status(16);
    public static final hr CREATOR = new hr();

    public Status(int i) {
        this(1, i, null, null);
    }

    public Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.f = i;
        this.g = i2;
        this.h = str;
        this.i = pendingIntent;
    }

    private String e() {
        return this.h != null ? this.h : hl.a(this.g);
    }

    public PendingIntent a() {
        return this.i;
    }

    public String b() {
        return this.h;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.f == status.f && this.g == status.g && jy.a(this.h, status.h) && jy.a(this.i, status.i);
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.f), Integer.valueOf(this.g), this.h, this.i);
    }

    public String toString() {
        return jy.a(this).a("statusCode", e()).a("resolution", this.i).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        hr.a(this, parcel, i);
    }
}
