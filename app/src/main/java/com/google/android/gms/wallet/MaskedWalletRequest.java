package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.auq;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class MaskedWalletRequest implements SafeParcelable {
    public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new auq();
    public String a;
    public boolean b;
    public boolean c;
    public boolean d;
    public String e;
    public String f;
    public String g;
    public Cart h;
    public boolean i;
    public boolean j;
    public CountrySpecification[] k;
    public boolean l;
    public boolean m;
    public ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> n;
    private final int o;

    MaskedWalletRequest() {
        this.o = 3;
        this.l = true;
        this.m = true;
    }

    public MaskedWalletRequest(int i, String str, boolean z, boolean z2, boolean z3, String str2, String str3, String str4, Cart cart, boolean z4, boolean z5, CountrySpecification[] countrySpecificationArr, boolean z6, boolean z7, ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> arrayList) {
        this.o = i;
        this.a = str;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = cart;
        this.i = z4;
        this.j = z5;
        this.k = countrySpecificationArr;
        this.l = z6;
        this.m = z7;
        this.n = arrayList;
    }

    public int a() {
        return this.o;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        auq.a(this, parcel, i);
    }
}
