package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ave;

/* loaded from: classes.dex */
public final class p implements SafeParcelable {
    public static final Parcelable.Creator<p> CREATOR = new ave();
    public String a;
    public String b;
    public l c;
    public n d;
    public n e;
    private final int f;

    p() {
        this.f = 1;
    }

    public p(int i, String str, String str2, l lVar, n nVar, n nVar2) {
        this.f = i;
        this.a = str;
        this.b = str2;
        this.c = lVar;
        this.d = nVar;
        this.e = nVar2;
    }

    public int a() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ave.a(this, parcel, i);
    }
}
