package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import defpackage.ks;

/* loaded from: classes.dex */
public class OnContentsResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnContentsResponse> CREATOR = new ks();
    public final int a;
    public final Contents b;
    public final boolean c;

    public OnContentsResponse(int i, Contents contents, boolean z) {
        this.a = i;
        this.b = contents;
        this.c = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ks.a(this, parcel, i);
    }
}
