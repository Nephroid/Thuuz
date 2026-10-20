package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kh;

/* loaded from: classes.dex */
public class DrivePreferences implements SafeParcelable {
    public static final Parcelable.Creator<DrivePreferences> CREATOR = new kh();
    public final int a;
    public final boolean b;

    public DrivePreferences(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kh.a(this, parcel, i);
    }
}
