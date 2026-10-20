package com.google.android.gms.games.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.qq;

/* loaded from: classes.dex */
public class ConnectionInfo implements SafeParcelable {
    public static final qq CREATOR = new qq();
    private final int a;
    private final String b;
    private final int c;

    public ConnectionInfo(int i, String str, int i2) {
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qq.a(this, parcel, i);
    }
}
