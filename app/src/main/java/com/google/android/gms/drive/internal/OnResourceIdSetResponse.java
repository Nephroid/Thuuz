package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.lb;
import java.util.List;

/* loaded from: classes.dex */
public class OnResourceIdSetResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnResourceIdSetResponse> CREATOR = new lb();
    private final int a;
    private final List<String> b;

    public OnResourceIdSetResponse(int i, List<String> list) {
        this.a = i;
        this.b = list;
    }

    public int a() {
        return this.a;
    }

    public List<String> b() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        lb.a(this, parcel, i);
    }
}
