package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aja;

/* loaded from: classes.dex */
public final class oo implements SafeParcelable {
    public static final Parcelable.Creator<oo> CREATOR = new aja();
    public String[] a;
    public byte[][] b;
    private final int c;

    oo() {
        this(1, new String[0], new byte[0]);
    }

    public oo(int i, String[] strArr, byte[][] bArr) {
        this.c = i;
        this.a = strArr;
        this.b = bArr;
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
        aja.a(this, parcel, i);
    }
}
