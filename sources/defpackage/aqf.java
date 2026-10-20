package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaLink;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

/* loaded from: classes.dex */
public class aqf implements Parcelable.Creator<StreetViewPanoramaLocation> {
    public static void a(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, streetViewPanoramaLocation.a());
        ke.a(parcel, 2, (Parcelable[]) streetViewPanoramaLocation.a, i, false);
        ke.a(parcel, 3, (Parcelable) streetViewPanoramaLocation.b, i, false);
        ke.a(parcel, 4, streetViewPanoramaLocation.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaLocation createFromParcel(Parcel parcel) {
        String o;
        LatLng latLng;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr;
        int i;
        String str = null;
        int b = a.b(parcel);
        int i2 = 0;
        LatLng latLng2 = null;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    String str2 = str;
                    latLng = latLng2;
                    streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
                    i = a.g(parcel, a);
                    o = str2;
                    break;
                case 2:
                    i = i2;
                    LatLng latLng3 = latLng2;
                    streetViewPanoramaLinkArr = (StreetViewPanoramaLink[]) a.b(parcel, a, StreetViewPanoramaLink.CREATOR);
                    o = str;
                    latLng = latLng3;
                    break;
                case 3:
                    streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
                    i = i2;
                    String str3 = str;
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    o = str3;
                    break;
                case 4:
                    o = a.o(parcel, a);
                    latLng = latLng2;
                    streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    o = str;
                    latLng = latLng2;
                    streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
                    i = i2;
                    break;
            }
            i2 = i;
            streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
            latLng2 = latLng;
            str = o;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new StreetViewPanoramaLocation(i2, streetViewPanoramaLinkArr2, latLng2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaLocation[] newArray(int i) {
        return new StreetViewPanoramaLocation[i];
    }
}
