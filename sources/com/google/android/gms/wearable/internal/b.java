package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avm;
import defpackage.avn;
import defpackage.avw;

/* loaded from: classes.dex */
public class b implements SafeParcelable {
    public static final Parcelable.Creator<b> CREATOR = new avw();
    public final int a;
    public final avm b;
    public final IntentFilter[] c;

    public b(int i, IBinder iBinder, IntentFilter[] intentFilterArr) {
        this.a = i;
        if (iBinder != null) {
            this.b = avn.a(iBinder);
        } else {
            this.b = null;
        }
        this.c = intentFilterArr;
    }

    public IBinder a() {
        if (this.b == null) {
            return null;
        }
        return this.b.asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avw.a(this, parcel, i);
    }
}
