package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.la;

/* loaded from: classes.dex */
public class OnLoadRealtimeResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnLoadRealtimeResponse> CREATOR = new la();
    public final int a;
    public final boolean b;

    public OnLoadRealtimeResponse(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        la.a(this, parcel, i);
    }
}
