package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.nl;

/* loaded from: classes.dex */
public class BeginCompoundOperationRequest implements SafeParcelable {
    public static final Parcelable.Creator<BeginCompoundOperationRequest> CREATOR = new nl();
    public final int a;
    public final boolean b;
    public final String c;
    public final boolean d;

    public BeginCompoundOperationRequest(int i, boolean z, String str, boolean z2) {
        this.a = i;
        this.b = z;
        this.c = str;
        this.d = z2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nl.a(this, parcel, i);
    }
}
