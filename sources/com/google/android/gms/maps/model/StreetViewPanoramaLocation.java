package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aqf;
import defpackage.jy;

/* loaded from: classes.dex */
public class StreetViewPanoramaLocation implements SafeParcelable {
    public static final aqf CREATOR = new aqf();
    public final StreetViewPanoramaLink[] a;
    public final LatLng b;
    public final String c;
    private final int d;

    public StreetViewPanoramaLocation(int i, StreetViewPanoramaLink[] streetViewPanoramaLinkArr, LatLng latLng, String str) {
        this.d = i;
        this.a = streetViewPanoramaLinkArr;
        this.b = latLng;
        this.c = str;
    }

    public int a() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
        return this.c.equals(streetViewPanoramaLocation.c) && this.b.equals(streetViewPanoramaLocation.b);
    }

    public int hashCode() {
        return jy.a(this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a("panoId", this.c).a("position", this.b.toString()).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aqf.a(this, parcel, i);
    }
}
