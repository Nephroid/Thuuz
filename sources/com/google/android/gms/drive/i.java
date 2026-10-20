package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.kb;

/* loaded from: classes.dex */
public abstract class i implements Parcelable {
    private volatile transient boolean a = false;

    public abstract void a(Parcel parcel, int i);

    public final boolean a() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kb.a(!a());
        this.a = true;
        a(parcel, i);
    }
}
