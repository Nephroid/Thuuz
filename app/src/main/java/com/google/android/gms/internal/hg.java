package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aek;

/* loaded from: classes.dex */
public class hg implements SafeParcelable {
    public static final aek CREATOR = new aek();
    public final int a;
    public final String b;
    public final String c;
    public final String d;

    public hg(int i, String str, String str2, String str3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aek aekVar = CREATOR;
        return 0;
    }

    public String toString() {
        return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", this.b, this.c, this.d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aek aekVar = CREATOR;
        aek.a(this, parcel, i);
    }
}
