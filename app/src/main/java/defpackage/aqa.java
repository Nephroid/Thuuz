package defpackage;

import android.os.Parcel;
import com.google.android.gms.maps.model.PolygonOptions;

/* loaded from: classes.dex */
public class aqa {
    public static void a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, polygonOptions.a());
        ke.c(parcel, 2, polygonOptions.c(), false);
        ke.d(parcel, 3, polygonOptions.b(), false);
        ke.a(parcel, 4, polygonOptions.d());
        ke.a(parcel, 5, polygonOptions.e());
        ke.a(parcel, 6, polygonOptions.f());
        ke.a(parcel, 7, polygonOptions.g());
        ke.a(parcel, 8, polygonOptions.h());
        ke.a(parcel, 9, polygonOptions.i());
        ke.a(parcel, a);
    }
}
