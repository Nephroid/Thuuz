package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aog;
import defpackage.aoi;
import defpackage.aom;
import defpackage.aon;
import defpackage.cw;
import defpackage.jy;
import defpackage.kb;

/* loaded from: classes.dex */
public final class CameraPosition implements SafeParcelable {
    public static final aom CREATOR = new aom();
    public final LatLng a;
    public final float b;
    public final float c;
    public final float d;
    private final int e;

    public CameraPosition(int i, LatLng latLng, float f, float f2, float f3) {
        kb.a(latLng, "null camera target");
        kb.b(0.0f <= f2 && f2 <= 90.0f, "Tilt needs to be between 0 and 90 inclusive");
        this.e = i;
        this.a = latLng;
        this.b = f;
        this.c = f2 + 0.0f;
        this.d = (((double) f3) <= 0.0d ? (f3 % 360.0f) + 360.0f : f3) % 360.0f;
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        this(1, latLng, f, f2, f3);
    }

    public static CameraPosition a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, cw.MapAttrs);
        LatLng latLng = new LatLng(obtainAttributes.hasValue(cw.MapAttrs_cameraTargetLat) ? obtainAttributes.getFloat(cw.MapAttrs_cameraTargetLat, 0.0f) : 0.0f, obtainAttributes.hasValue(cw.MapAttrs_cameraTargetLng) ? obtainAttributes.getFloat(cw.MapAttrs_cameraTargetLng, 0.0f) : 0.0f);
        aoi b = b();
        b.a(latLng);
        if (obtainAttributes.hasValue(cw.MapAttrs_cameraZoom)) {
            b.a(obtainAttributes.getFloat(cw.MapAttrs_cameraZoom, 0.0f));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_cameraBearing)) {
            b.c(obtainAttributes.getFloat(cw.MapAttrs_cameraBearing, 0.0f));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_cameraTilt)) {
            b.b(obtainAttributes.getFloat(cw.MapAttrs_cameraTilt, 0.0f));
        }
        return b.a();
    }

    public static aoi b() {
        return new aoi();
    }

    public int a() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        return this.a.equals(cameraPosition.a) && Float.floatToIntBits(this.b) == Float.floatToIntBits(cameraPosition.b) && Float.floatToIntBits(this.c) == Float.floatToIntBits(cameraPosition.c) && Float.floatToIntBits(this.d) == Float.floatToIntBits(cameraPosition.d);
    }

    public int hashCode() {
        return jy.a(this.a, Float.valueOf(this.b), Float.valueOf(this.c), Float.valueOf(this.d));
    }

    public String toString() {
        return jy.a(this).a("target", this.a).a("zoom", Float.valueOf(this.b)).a("tilt", Float.valueOf(this.c)).a("bearing", Float.valueOf(this.d)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            aon.a(this, parcel, i);
        } else {
            aom.a(this, parcel, i);
        }
    }
}
