package com.google.android.gms.plus.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aqx;
import defpackage.jy;

/* loaded from: classes.dex */
public class PlusCommonExtras implements SafeParcelable {
    private final int b;
    private String c;
    private String d;
    public static String a = "PlusCommonExtras";
    public static final aqx CREATOR = new aqx();

    public PlusCommonExtras() {
        this.b = 1;
        this.c = "";
        this.d = "";
    }

    public PlusCommonExtras(int i, String str, String str2) {
        this.b = i;
        this.c = str;
        this.d = str2;
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusCommonExtras)) {
            return false;
        }
        PlusCommonExtras plusCommonExtras = (PlusCommonExtras) obj;
        return this.b == plusCommonExtras.b && jy.a(this.c, plusCommonExtras.c) && jy.a(this.d, plusCommonExtras.d);
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.b), this.c, this.d);
    }

    public String toString() {
        return jy.a(this).a("versionCode", Integer.valueOf(this.b)).a("Gpsrc", this.c).a("ClientCallingPackage", this.d).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aqx.a(this, parcel, i);
    }
}
