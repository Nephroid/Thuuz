package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;
import defpackage.pi;

/* loaded from: classes.dex */
public class b implements SafeParcelable {
    public static final Parcelable.Creator<b> CREATOR = new pi();
    private final int a;
    private final String b;
    private final BleDevice c;

    public b(int i, String str, BleDevice bleDevice) {
        this.a = i;
        this.b = str;
        this.c = bleDevice;
    }

    public String a() {
        return this.b;
    }

    public BleDevice b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", this.b, this.c);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pi.a(this, parcel, i);
    }
}
