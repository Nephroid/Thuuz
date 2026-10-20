package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.CameraPosition;
import defpackage.akv;
import defpackage.akw;
import defpackage.amc;
import defpackage.aog;
import defpackage.cw;

/* loaded from: classes.dex */
public final class GoogleMapOptions implements SafeParcelable {
    public static final akv CREATOR = new akv();
    private final int a;
    private Boolean b;
    private Boolean c;
    private int d;
    private CameraPosition e;
    private Boolean f;
    private Boolean g;
    private Boolean h;
    private Boolean i;
    private Boolean j;
    private Boolean k;

    public GoogleMapOptions() {
        this.d = -1;
        this.a = 1;
    }

    public GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        this.d = -1;
        this.a = i;
        this.b = amc.a(b);
        this.c = amc.a(b2);
        this.d = i2;
        this.e = cameraPosition;
        this.f = amc.a(b3);
        this.g = amc.a(b4);
        this.h = amc.a(b5);
        this.i = amc.a(b6);
        this.j = amc.a(b7);
        this.k = amc.a(b8);
    }

    public static GoogleMapOptions a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, cw.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(cw.MapAttrs_mapType)) {
            googleMapOptions.a(obtainAttributes.getInt(cw.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_zOrderOnTop)) {
            googleMapOptions.a(obtainAttributes.getBoolean(cw.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_useViewLifecycle)) {
            googleMapOptions.b(obtainAttributes.getBoolean(cw.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_uiCompass)) {
            googleMapOptions.d(obtainAttributes.getBoolean(cw.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_uiRotateGestures)) {
            googleMapOptions.h(obtainAttributes.getBoolean(cw.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_uiScrollGestures)) {
            googleMapOptions.e(obtainAttributes.getBoolean(cw.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_uiTiltGestures)) {
            googleMapOptions.g(obtainAttributes.getBoolean(cw.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_uiZoomGestures)) {
            googleMapOptions.f(obtainAttributes.getBoolean(cw.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(cw.MapAttrs_uiZoomControls)) {
            googleMapOptions.c(obtainAttributes.getBoolean(cw.MapAttrs_uiZoomControls, true));
        }
        googleMapOptions.a(CameraPosition.a(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public int a() {
        return this.a;
    }

    public GoogleMapOptions a(int i) {
        this.d = i;
        return this;
    }

    public GoogleMapOptions a(CameraPosition cameraPosition) {
        this.e = cameraPosition;
        return this;
    }

    public GoogleMapOptions a(boolean z) {
        this.b = Boolean.valueOf(z);
        return this;
    }

    public byte b() {
        return amc.a(this.b);
    }

    public GoogleMapOptions b(boolean z) {
        this.c = Boolean.valueOf(z);
        return this;
    }

    public byte c() {
        return amc.a(this.c);
    }

    public GoogleMapOptions c(boolean z) {
        this.f = Boolean.valueOf(z);
        return this;
    }

    public byte d() {
        return amc.a(this.f);
    }

    public GoogleMapOptions d(boolean z) {
        this.g = Boolean.valueOf(z);
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte e() {
        return amc.a(this.g);
    }

    public GoogleMapOptions e(boolean z) {
        this.h = Boolean.valueOf(z);
        return this;
    }

    public byte f() {
        return amc.a(this.h);
    }

    public GoogleMapOptions f(boolean z) {
        this.i = Boolean.valueOf(z);
        return this;
    }

    public byte g() {
        return amc.a(this.i);
    }

    public GoogleMapOptions g(boolean z) {
        this.j = Boolean.valueOf(z);
        return this;
    }

    public byte h() {
        return amc.a(this.j);
    }

    public GoogleMapOptions h(boolean z) {
        this.k = Boolean.valueOf(z);
        return this;
    }

    public byte i() {
        return amc.a(this.k);
    }

    public int j() {
        return this.d;
    }

    public CameraPosition k() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (aog.a()) {
            akw.a(this, parcel, i);
        } else {
            akv.a(this, parcel, i);
        }
    }
}
