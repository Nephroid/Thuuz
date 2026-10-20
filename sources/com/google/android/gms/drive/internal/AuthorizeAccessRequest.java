package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.lj;

/* loaded from: classes.dex */
public class AuthorizeAccessRequest implements SafeParcelable {
    public static final Parcelable.Creator<AuthorizeAccessRequest> CREATOR = new lj();
    public final int a;
    public final long b;
    public final DriveId c;

    public AuthorizeAccessRequest(int i, long j, DriveId driveId) {
        this.a = i;
        this.b = j;
        this.c = driveId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lj.a(this, parcel, i);
    }
}
