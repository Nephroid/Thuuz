package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kf;

@Deprecated
/* loaded from: classes.dex */
public class Contents implements SafeParcelable {
    public static final Parcelable.Creator<Contents> CREATOR = new kf();
    public final int a;
    public final ParcelFileDescriptor b;
    public final int c;
    public final int d;
    public final DriveId e;
    public final boolean f;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;

    public Contents(int i, ParcelFileDescriptor parcelFileDescriptor, int i2, int i3, DriveId driveId, boolean z) {
        this.a = i;
        this.b = parcelFileDescriptor;
        this.c = i2;
        this.d = i3;
        this.e = driveId;
        this.f = z;
    }

    public int a() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kf.a(this, parcel, i);
    }
}
