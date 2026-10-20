package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aog;
import defpackage.aqh;
import defpackage.aqi;

/* loaded from: classes.dex */
public final class Tile implements SafeParcelable {
    public static final aqh CREATOR = new aqh();
    public final int a;
    public final int b;
    public final byte[] c;
    private final int d;

    public Tile(int i, int i2, int i3, byte[] bArr) {
        this.d = i;
        this.a = i2;
        this.b = i3;
        this.c = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    public int a() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            aqi.a(this, parcel, i);
        } else {
            aqh.a(this, parcel, i);
        }
    }
}
