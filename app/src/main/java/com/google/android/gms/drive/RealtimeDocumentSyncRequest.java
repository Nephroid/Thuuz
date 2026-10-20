package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kb;
import defpackage.kk;
import java.util.List;

/* loaded from: classes.dex */
public class RealtimeDocumentSyncRequest implements SafeParcelable {
    public static final Parcelable.Creator<RealtimeDocumentSyncRequest> CREATOR = new kk();
    public final int a;
    public final List<String> b;
    public final List<String> c;

    public RealtimeDocumentSyncRequest(int i, List<String> list, List<String> list2) {
        this.a = i;
        this.b = (List) kb.a(list);
        this.c = (List) kb.a(list2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kk.a(this, parcel, i);
    }
}
