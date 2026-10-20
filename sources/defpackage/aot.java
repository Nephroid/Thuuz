package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
public class aot {
    public static void a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, latLngBounds.a());
        ke.a(parcel, 2, (Parcelable) latLngBounds.a, i, false);
        ke.a(parcel, 3, (Parcelable) latLngBounds.b, i, false);
        ke.a(parcel, a);
    }
}
