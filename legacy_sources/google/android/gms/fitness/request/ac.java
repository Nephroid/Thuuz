package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.pe;
import defpackage.pp;
import defpackage.pq;

/* loaded from: classes.dex */
public class ac implements SafeParcelable {
    public static final Parcelable.Creator<ac> CREATOR = new pe();
    private final int a;
    private final pp b;

    public ac(int i, IBinder iBinder) {
        this.a = i;
        this.b = pq.a(iBinder);
    }

    public int a() {
        return this.a;
    }

    public IBinder b() {
        return this.b.asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pe.a(this, parcel, i);
    }
}
