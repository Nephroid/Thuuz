package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aog;
import defpackage.aos;
import defpackage.aot;
import defpackage.jy;
import defpackage.kb;

/* loaded from: classes.dex */
public final class LatLngBounds implements SafeParcelable {
    public static final aos CREATOR = new aos();
    public final LatLng a;
    public final LatLng b;
    private final int c;

    public LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        kb.a(latLng, "null southwest");
        kb.a(latLng2, "null northeast");
        kb.b(latLng2.a >= latLng.a, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.a), Double.valueOf(latLng2.a));
        this.c = i;
        this.a = latLng;
        this.b = latLng2;
    }

    public int a() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.a.equals(latLngBounds.a) && this.b.equals(latLngBounds.b);
    }

    public int hashCode() {
        return jy.a(this.a, this.b);
    }

    public String toString() {
        return jy.a(this).a("southwest", this.a).a("northeast", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            aot.a(this, parcel, i);
        } else {
            aos.a(this, parcel, i);
        }
    }
}
