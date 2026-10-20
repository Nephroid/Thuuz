package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ahx;
import java.util.List;

/* loaded from: classes.dex */
public class me implements SafeParcelable {
    public static final ahx CREATOR = new ahx();
    public final int a;
    private final boolean b;
    private final List<mo> c;

    public me(int i, boolean z, List<mo> list) {
        this.a = i;
        this.b = z;
        this.c = list;
    }

    public boolean a() {
        return this.b;
    }

    public List<mo> b() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        ahx ahxVar = CREATOR;
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ahx ahxVar = CREATOR;
        ahx.a(this, parcel, i);
    }
}
