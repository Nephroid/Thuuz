package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.CircleOptions;

/* loaded from: classes.dex */
public class aop {
    public static void a(CircleOptions circleOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, circleOptions.a());
        ke.a(parcel, 2, (Parcelable) circleOptions.b(), i, false);
        ke.a(parcel, 3, circleOptions.c());
        ke.a(parcel, 4, circleOptions.d());
        ke.a(parcel, 5, circleOptions.e());
        ke.a(parcel, 6, circleOptions.f());
        ke.a(parcel, 7, circleOptions.g());
        ke.a(parcel, 8, circleOptions.h());
        ke.a(parcel, a);
    }
}
