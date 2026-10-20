package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.oq;

/* loaded from: classes.dex */
public final class Field implements SafeParcelable {
    public static final Parcelable.Creator<Field> CREATOR = new oq();
    private final int a;
    private final String b;
    private final int c;

    public Field(int i, String str, int i2) {
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    private boolean a(Field field) {
        return this.b.equals(field.b) && this.c == field.c;
    }

    public String a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Field) && a((Field) obj));
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.b;
        objArr[1] = this.c == 1 ? "i" : "f";
        return String.format("%s(%s)", objArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        oq.a(this, parcel, i);
    }
}
