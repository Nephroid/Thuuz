package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.GroundOverlayOptions;

/* loaded from: classes.dex */
public class aor {
    public static void a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, groundOverlayOptions.b());
        ke.a(parcel, 2, groundOverlayOptions.a(), false);
        ke.a(parcel, 3, (Parcelable) groundOverlayOptions.c(), i, false);
        ke.a(parcel, 4, groundOverlayOptions.d());
        ke.a(parcel, 5, groundOverlayOptions.e());
        ke.a(parcel, 6, (Parcelable) groundOverlayOptions.f(), i, false);
        ke.a(parcel, 7, groundOverlayOptions.g());
        ke.a(parcel, 8, groundOverlayOptions.h());
        ke.a(parcel, 9, groundOverlayOptions.l());
        ke.a(parcel, 10, groundOverlayOptions.i());
        ke.a(parcel, 11, groundOverlayOptions.j());
        ke.a(parcel, 12, groundOverlayOptions.k());
        ke.a(parcel, a);
    }
}
