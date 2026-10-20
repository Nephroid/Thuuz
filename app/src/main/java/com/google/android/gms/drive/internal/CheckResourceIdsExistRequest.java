package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ln;
import java.util.List;

/* loaded from: classes.dex */
public class CheckResourceIdsExistRequest implements SafeParcelable {
    public static final Parcelable.Creator<CheckResourceIdsExistRequest> CREATOR = new ln();
    private final int a;
    private final List<String> b;

    public CheckResourceIdsExistRequest(int i, List<String> list) {
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
        ln.a(this, parcel, i);
    }
}
