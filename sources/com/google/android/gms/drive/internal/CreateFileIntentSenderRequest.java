package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.lr;

/* loaded from: classes.dex */
public class CreateFileIntentSenderRequest implements SafeParcelable {
    public static final Parcelable.Creator<CreateFileIntentSenderRequest> CREATOR = new lr();
    public final int a;
    public final MetadataBundle b;
    public final int c;
    public final String d;
    public final DriveId e;
    public final Integer f;

    public CreateFileIntentSenderRequest(int i, MetadataBundle metadataBundle, int i2, String str, DriveId driveId, Integer num) {
        this.a = i;
        this.b = metadataBundle;
        this.c = i2;
        this.d = str;
        this.e = driveId;
        this.f = num;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lr.a(this, parcel, i);
    }
}
