package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aux;

/* loaded from: classes.dex */
public final class b implements SafeParcelable {
    public static final Parcelable.Creator<b> CREATOR = new aux();
    public String a;
    public String b;
    private final int c;

    b() {
        this.c = 1;
    }

    public b(int i, String str, String str2) {
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
        aux.a(this, parcel, i);
    }
}
