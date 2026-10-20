package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.lu;

/* loaded from: classes.dex */
public class DeleteResourceRequest implements SafeParcelable {
    public static final Parcelable.Creator<DeleteResourceRequest> CREATOR = new lu();
    public final int a;
    public final DriveId b;

    public DeleteResourceRequest(int i, DriveId driveId) {
        this.a = i;
        this.b = driveId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lu.a(this, parcel, i);
    }
}
