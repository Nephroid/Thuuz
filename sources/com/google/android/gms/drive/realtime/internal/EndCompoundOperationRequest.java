package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.nm;

/* loaded from: classes.dex */
public class EndCompoundOperationRequest implements SafeParcelable {
    public static final Parcelable.Creator<EndCompoundOperationRequest> CREATOR = new nm();
    public final int a;

    public EndCompoundOperationRequest() {
        this(1);
    }

    public EndCompoundOperationRequest(int i) {
        this.a = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nm.a(this, parcel, i);
    }
}
