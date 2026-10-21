package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
public class aou implements Parcelable.Creator<LatLng> {
    public static void a(LatLng latLng, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, latLng.a());
        ke.a(parcel, 2, latLng.a);
        ke.a(parcel, 3, latLng.b);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLng createFromParcel(Parcel parcel) {
        double d = 0.0d;
        int b = a.b(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    d2 = a.m(parcel, a);
                    break;
                case 3:
                    d = a.m(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new LatLng(i, d2, d);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
