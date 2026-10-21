package com.google.android.gms.auth.api;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.gs;

/* loaded from: classes.dex */
public class GoogleAuthApiResponse implements SafeParcelable {
    public static final gs CREATOR = new gs();
    public final int a;
    public final int b;
    public final Bundle c;
    public final byte[] d;

    public GoogleAuthApiResponse(int i, int i2, Bundle bundle, byte[] bArr) {
        this.a = i;
        this.b = i2;
        this.c = bundle;
        this.d = bArr;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        gs.a(this, parcel, i);
    }
}
