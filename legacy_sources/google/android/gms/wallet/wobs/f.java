package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ava;

/* loaded from: classes.dex */
public final class f implements SafeParcelable {
    public static final Parcelable.Creator<f> CREATOR = new ava();
    public String a;
    public g b;
    public String c;
    public l d;
    private final int e;

    f() {
        this.e = 1;
    }

    public f(int i, String str, g gVar, String str2, l lVar) {
        this.e = i;
        this.a = str;
        this.b = gVar;
        this.c = str2;
        this.d = lVar;
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
        ava.a(this, parcel, i);
    }
}
