package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aia;
import defpackage.jy;

/* loaded from: classes.dex */
public class mk implements SafeParcelable {
    public static final aia CREATOR = new aia();
    public final int a;
    private final String b;
    private final String c;

    public mk(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aia aiaVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof mk)) {
            return false;
        }
        mk mkVar = (mk) obj;
        return jy.a(this.b, mkVar.b) && jy.a(this.c, mkVar.c);
    }

    public int hashCode() {
        return jy.a(this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a("mPlaceId", this.b).a("mTag", this.c).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aia aiaVar = CREATOR;
        aia.a(this, parcel, i);
    }
}
