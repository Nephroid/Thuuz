package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aqg;
import defpackage.jy;
import defpackage.kb;

/* loaded from: classes.dex */
public class StreetViewPanoramaOrientation implements SafeParcelable {
    public static final aqg CREATOR = new aqg();
    public final float a;
    public final float b;
    private final int c;

    public StreetViewPanoramaOrientation(float f, float f2) {
        this(1, f, f2);
    }

    public StreetViewPanoramaOrientation(int i, float f, float f2) {
        kb.b(-90.0f <= f && f <= 90.0f, "Tilt needs to be between -90 and 90 inclusive");
        this.c = i;
        this.a = 0.0f + f;
        this.b = (((double) f2) <= 0.0d ? (f2 % 360.0f) + 360.0f : f2) % 360.0f;
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
        if (!(obj instanceof StreetViewPanoramaOrientation)) {
            return false;
        }
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) obj;
        return Float.floatToIntBits(this.a) == Float.floatToIntBits(streetViewPanoramaOrientation.a) && Float.floatToIntBits(this.b) == Float.floatToIntBits(streetViewPanoramaOrientation.b);
    }

    public int hashCode() {
        return jy.a(Float.valueOf(this.a), Float.valueOf(this.b));
    }

    public String toString() {
        return jy.a(this).a("tilt", Float.valueOf(this.a)).a("bearing", Float.valueOf(this.b)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aqg.a(this, parcel, i);
    }
}
