package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.le;

/* loaded from: classes.dex */
public class OpenContentsRequest implements SafeParcelable {
    public static final Parcelable.Creator<OpenContentsRequest> CREATOR = new le();
    public final int a;
    public final DriveId b;
    public final int c;
    public final int d;

    public OpenContentsRequest(int i, DriveId driveId, int i2, int i3) {
        this.a = i;
        this.b = driveId;
        this.c = i2;
        this.d = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        le.a(this, parcel, i);
    }
}
