package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes.dex */
public class akx implements Parcelable.Creator<StreetViewPanoramaOptions> {
    public static void a(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, streetViewPanoramaOptions.a());
        ke.a(parcel, 2, (Parcelable) streetViewPanoramaOptions.g(), i, false);
        ke.a(parcel, 3, streetViewPanoramaOptions.j(), false);
        ke.a(parcel, 4, (Parcelable) streetViewPanoramaOptions.h(), i, false);
        ke.a(parcel, 5, streetViewPanoramaOptions.i(), false);
        ke.a(parcel, 6, streetViewPanoramaOptions.b());
        ke.a(parcel, 7, streetViewPanoramaOptions.c());
        ke.a(parcel, 8, streetViewPanoramaOptions.d());
        ke.a(parcel, 9, streetViewPanoramaOptions.e());
        ke.a(parcel, 10, streetViewPanoramaOptions.f());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOptions createFromParcel(Parcel parcel) {
        Integer num = null;
        byte b = 0;
        int b2 = a.b(parcel);
        byte b3 = 0;
        byte b4 = 0;
        byte b5 = 0;
        byte b6 = 0;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int i = 0;
        while (parcel.dataPosition() < b2) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) a.a(parcel, a, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 5:
                    num = a.h(parcel, a);
                    break;
                case 6:
                    b6 = a.e(parcel, a);
                    break;
                case 7:
                    b5 = a.e(parcel, a);
                    break;
                case 8:
                    b4 = a.e(parcel, a);
                    break;
                case 9:
                    b3 = a.e(parcel, a);
                    break;
                case 10:
                    b = a.e(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b2) {
            throw new a.C0003a("Overread allowed size end=" + b2, parcel);
        }
        return new StreetViewPanoramaOptions(i, streetViewPanoramaCamera, str, latLng, num, b6, b5, b4, b3, b);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOptions[] newArray(int i) {
        return new StreetViewPanoramaOptions[i];
    }
}
