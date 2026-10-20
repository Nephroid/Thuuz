package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aij;
import defpackage.jy;
import java.util.Locale;

/* loaded from: classes.dex */
public class mw implements SafeParcelable {
    public static final aij CREATOR = new aij();
    public final int a;
    public final String b;
    public final String c;
    public final String d;

    public mw(int i, String str, String str2, String str3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public mw(String str, Locale locale, String str2) {
        this.a = 0;
        this.b = str;
        this.c = locale.toString();
        this.d = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aij aijVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof mw)) {
            return false;
        }
        mw mwVar = (mw) obj;
        return this.c.equals(mwVar.c) && this.b.equals(mwVar.b) && jy.a(this.d, mwVar.d);
    }

    public int hashCode() {
        return jy.a(this.b, this.c, this.d);
    }

    public String toString() {
        return jy.a(this).a("clientPackageName", this.b).a("locale", this.c).a("accountName", this.d).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aij aijVar = CREATOR;
        aij.a(this, parcel, i);
    }
}
