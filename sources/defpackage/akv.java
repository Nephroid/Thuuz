package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;

/* loaded from: classes.dex */
public class akv implements Parcelable.Creator<GoogleMapOptions> {
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

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GoogleMapOptions createFromParcel(Parcel parcel) {
        byte b = 0;
        int b2 = a.b(parcel);
        CameraPosition cameraPosition = null;
        byte b3 = 0;
        byte b4 = 0;
        byte b5 = 0;
        byte b6 = 0;
        byte b7 = 0;
        int i = 0;
        byte b8 = 0;
        byte b9 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < b2) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    b9 = a.e(parcel, a);
                    break;
                case 3:
                    b8 = a.e(parcel, a);
                    break;
                case 4:
                    i = a.g(parcel, a);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) a.a(parcel, a, CameraPosition.CREATOR);
                    break;
                case 6:
                    b7 = a.e(parcel, a);
                    break;
                case 7:
                    b6 = a.e(parcel, a);
                    break;
                case 8:
                    b5 = a.e(parcel, a);
                    break;
                case 9:
                    b4 = a.e(parcel, a);
                    break;
                case 10:
                    b3 = a.e(parcel, a);
                    break;
                case 11:
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
        return new GoogleMapOptions(i2, b9, b8, i, cameraPosition, b7, b6, b5, b4, b3, b);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GoogleMapOptions[] newArray(int i) {
        return new GoogleMapOptions[i];
    }
}
