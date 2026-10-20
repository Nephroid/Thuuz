package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aem;

/* loaded from: classes.dex */
public class hk implements SafeParcelable {
    public static final aem CREATOR = new aem();
    public final int a;
    public final int b;
    public final Bundle c;

    public hk(int i, int i2, Bundle bundle) {
        this.a = i;
        this.b = i2;
        this.c = bundle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aem aemVar = CREATOR;
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aem aemVar = CREATOR;
        aem.a(this, parcel, i);
    }
}
