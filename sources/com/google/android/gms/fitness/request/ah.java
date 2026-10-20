package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import defpackage.jy;
import defpackage.ph;

/* loaded from: classes.dex */
public class ah implements SafeParcelable {
    public static final Parcelable.Creator<ah> CREATOR = new ph();
    private final int a;
    private final DataType b;
    private final DataSource c;

    public ah(int i, DataType dataType, DataSource dataSource) {
        this.a = i;
        this.b = dataType;
        this.c = dataSource;
    }

    private boolean a(ah ahVar) {
        return jy.a(this.c, ahVar.c) && jy.a(this.b, ahVar.b);
    }

    public int a() {
        return this.a;
    }

    public DataType b() {
        return this.b;
    }

    public DataSource c() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ah) && a((ah) obj));
    }

    public int hashCode() {
        return jy.a(this.c, this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ph.a(this, parcel, i);
    }
}
