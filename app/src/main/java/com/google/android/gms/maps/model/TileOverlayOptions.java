package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aog;
import defpackage.aok;
import defpackage.aol;
import defpackage.apt;
import defpackage.apu;
import defpackage.aqj;
import defpackage.aqk;

/* loaded from: classes.dex */
public final class TileOverlayOptions implements SafeParcelable {
    public static final aqj CREATOR = new aqj();
    private final int a;
    private apt b;
    private aol c;
    private boolean d;
    private float e;
    private boolean f;

    public TileOverlayOptions() {
        this.d = true;
        this.f = true;
        this.a = 1;
    }

    public TileOverlayOptions(int i, IBinder iBinder, boolean z, float f, boolean z2) {
        this.d = true;
        this.f = true;
        this.a = i;
        this.b = apu.a(iBinder);
        this.c = this.b == null ? null : new aok(this);
        this.d = z;
        this.e = f;
        this.f = z2;
    }

    public int a() {
        return this.a;
    }

    public IBinder b() {
        return this.b.asBinder();
    }

    public float c() {
        return this.e;
    }

    public boolean d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean e() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            aqk.a(this, parcel, i);
        } else {
            aqj.a(this, parcel, i);
        }
    }
}
