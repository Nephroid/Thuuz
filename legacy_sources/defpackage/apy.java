package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.MarkerOptions;

/* loaded from: classes.dex */
public class apy {
    public static void a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, markerOptions.a());
        ke.a(parcel, 2, (Parcelable) markerOptions.c(), i, false);
        ke.a(parcel, 3, markerOptions.d(), false);
        ke.a(parcel, 4, markerOptions.e(), false);
        ke.a(parcel, 5, markerOptions.b(), false);
        ke.a(parcel, 6, markerOptions.f());
        ke.a(parcel, 7, markerOptions.g());
        ke.a(parcel, 8, markerOptions.h());
        ke.a(parcel, 9, markerOptions.i());
        ke.a(parcel, a);
    }
}
