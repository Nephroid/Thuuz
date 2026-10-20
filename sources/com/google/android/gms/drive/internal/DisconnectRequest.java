package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.lv;

/* loaded from: classes.dex */
public class DisconnectRequest implements SafeParcelable {
    public static final Parcelable.Creator<DisconnectRequest> CREATOR = new lv();
    public final int a;

    public DisconnectRequest() {
        this(1);
    }

    public DisconnectRequest(int i) {
        this.a = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lv.a(this, parcel, i);
    }
}
