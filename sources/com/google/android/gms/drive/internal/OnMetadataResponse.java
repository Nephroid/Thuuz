package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.kz;

/* loaded from: classes.dex */
public class OnMetadataResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnMetadataResponse> CREATOR = new kz();
    public final int a;
    public final MetadataBundle b;

    public OnMetadataResponse(int i, MetadataBundle metadataBundle) {
        this.a = i;
        this.b = metadataBundle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kz.a(this, parcel, i);
    }
}
