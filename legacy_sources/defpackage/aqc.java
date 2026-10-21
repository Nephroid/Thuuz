package defpackage;

import android.os.Parcel;
import com.google.android.gms.maps.model.PolylineOptions;

/* loaded from: classes.dex */
public class aqc {
    public static void a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, polylineOptions.a());
        ke.c(parcel, 2, polylineOptions.b(), false);
        ke.a(parcel, 3, polylineOptions.c());
        ke.a(parcel, 4, polylineOptions.d());
        ke.a(parcel, 5, polylineOptions.e());
        ke.a(parcel, 6, polylineOptions.f());
        ke.a(parcel, 7, polylineOptions.g());
        ke.a(parcel, a);
    }
}
