package defpackage;

import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes.dex */
public final class aoj {
    public float a;
    public float b;

    public aoj a(float f) {
        this.b = f;
        return this;
    }

    public StreetViewPanoramaOrientation a() {
        return new StreetViewPanoramaOrientation(this.b, this.a);
    }

    public aoj b(float f) {
        this.a = f;
        return this;
    }
}
