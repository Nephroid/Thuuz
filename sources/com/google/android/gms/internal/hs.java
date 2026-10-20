package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aes;

/* loaded from: classes.dex */
public class hs implements SafeParcelable {
    public static final aes CREATOR = new aes();
    public final int a;
    public final hg b;
    public final long c;
    public final int d;
    public final String e;
    public final he f;

    public hs(int i, hg hgVar, long j, int i2, String str, he heVar) {
        this.a = i;
        this.b = hgVar;
        this.c = j;
        this.d = i2;
        this.e = str;
        this.f = heVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aes aesVar = CREATOR;
        return 0;
    }

    public String toString() {
        return String.format("UsageInfo[documentId=%s, timestamp=%d, usageType=%d]", this.b, Long.valueOf(this.c), Integer.valueOf(this.d));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aes aesVar = CREATOR;
        aes.a(this, parcel, i);
    }
}
