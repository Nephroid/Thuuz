package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aue;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class Cart implements SafeParcelable {
    public static final Parcelable.Creator<Cart> CREATOR = new aue();
    public String a;
    public String b;
    public ArrayList<LineItem> c;
    private final int d;

    Cart() {
        this.d = 1;
        this.c = new ArrayList<>();
    }

    public Cart(int i, String str, String str2, ArrayList<LineItem> arrayList) {
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
        aue.a(this, parcel, i);
    }
}
