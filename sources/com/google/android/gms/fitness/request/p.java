package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.or;
import defpackage.os;
import defpackage.pu;

/* loaded from: classes.dex */
public class p implements SafeParcelable {
    public static final Parcelable.Creator<p> CREATOR = new pu();
    private final int a;
    private final or b;
    private final PendingIntent c;

    public p(int i, IBinder iBinder, PendingIntent pendingIntent) {
        this.a = i;
        this.b = iBinder == null ? null : os.a(iBinder);
        this.c = pendingIntent;
    }

    public PendingIntent a() {
        return this.c;
    }

    public int b() {
        return this.a;
    }

    public IBinder c() {
        if (this.b == null) {
            return null;
        }
        return this.b.asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("SensorUnregistrationRequest{%s}", this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pu.a(this, parcel, i);
    }
}
