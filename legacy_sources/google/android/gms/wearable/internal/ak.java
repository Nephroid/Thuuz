package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avq;

/* loaded from: classes.dex */
public class ak implements SafeParcelable {
    public static final Parcelable.Creator<ak> CREATOR = new avq();
    public final int a;
    private final String b;
    private final String c;

    public ak(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ak)) {
            return false;
        }
        ak akVar = (ak) obj;
        return akVar.b.equals(this.b) && akVar.c.equals(this.c);
    }

    public int hashCode() {
        return ((this.b.hashCode() + 629) * 37) + this.c.hashCode();
    }

    public String toString() {
        return "NodeParcelable{" + this.b + "," + this.c + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avq.a(this, parcel, i);
    }
}
