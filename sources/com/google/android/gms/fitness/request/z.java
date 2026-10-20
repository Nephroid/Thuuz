package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.pc;

/* loaded from: classes.dex */
public class z implements SafeParcelable {
    public static final Parcelable.Creator<z> CREATOR = new pc();
    private final int a;
    private final PendingIntent b;

    public z(int i, PendingIntent pendingIntent) {
        this.a = i;
        this.b = pendingIntent;
    }

    private boolean a(z zVar) {
        return jy.a(this.b, zVar.b);
    }

    public PendingIntent a() {
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
        return this == obj || ((obj instanceof z) && a((z) obj));
    }

    public int hashCode() {
        return jy.a(this.b);
    }

    public String toString() {
        return jy.a(this).a("pendingIntent", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pc.a(this, parcel, i);
    }
}
