package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.afu;
import defpackage.auy;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class d implements SafeParcelable {
    public static final Parcelable.Creator<d> CREATOR = new auy();
    public String a;
    public String b;
    public ArrayList<b> c;
    private final int d;

    d() {
        this.d = 1;
        this.c = afu.a();
    }

    public d(int i, String str, String str2, ArrayList<b> arrayList) {
        this.d = i;
        this.a = str;
        this.b = str2;
        this.c = arrayList;
    }

    public int a() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        auy.a(this, parcel, i);
    }
}
