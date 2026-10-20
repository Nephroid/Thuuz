package com.google.android.gms.plus.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aqz;
import defpackage.jy;
import java.util.Arrays;

/* loaded from: classes.dex */
public class h implements SafeParcelable {
    public static final aqz CREATOR = new aqz();
    private final int a;
    private final String b;
    private final String[] c;
    private final String[] d;
    private final String[] e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final PlusCommonExtras j;

    public h(int i, String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, String str5, PlusCommonExtras plusCommonExtras) {
        this.a = i;
        this.b = str;
        this.c = strArr;
        this.d = strArr2;
        this.e = strArr3;
        this.f = str2;
        this.g = str3;
        this.h = str4;
        this.i = str5;
        this.j = plusCommonExtras;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String[] c() {
        return this.c;
    }

    public String[] d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String[] e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.a == hVar.a && jy.a(this.b, hVar.b) && Arrays.equals(this.c, hVar.c) && Arrays.equals(this.d, hVar.d) && Arrays.equals(this.e, hVar.e) && jy.a(this.f, hVar.f) && jy.a(this.g, hVar.g) && jy.a(this.h, hVar.h) && jy.a(this.i, hVar.i) && jy.a(this.j, hVar.j);
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.a), this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
    }

    public String i() {
        return this.i;
    }

    public PlusCommonExtras j() {
        return this.j;
    }

    public String toString() {
        return jy.a(this).a("versionCode", Integer.valueOf(this.a)).a("accountName", this.b).a("requestedScopes", this.c).a("visibleActivities", this.d).a("requiredFeatures", this.e).a("packageNameForAuth", this.f).a("callingPackageName", this.g).a("applicationName", this.h).a("extra", this.j.toString()).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aqz.a(this, parcel, i);
    }
}
