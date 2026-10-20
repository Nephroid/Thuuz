package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.kp;

/* loaded from: classes.dex */
public class ListParentsRequest implements SafeParcelable {
    public static final Parcelable.Creator<ListParentsRequest> CREATOR = new kp();
    public final int a;
    public final DriveId b;

    public ListParentsRequest(int i, DriveId driveId) {
        this.a = i;
        this.b = driveId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kp.a(this, parcel, i);
    }
}
