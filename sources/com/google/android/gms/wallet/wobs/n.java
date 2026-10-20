package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avd;

/* loaded from: classes.dex */
public final class n implements SafeParcelable {
    public static final Parcelable.Creator<n> CREATOR = new avd();
    public String a;
    public String b;
    private final int c;

    n() {
        this.c = 1;
    }

    public n(int i, String str, String str2) {
        this.c = i;
        this.a = str;
        this.b = str2;
    }

    public int a() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avd.a(this, parcel, i);
    }
}
