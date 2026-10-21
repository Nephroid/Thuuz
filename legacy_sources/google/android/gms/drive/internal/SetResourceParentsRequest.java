package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.lk;
import java.util.List;

/* loaded from: classes.dex */
public class SetResourceParentsRequest implements SafeParcelable {
    public static final Parcelable.Creator<SetResourceParentsRequest> CREATOR = new lk();
    public final int a;
    public final DriveId b;
    public final List<DriveId> c;

    public SetResourceParentsRequest(int i, DriveId driveId, List<DriveId> list) {
        this.a = i;
        this.b = driveId;
        this.c = list;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lk.a(this, parcel, i);
    }
}
