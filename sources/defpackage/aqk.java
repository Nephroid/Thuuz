package defpackage;

import android.os.Parcel;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* loaded from: classes.dex */
public class aqk {
    public static void a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, tileOverlayOptions.a());
        ke.a(parcel, 2, tileOverlayOptions.b(), false);
        ke.a(parcel, 3, tileOverlayOptions.d());
        ke.a(parcel, 4, tileOverlayOptions.c());
        ke.a(parcel, a);
    }
}
