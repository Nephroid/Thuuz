package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.afk;

/* loaded from: classes.dex */
public class jb implements SafeParcelable {
    public static final afk CREATOR = new afk();
    public final int a;
    public final String b;
    public final int c;

    public jb(int i, String str, int i2) {
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        afk.a(this, parcel, i);
    }
}
