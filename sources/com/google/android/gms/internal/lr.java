package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ahf;
import defpackage.jy;

/* loaded from: classes.dex */
public class lr implements SafeParcelable {
    public static final ahf CREATOR = new ahf();
    public final int a;
    public final String b;
    private final int c;

    public lr(int i, int i2, String str) {
        this.c = i;
        this.a = i2;
        this.b = str;
    }

    public int a() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof lr)) {
            return false;
        }
        lr lrVar = (lr) obj;
        return lrVar.a == this.a && jy.a(lrVar.b, this.b);
    }

    public int hashCode() {
        return this.a;
    }

    public String toString() {
        return String.format("%d:%s", Integer.valueOf(this.a), this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ahf.a(this, parcel, i);
    }
}
