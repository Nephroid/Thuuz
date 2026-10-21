package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aej;

/* loaded from: classes.dex */
public class he implements SafeParcelable {
    public static final aej CREATOR = new aej();
    public final int a;
    public final hi[] b;
    public final String c;
    public final boolean d;
    public final Account e;

    public he(int i, hi[] hiVarArr, String str, boolean z, Account account) {
        this.a = i;
        this.b = hiVarArr;
        this.c = str;
        this.d = z;
        this.e = account;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aej aejVar = CREATOR;
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aej aejVar = CREATOR;
        aej.a(this, parcel, i);
    }
}
