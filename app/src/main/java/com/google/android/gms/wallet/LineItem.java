package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aun;

/* loaded from: classes.dex */
public final class LineItem implements SafeParcelable {
    public static final Parcelable.Creator<LineItem> CREATOR = new aun();
    public String a;
    public String b;
    public String c;
    public String d;
    public int e;
    public String f;
    private final int g;

    LineItem() {
        this.g = 1;
        this.e = 0;
    }

    public LineItem(int i, String str, String str2, String str3, String str4, int i2, String str5) {
        this.g = i;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = i2;
        this.f = str5;
    }

    public int a() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aun.a(this, parcel, i);
    }
}
