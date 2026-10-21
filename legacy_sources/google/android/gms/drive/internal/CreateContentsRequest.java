package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kb;
import defpackage.lq;

/* loaded from: classes.dex */
public class CreateContentsRequest implements SafeParcelable {
    public static final Parcelable.Creator<CreateContentsRequest> CREATOR = new lq();
    public final int a;
    public final int b;

    public CreateContentsRequest(int i, int i2) {
        this.a = i;
        kb.b(i2 == 536870912 || i2 == 805306368, "Cannot create a new read-only contents!");
        this.b = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lq.a(this, parcel, i);
    }
}
