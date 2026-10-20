package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.i;
import defpackage.kx;

/* loaded from: classes.dex */
public class OnListEntriesResponse extends i implements SafeParcelable {
    public static final Parcelable.Creator<OnListEntriesResponse> CREATOR = new kx();
    public final int a;
    public final DataHolder b;
    public final boolean c;

    public OnListEntriesResponse(int i, DataHolder dataHolder, boolean z) {
        this.a = i;
        this.b = dataHolder;
        this.c = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.i
    public void a(Parcel parcel, int i) {
        kx.a(this, parcel, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
