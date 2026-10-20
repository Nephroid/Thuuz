package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.gq;
import defpackage.kb;
import java.util.List;

/* loaded from: classes.dex */
public class AccountChangeEventsResponse implements SafeParcelable {
    public static final gq CREATOR = new gq();
    public final int a;
    public final List<AccountChangeEvent> b;

    public AccountChangeEventsResponse(int i, List<AccountChangeEvent> list) {
        this.a = i;
        this.b = (List) kb.a(list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        gq.a(this, parcel, i);
    }
}
