package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avc;

/* loaded from: classes.dex */
public final class l implements SafeParcelable {
    public static final Parcelable.Creator<l> CREATOR = new avc();
    public long a;
    public long b;
    private final int c;

    l() {
        this.c = 1;
    }

    public l(int i, long j, long j2) {
        this.c = i;
        this.a = j;
        this.b = j2;
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
        avc.a(this, parcel, i);
    }
}
