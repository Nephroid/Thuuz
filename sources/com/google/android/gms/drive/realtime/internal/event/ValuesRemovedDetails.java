package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.nv;

/* loaded from: classes.dex */
public class ValuesRemovedDetails implements SafeParcelable {
    public static final Parcelable.Creator<ValuesRemovedDetails> CREATOR = new nv();
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final String e;
    public final int f;

    public ValuesRemovedDetails(int i, int i2, int i3, int i4, String str, int i5) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = str;
        this.f = i5;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nv.a(this, parcel, i);
    }
}
