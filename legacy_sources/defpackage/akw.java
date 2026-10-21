package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.GoogleMapOptions;

/* loaded from: classes.dex */
public class akw {
    public static void a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, googleMapOptions.a());
        ke.a(parcel, 2, googleMapOptions.b());
        ke.a(parcel, 3, googleMapOptions.c());
        ke.a(parcel, 4, googleMapOptions.j());
        ke.a(parcel, 5, (Parcelable) googleMapOptions.k(), i, false);
        ke.a(parcel, 6, googleMapOptions.d());
        ke.a(parcel, 7, googleMapOptions.e());
        ke.a(parcel, 8, googleMapOptions.f());
        ke.a(parcel, 9, googleMapOptions.g());
        ke.a(parcel, 10, googleMapOptions.h());
        ke.a(parcel, 11, googleMapOptions.i());
        ke.a(parcel, a);
    }
}
