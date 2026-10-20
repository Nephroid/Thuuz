package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kl;

/* loaded from: classes.dex */
public class StorageStats implements SafeParcelable {
    public static final Parcelable.Creator<StorageStats> CREATOR = new kl();
    public final int a;
    public final long b;
    public final long c;
    public final long d;
    public final long e;
    public final int f;

    public StorageStats(int i, long j, long j2, long j3, long j4, int i2) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kl.a(this, parcel, i);
    }
}
