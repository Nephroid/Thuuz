package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.lm;

/* loaded from: classes.dex */
public class UpdateMetadataRequest implements SafeParcelable {
    public static final Parcelable.Creator<UpdateMetadataRequest> CREATOR = new lm();
    public final int a;
    public final DriveId b;
    public final MetadataBundle c;

    public UpdateMetadataRequest(int i, DriveId driveId, MetadataBundle metadataBundle) {
        this.a = i;
        this.b = driveId;
        this.c = metadataBundle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lm.a(this, parcel, i);
    }
}
