package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.kb;
import defpackage.ls;

/* loaded from: classes.dex */
public class CreateFileRequest implements SafeParcelable {
    public static final Parcelable.Creator<CreateFileRequest> CREATOR = new ls();
    public final int a;
    public final DriveId b;
    public final MetadataBundle c;
    public final Contents d;
    public final Integer e;
    public final boolean f;
    public final String g;
    public final int h;
    public final int i;

    public CreateFileRequest(int i, DriveId driveId, MetadataBundle metadataBundle, Contents contents, Integer num, boolean z, String str, int i2, int i3) {
        if (contents != null && i3 != 0) {
            kb.b(contents.a() == i3, "inconsistent contents reference");
        }
        if ((num == null || num.intValue() == 0) && contents == null && i3 == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.a = i;
        this.b = (DriveId) kb.a(driveId);
        this.c = (MetadataBundle) kb.a(metadataBundle);
        this.d = contents;
        this.e = num;
        this.g = str;
        this.h = i2;
        this.f = z;
        this.i = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ls.a(this, parcel, i);
    }
}
