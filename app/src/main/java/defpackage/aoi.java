package defpackage;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
public final class aoi {
    private LatLng a;
    private float b;
    private float c;
    private float d;

    public aoi a(float f) {
        this.b = f;
        return this;
    }

    public aoi a(LatLng latLng) {
        this.a = latLng;
        return this;
    }

    public CameraPosition a() {
        return new CameraPosition(this.a, this.b, this.c, this.d);
    }

    public aoi b(float f) {
        this.c = f;
        return this;
    }

    public aoi c(float f) {
        this.d = f;
        return this;
    }
}
