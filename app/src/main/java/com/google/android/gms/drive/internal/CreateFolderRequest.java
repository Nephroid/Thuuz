package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.kb;
import defpackage.lt;

/* loaded from: classes.dex */
public class CreateFolderRequest implements SafeParcelable {
    public static final Parcelable.Creator<CreateFolderRequest> CREATOR = new lt();
    public final int a;
    public final DriveId b;
    public final MetadataBundle c;

    public CreateFolderRequest(int i, DriveId driveId, MetadataBundle metadataBundle) {
        this.a = i;
        this.b = (DriveId) kb.a(driveId);
        this.c = (MetadataBundle) kb.a(metadataBundle);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lt.a(this, parcel, i);
    }
}
