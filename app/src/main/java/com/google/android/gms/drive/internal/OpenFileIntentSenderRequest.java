package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.lf;

/* loaded from: classes.dex */
public class OpenFileIntentSenderRequest implements SafeParcelable {
    public static final Parcelable.Creator<OpenFileIntentSenderRequest> CREATOR = new lf();
    public final int a;
    public final String b;
    public final String[] c;
    public final DriveId d;

    public OpenFileIntentSenderRequest(int i, String str, String[] strArr, DriveId driveId) {
        this.a = i;
        this.b = str;
        this.c = strArr;
        this.d = driveId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lf.a(this, parcel, i);
    }
}
