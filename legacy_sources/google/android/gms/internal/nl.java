package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aim;
import defpackage.jy;

/* loaded from: classes.dex */
public class nl implements SafeParcelable {
    public static final aim CREATOR = new aim();
    public final int a;
    public final String b;
    public final int c;
    public final int d;
    public final String e;
    public final String f;
    public final boolean g;

    public nl(int i, String str, int i2, int i3, String str2, String str3, boolean z) {
        this.a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = str2;
        this.f = str3;
        this.g = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nl)) {
            return false;
        }
        nl nlVar = (nl) obj;
        return this.b.equals(nlVar.b) && this.c == nlVar.c && this.d == nlVar.d && jy.a(this.e, nlVar.e) && jy.a(this.f, nlVar.f) && this.g == nlVar.g;
    }

    public int hashCode() {
        return jy.a(this.b, Integer.valueOf(this.c), Integer.valueOf(this.d), this.e, this.f, Boolean.valueOf(this.g));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("package=").append(this.b).append(',');
        sb.append("versionCode=").append(this.a).append(',');
        sb.append("logSource=").append(this.d).append(',');
        sb.append("uploadAccount=").append(this.e).append(',');
        sb.append("loggingId=").append(this.f).append(',');
        sb.append("logAndroidId=").append(this.g);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aim.a(this, parcel, i);
    }
}
