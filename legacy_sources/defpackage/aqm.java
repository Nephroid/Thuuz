package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.VisibleRegion;

/* loaded from: classes.dex */
public class aqm {
    public static void a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, visibleRegion.a());
        ke.a(parcel, 2, (Parcelable) visibleRegion.a, i, false);
        ke.a(parcel, 3, (Parcelable) visibleRegion.b, i, false);
        ke.a(parcel, 4, (Parcelable) visibleRegion.c, i, false);
        ke.a(parcel, 5, (Parcelable) visibleRegion.d, i, false);
        ke.a(parcel, 6, (Parcelable) visibleRegion.e, i, false);
        ke.a(parcel, a);
    }
}
