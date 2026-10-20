package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.dw;
import defpackage.uh;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class bj implements SafeParcelable {
    public static final uh CREATOR = new uh();
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final String j;
    public final int k;
    public final String l;
    public final int m;
    public final int n;
    public final String o;

    public bj(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str, int i10, String str2, int i11, int i12, String str3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
        this.i = i9;
        this.j = str;
        this.k = i10;
        this.l = str2;
        this.m = i11;
        this.n = i12;
        this.o = str3;
    }

    public bj(dw dwVar) {
        this.a = 1;
        this.b = dwVar.a();
        this.c = dwVar.b();
        this.d = dwVar.c();
        this.e = dwVar.d();
        this.f = dwVar.e();
        this.g = dwVar.f();
        this.h = dwVar.g();
        this.i = dwVar.h();
        this.j = dwVar.i();
        this.k = dwVar.j();
        this.l = dwVar.k();
        this.m = dwVar.l();
        this.n = dwVar.m();
        this.o = dwVar.n();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        uh.a(this, parcel, i);
    }
}
