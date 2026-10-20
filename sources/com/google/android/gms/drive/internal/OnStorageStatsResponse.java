package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.StorageStats;
import defpackage.lc;

/* loaded from: classes.dex */
public class OnStorageStatsResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnStorageStatsResponse> CREATOR = new lc();
    public final int a;
    public StorageStats b;

    public OnStorageStatsResponse(int i, StorageStats storageStats) {
        this.a = i;
        this.b = storageStats;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lc.a(this, parcel, i);
    }
}
