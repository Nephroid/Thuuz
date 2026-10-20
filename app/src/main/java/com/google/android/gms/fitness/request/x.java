package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.jy;
import defpackage.pz;

/* loaded from: classes.dex */
public class x implements SafeParcelable {
    public static final Parcelable.Creator<x> CREATOR = new pz();
    private final int a;
    private final String b;
    private final String c;

    public x(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    private boolean a(x xVar) {
        return jy.a(this.b, xVar.b) && jy.a(this.c, xVar.c);
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof x) && a((x) obj));
    }

    public int hashCode() {
        return jy.a(this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a(Utilities.DB_KEY_IMAGE_NAME, this.b).a("identifier", this.c).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pz.a(this, parcel, i);
    }
}
