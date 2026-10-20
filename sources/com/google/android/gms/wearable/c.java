package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avi;
import defpackage.jy;

/* loaded from: classes.dex */
public class c implements SafeParcelable {
    public static final Parcelable.Creator<c> CREATOR = new avi();
    public final int a;
    private final String b;
    private final String c;
    private final int d;
    private final int e;
    private final boolean f;
    private boolean g;
    private String h;

    public c(int i, String str, String str2, int i2, int i3, boolean z, boolean z2, String str3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = i2;
        this.e = i3;
        this.f = z;
        this.g = z2;
        this.h = str3;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return jy.a(Integer.valueOf(this.a), Integer.valueOf(cVar.a)) && jy.a(this.b, cVar.b) && jy.a(this.c, cVar.c) && jy.a(Integer.valueOf(this.d), Integer.valueOf(cVar.d)) && jy.a(Integer.valueOf(this.e), Integer.valueOf(cVar.e)) && jy.a(Boolean.valueOf(this.f), Boolean.valueOf(cVar.f));
    }

    public String f() {
        return this.h;
    }

    public boolean g() {
        return this.f;
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.a), this.b, this.c, Integer.valueOf(this.d), Integer.valueOf(this.e), Boolean.valueOf(this.f));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ConnectionConfiguration[ ");
        sb.append("mName=" + this.b);
        sb.append(", mAddress=" + this.c);
        sb.append(", mType=" + this.d);
        sb.append(", mRole=" + this.e);
        sb.append(", mEnabled=" + this.f);
        sb.append(", mIsConnected=" + this.g);
        sb.append(", mEnabled=" + this.h);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avi.a(this, parcel, i);
    }
}
