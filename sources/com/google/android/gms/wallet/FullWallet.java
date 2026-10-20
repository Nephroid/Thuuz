package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;
import defpackage.auh;

/* loaded from: classes.dex */
public final class FullWallet implements SafeParcelable {
    public static final Parcelable.Creator<FullWallet> CREATOR = new auh();
    public String a;
    public String b;
    public ProxyCard c;
    public String d;
    public Address e;
    public Address f;
    public String[] g;
    public UserAddress h;
    public UserAddress i;
    public InstrumentInfo[] j;
    private final int k;

    private FullWallet() {
        this.k = 1;
    }

    public FullWallet(int i, String str, String str2, ProxyCard proxyCard, String str3, Address address, Address address2, String[] strArr, UserAddress userAddress, UserAddress userAddress2, InstrumentInfo[] instrumentInfoArr) {
        this.k = i;
        this.a = str;
        this.b = str2;
        this.c = proxyCard;
        this.d = str3;
        this.e = address;
        this.f = address2;
        this.g = strArr;
        this.h = userAddress;
        this.i = userAddress2;
        this.j = instrumentInfoArr;
    }

    public int a() {
        return this.k;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        auh.a(this, parcel, i);
    }
}
