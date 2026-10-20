package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avb;

/* loaded from: classes.dex */
public final class j implements SafeParcelable {
    public static final Parcelable.Creator<j> CREATOR = new avb();
    public String a;
    public String b;
    private final int c;

    j() {
        this.c = 1;
    }

    public j(int i, String str, String str2) {
        this.c = i;
        this.a = str;
        this.b = str2;
    }

    public int a() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avb.a(this, parcel, i);
    }
}
