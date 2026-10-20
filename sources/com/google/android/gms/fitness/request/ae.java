package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Subscription;
import defpackage.jy;
import defpackage.pf;

/* loaded from: classes.dex */
public class ae implements SafeParcelable {
    public static final Parcelable.Creator<ae> CREATOR = new pf();
    private final int a;
    private final Subscription b;
    private final boolean c;

    public ae(int i, Subscription subscription, boolean z) {
        this.a = i;
        this.b = subscription;
        this.c = z;
    }

    public Subscription a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return jy.a(this).a("subscription", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pf.a(this, parcel, i);
    }
}
