package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.StreetViewPanoramaLink;

/* loaded from: classes.dex */
public class aqe implements Parcelable.Creator<StreetViewPanoramaLink> {
    public static void a(StreetViewPanoramaLink streetViewPanoramaLink, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, streetViewPanoramaLink.a());
        ke.a(parcel, 2, streetViewPanoramaLink.a, false);
        ke.a(parcel, 3, streetViewPanoramaLink.b);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaLink createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        float f = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
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
        return new StreetViewPanoramaLink(i, str, f);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaLink[] newArray(int i) {
        return new StreetViewPanoramaLink[i];
    }
}
