package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.lo;

/* loaded from: classes.dex */
public class CloseContentsAndUpdateMetadataRequest implements SafeParcelable {
    public static final Parcelable.Creator<CloseContentsAndUpdateMetadataRequest> CREATOR = new lo();
    public final int a;
    public final DriveId b;
    public final MetadataBundle c;
    public final Contents d;
    public final boolean e;
    public final String f;
    public final int g;

    public CloseContentsAndUpdateMetadataRequest(int i, DriveId driveId, MetadataBundle metadataBundle, Contents contents, boolean z, String str, int i2) {
        this.a = i;
        this.b = driveId;
        this.c = metadataBundle;
        this.d = contents;
        this.e = z;
        this.f = str;
        this.g = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lo.a(this, parcel, i);
    }
}
