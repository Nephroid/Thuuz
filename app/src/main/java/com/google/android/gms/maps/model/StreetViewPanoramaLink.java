package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aqe;
import defpackage.jy;

/* loaded from: classes.dex */
public class StreetViewPanoramaLink implements SafeParcelable {
    public static final aqe CREATOR = new aqe();
    public final String a;
    public final float b;
    private final int c;

    public StreetViewPanoramaLink(int i, String str, float f) {
        this.c = i;
        this.a = str;
        this.b = (((double) f) <= 0.0d ? (f % 360.0f) + 360.0f : f) % 360.0f;
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
        if (!(obj instanceof StreetViewPanoramaLink)) {
            return false;
        }
        StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink) obj;
        return this.a.equals(streetViewPanoramaLink.a) && Float.floatToIntBits(this.b) == Float.floatToIntBits(streetViewPanoramaLink.b);
    }

    public int hashCode() {
        return jy.a(this.a, Float.valueOf(this.b));
    }

    public String toString() {
        return jy.a(this).a("panoId", this.a).a("bearing", Float.valueOf(this.b)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aqe.a(this, parcel, i);
    }
}
