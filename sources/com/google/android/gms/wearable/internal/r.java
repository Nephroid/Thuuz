package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.c;
import defpackage.awa;

@Deprecated
/* loaded from: classes.dex */
public class r implements SafeParcelable {
    public static final Parcelable.Creator<r> CREATOR = new awa();
    public final int a;
    public final int b;
    public final c c;

    public r(int i, int i2, c cVar) {
        this.a = i;
        this.b = i2;
        this.c = cVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        awa.a(this, parcel, i);
    }
}
