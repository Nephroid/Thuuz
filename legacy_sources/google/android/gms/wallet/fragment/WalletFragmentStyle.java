package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.auk;

/* loaded from: classes.dex */
public final class WalletFragmentStyle implements SafeParcelable {
    public static final Parcelable.Creator<WalletFragmentStyle> CREATOR = new auk();
    public final int a;
    public Bundle b;
    public int c;

    public WalletFragmentStyle() {
        this.a = 1;
        this.b = new Bundle();
    }

    public WalletFragmentStyle(int i, Bundle bundle, int i2) {
        this.a = i;
        this.b = bundle;
        this.c = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        auk.a(this, parcel, i);
    }
}
