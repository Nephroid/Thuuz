package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import defpackage.lp;

/* loaded from: classes.dex */
public class CloseContentsRequest implements SafeParcelable {
    public static final Parcelable.Creator<CloseContentsRequest> CREATOR = new lp();
    public final int a;
    public final Contents b;
    public final Boolean c;

    public CloseContentsRequest(int i, Contents contents, Boolean bool) {
        this.a = i;
        this.b = contents;
        this.c = bool;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lp.a(this, parcel, i);
    }
}
