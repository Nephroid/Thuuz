package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.nw;

/* loaded from: classes.dex */
public class ValuesSetDetails implements SafeParcelable {
    public static final Parcelable.Creator<ValuesSetDetails> CREATOR = new nw();
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public ValuesSetDetails(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nw.a(this, parcel, i);
    }
}
