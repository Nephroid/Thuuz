package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.kn;

/* loaded from: classes.dex */
public class AddEventListenerRequest implements SafeParcelable {
    public static final Parcelable.Creator<AddEventListenerRequest> CREATOR = new kn();
    public final int a;
    public final DriveId b;
    public final int c;

    public AddEventListenerRequest(int i, DriveId driveId, int i2) {
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
        kn.a(this, parcel, i);
    }
}
