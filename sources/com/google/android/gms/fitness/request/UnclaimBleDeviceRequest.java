package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.pg;

/* loaded from: classes.dex */
public class UnclaimBleDeviceRequest implements SafeParcelable {
    public static final Parcelable.Creator<UnclaimBleDeviceRequest> CREATOR = new pg();
    private final int a;
    private final String b;

    public UnclaimBleDeviceRequest(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pg.a(this, parcel, i);
    }
}
