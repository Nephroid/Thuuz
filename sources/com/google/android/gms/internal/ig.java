package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aeu;
import defpackage.aev;
import defpackage.jy;

/* loaded from: classes.dex */
public class ig implements SafeParcelable {
    public static final Parcelable.Creator<ig> CREATOR = new aeu();
    private final int a;
    private String b;

    public ig() {
        this(1, null);
    }

    public ig(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ig) {
            return aev.a(this.b, ((ig) obj).b);
        }
        return false;
    }

    public int hashCode() {
        return jy.a(this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aeu.a(this, parcel, i);
    }
}
