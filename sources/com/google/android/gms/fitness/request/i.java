package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.jy;
import defpackage.po;

/* loaded from: classes.dex */
public class i implements SafeParcelable {
    public static final Parcelable.Creator<i> CREATOR = new po();
    private final int a;
    private final String b;

    public i(int i, String str) {
        this.a = i;
        this.b = str;
    }

    private boolean a(i iVar) {
        return jy.a(this.b, iVar.b);
    }

    public String a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof i) && a((i) obj));
    }

    public int hashCode() {
        return jy.a(this.b);
    }

    public String toString() {
        return jy.a(this).a(Utilities.DB_KEY_IMAGE_NAME, this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        po.a(this, parcel, i);
    }
}
