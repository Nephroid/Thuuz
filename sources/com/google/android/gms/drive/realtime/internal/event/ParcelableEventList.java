package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.np;
import java.util.List;

/* loaded from: classes.dex */
public class ParcelableEventList implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableEventList> CREATOR = new np();
    public final int a;
    public final List<ParcelableEvent> b;
    public final DataHolder c;
    public final boolean d;
    public final List<String> e;

    public ParcelableEventList(int i, List<ParcelableEvent> list, DataHolder dataHolder, boolean z, List<String> list2) {
        this.a = i;
        this.b = list;
        this.c = dataHolder;
        this.d = z;
        this.e = list2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        np.a(this, parcel, i);
    }
}
