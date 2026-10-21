package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ld;

/* loaded from: classes.dex */
public class OnSyncMoreResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnSyncMoreResponse> CREATOR = new ld();
    public final int a;
    public final boolean b;

    public OnSyncMoreResponse(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ld.a(this, parcel, i);
    }
}
