package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.nt;

/* loaded from: classes.dex */
public class ValueChangedDetails implements SafeParcelable {
    public static final Parcelable.Creator<ValueChangedDetails> CREATOR = new nt();
    public final int a;
    public final int b;

    public ValueChangedDetails(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nt.a(this, parcel, i);
    }
}
