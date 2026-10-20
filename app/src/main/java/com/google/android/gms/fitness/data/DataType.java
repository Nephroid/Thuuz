package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.oo;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class DataType implements SafeParcelable {
    public static final Parcelable.Creator<DataType> CREATOR = new oo();
    private final int a;
    private final String b;
    private final List<Field> c;

    public DataType(int i, String str, List<Field> list) {
        this.a = i;
        this.b = str;
        this.c = Collections.unmodifiableList(list);
    }

    private boolean a(DataType dataType) {
        return this.b.equals(dataType.b) && this.c.equals(dataType.c);
    }

    public String a() {
        return this.b;
    }

    public List<Field> b() {
        return this.c;
    }

    public String c() {
        return this.b.startsWith("com.google.") ? this.b.substring(11) : this.b;
    }

    public int d() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof DataType) && a((DataType) obj));
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        return String.format("DataType{%s%s}", this.b, this.c);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        oo.a(this, parcel, i);
    }
}
