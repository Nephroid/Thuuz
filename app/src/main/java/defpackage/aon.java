package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.CameraPosition;

/* loaded from: classes.dex */
public class aon {
    public static void a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, cameraPosition.a());
        ke.a(parcel, 2, (Parcelable) cameraPosition.a, i, false);
        ke.a(parcel, 3, cameraPosition.b);
        ke.a(parcel, 4, cameraPosition.c);
        ke.a(parcel, 5, cameraPosition.d);
        ke.a(parcel, a);
    }
}
