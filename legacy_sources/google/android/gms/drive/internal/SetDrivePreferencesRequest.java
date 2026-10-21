package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DrivePreferences;
import defpackage.li;

/* loaded from: classes.dex */
public class SetDrivePreferencesRequest implements SafeParcelable {
    public static final Parcelable.Creator<SetDrivePreferencesRequest> CREATOR = new li();
    public final int a;
    public final DrivePreferences b;

    public SetDrivePreferencesRequest(int i, DrivePreferences drivePreferences) {
        this.a = i;
        this.b = drivePreferences;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        li.a(this, parcel, i);
    }
}
