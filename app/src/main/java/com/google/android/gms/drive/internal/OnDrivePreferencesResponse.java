package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DrivePreferences;
import defpackage.kv;

/* loaded from: classes.dex */
public class OnDrivePreferencesResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnDrivePreferencesResponse> CREATOR = new kv();
    public final int a;
    public DrivePreferences b;

    public OnDrivePreferencesResponse(int i, DrivePreferences drivePreferences) {
        this.a = i;
        this.b = drivePreferences;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kv.a(this, parcel, i);
    }
}
