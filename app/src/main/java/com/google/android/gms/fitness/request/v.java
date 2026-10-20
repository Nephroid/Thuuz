package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import defpackage.jy;
import defpackage.py;

/* loaded from: classes.dex */
public class v implements SafeParcelable {
    public static final Parcelable.Creator<v> CREATOR = new py();
    private final int a;
    private final Session b;

    public v(int i, Session session) {
        this.a = i;
        this.b = session;
    }

    private boolean a(v vVar) {
        return jy.a(this.b, vVar.b);
    }

    public Session a() {
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
        return obj == this || ((obj instanceof v) && a((v) obj));
    }

    public int hashCode() {
        return jy.a(this.b);
    }

    public String toString() {
        return jy.a(this).a("session", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        py.a(this, parcel, i);
    }
}
