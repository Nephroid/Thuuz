package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.km;

/* loaded from: classes.dex */
public class UserMetadata implements SafeParcelable {
    public static final Parcelable.Creator<UserMetadata> CREATOR = new km();
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final String f;

    public UserMetadata(int i, String str, String str2, String str3, boolean z, String str4) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = str4;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", this.b, this.c, this.d, Boolean.valueOf(this.e), this.f);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        km.a(this, parcel, i);
    }
}
