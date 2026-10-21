package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aut;

/* loaded from: classes.dex */
public final class ProxyCard implements SafeParcelable {
    public static final Parcelable.Creator<ProxyCard> CREATOR = new aut();
    public String a;
    public String b;
    public int c;
    public int d;
    private final int e;

    public ProxyCard(int i, String str, String str2, int i2, int i3) {
        this.e = i;
        this.a = str;
        this.b = str2;
        this.c = i2;
        this.d = i3;
    }

    public int a() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aut.a(this, parcel, i);
    }
}
