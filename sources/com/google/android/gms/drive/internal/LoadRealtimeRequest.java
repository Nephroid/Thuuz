package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.kq;

/* loaded from: classes.dex */
public class LoadRealtimeRequest implements SafeParcelable {
    public static final Parcelable.Creator<LoadRealtimeRequest> CREATOR = new kq();
    public final int a;
    public final DriveId b;
    public final boolean c;

    public LoadRealtimeRequest(int i, DriveId driveId, boolean z) {
        this.a = i;
        this.b = driveId;
        this.c = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kq.a(this, parcel, i);
    }
}
