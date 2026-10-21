package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.gp;

/* loaded from: classes.dex */
public class AccountChangeEventsRequest implements SafeParcelable {
    public static final gp CREATOR = new gp();
    public final int a;
    public int b;
    public String c;

    public AccountChangeEventsRequest() {
        this.a = 1;
    }

    public AccountChangeEventsRequest(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        gp.a(this, parcel, i);
    }
}
