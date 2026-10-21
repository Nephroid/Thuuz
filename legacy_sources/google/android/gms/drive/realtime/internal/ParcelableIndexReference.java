package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ny;

/* loaded from: classes.dex */
public class ParcelableIndexReference implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableIndexReference> CREATOR = new ny();
    public final int a;
    public final String b;
    public final int c;
    public final boolean d;

    public ParcelableIndexReference(int i, String str, int i2, boolean z) {
        this.a = i;
        this.b = str;
        this.c = i2;
        this.d = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ny.a(this, parcel, i);
    }
}
