package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.lh;

/* loaded from: classes.dex */
public class RemoveEventListenerRequest implements SafeParcelable {
    public static final Parcelable.Creator<RemoveEventListenerRequest> CREATOR = new lh();
    public final int a;
    public final DriveId b;
    public final int c;

    public RemoveEventListenerRequest(int i, DriveId driveId, int i2) {
        this.a = i;
        this.b = driveId;
        this.c = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lh.a(this, parcel, i);
    }
}
