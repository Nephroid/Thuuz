package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avm;
import defpackage.avn;
import defpackage.avt;

/* loaded from: classes.dex */
public class aq implements SafeParcelable {
    public static final Parcelable.Creator<aq> CREATOR = new avt();
    public final int a;
    public final avm b;

    public aq(int i, IBinder iBinder) {
        this.a = i;
        if (iBinder != null) {
            this.b = avn.a(iBinder);
        } else {
            this.b = null;
        }
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
        avt.a(this, parcel, i);
    }
}
