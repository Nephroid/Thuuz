package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.VisibleRegion;

/* loaded from: classes.dex */
public class aql implements Parcelable.Creator<VisibleRegion> {
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

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public VisibleRegion createFromParcel(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int b = a.b(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    latLng4 = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 3:
                    latLng3 = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 4:
                    latLng2 = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 5:
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) a.a(parcel, a, LatLngBounds.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }
}
