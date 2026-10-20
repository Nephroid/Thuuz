package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.oy;

/* loaded from: classes.dex */
public class q implements SafeParcelable {
    public static final Parcelable.Creator<q> CREATOR = new oy();
    public final int a;
    private final Session b;
    private final DataSet c;

    public q(int i, Session session, DataSet dataSet) {
        this.a = i;
        this.b = session;
        this.c = dataSet;
    }

    private boolean a(q qVar) {
        return jy.a(this.b, qVar.b) && jy.a(this.c, qVar.c);
    }

    public Session a() {
        return this.b;
    }

    public DataSet b() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof q) && a((q) obj));
    }

    public int hashCode() {
        return jy.a(this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a("session", this.b).a("dataSet", this.c).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        oy.a(this, parcel, i);
    }
}
