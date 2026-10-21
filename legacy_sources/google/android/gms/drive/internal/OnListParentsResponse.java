package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.i;
import defpackage.ky;

/* loaded from: classes.dex */
public class OnListParentsResponse extends i implements SafeParcelable {
    public static final Parcelable.Creator<OnListParentsResponse> CREATOR = new ky();
    public final int a;
    public final DataHolder b;

    public OnListParentsResponse(int i, DataHolder dataHolder) {
        this.a = i;
        this.b = dataHolder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.i
    public void a(Parcel parcel, int i) {
        ky.a(this, parcel, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
