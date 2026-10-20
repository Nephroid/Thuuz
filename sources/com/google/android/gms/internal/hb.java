package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.aef;

/* loaded from: classes.dex */
public class hb implements Parcelable {

    @Deprecated
    public static final Parcelable.Creator<hb> CREATOR = new aef();
    private String a;
    private String b;
    private String c;

    @Deprecated
    public hb() {
    }

    @Deprecated
    public hb(Parcel parcel) {
        a(parcel);
    }

    public hb(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @Deprecated
    private void a(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    @Override // android.os.Parcelable
    @Deprecated
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}
