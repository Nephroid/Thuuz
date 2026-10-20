package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
public class aos implements Parcelable.Creator<LatLngBounds> {
    public static void a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, latLngBounds.a());
        ke.a(parcel, 2, (Parcelable) latLngBounds.a, i, false);
        ke.a(parcel, 3, (Parcelable) latLngBounds.b, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLngBounds createFromParcel(Parcel parcel) {
        LatLng latLng;
        LatLng latLng2;
        int i;
        LatLng latLng3 = null;
        int b = a.b(parcel);
        int i2 = 0;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    LatLng latLng5 = latLng3;
                    latLng2 = latLng4;
                    i = a.g(parcel, a);
                    latLng = latLng5;
                    break;
                case 2:
                    LatLng latLng6 = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    i = i2;
                    latLng = latLng3;
                    latLng2 = latLng6;
                    break;
                case 3:
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    latLng2 = latLng4;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    latLng = latLng3;
                    latLng2 = latLng4;
                    i = i2;
                    break;
            }
            i2 = i;
            latLng4 = latLng2;
            latLng3 = latLng;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new LatLngBounds(i2, latLng4, latLng3);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLngBounds[] newArray(int i) {
        return new LatLngBounds[i];
    }
}
