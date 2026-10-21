package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
public class aom implements Parcelable.Creator<CameraPosition> {
    public static void a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, cameraPosition.a());
        ke.a(parcel, 2, (Parcelable) cameraPosition.a, i, false);
        ke.a(parcel, 3, cameraPosition.b);
        ke.a(parcel, 4, cameraPosition.c);
        ke.a(parcel, 5, cameraPosition.d);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CameraPosition createFromParcel(Parcel parcel) {
        float f = 0.0f;
        int b = a.b(parcel);
        int i = 0;
        LatLng latLng = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 3:
                    f3 = a.l(parcel, a);
                    break;
                case 4:
                    f2 = a.l(parcel, a);
                    break;
                case 5:
                    f = a.l(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CameraPosition(i, latLng, f3, f2, f);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }
}
