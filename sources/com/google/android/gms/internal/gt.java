package com.google.android.gms.internal;

import android.os.Parcel;
import com.facebook.AppEventsConstants;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ado;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class gt implements SafeParcelable {
    public static final ado CREATOR = new ado();
    public final int a;
    public String b;
    public int c;
    public int d;
    public boolean e;

    public gt(int i, int i2, boolean z) {
        this(1, "afma-sdk-a-v" + i + "." + i2 + "." + (z ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES), i, i2, z);
    }

    public gt(int i, String str, int i2, int i3, boolean z) {
        this.a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ado.a(this, parcel, i);
    }
}
