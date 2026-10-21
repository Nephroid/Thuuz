package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class aqb implements Parcelable.Creator<PolylineOptions> {
    public static void a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, polylineOptions.a());
        ke.c(parcel, 2, polylineOptions.b(), false);
        ke.a(parcel, 3, polylineOptions.c());
        ke.a(parcel, 4, polylineOptions.d());
        ke.a(parcel, 5, polylineOptions.e());
        ke.a(parcel, 6, polylineOptions.f());
        ke.a(parcel, 7, polylineOptions.g());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PolylineOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = a.b(parcel);
        ArrayList arrayList = null;
        boolean z2 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    arrayList = a.c(parcel, a, LatLng.CREATOR);
                    break;
                case 3:
                    f2 = a.l(parcel, a);
                    break;
                case 4:
                    i = a.g(parcel, a);
                    break;
                case 5:
                    f = a.l(parcel, a);
                    break;
                case 6:
                    z2 = a.c(parcel, a);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new PolylineOptions(i2, arrayList, f2, i, f, z2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PolylineOptions[] newArray(int i) {
        return new PolylineOptions[i];
    }
}
