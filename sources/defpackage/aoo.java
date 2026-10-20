package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
public class aoo implements Parcelable.Creator<CircleOptions> {
    public static void a(CircleOptions circleOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, circleOptions.a());
        ke.a(parcel, 2, (Parcelable) circleOptions.b(), i, false);
        ke.a(parcel, 3, circleOptions.c());
        ke.a(parcel, 4, circleOptions.d());
        ke.a(parcel, 5, circleOptions.e());
        ke.a(parcel, 6, circleOptions.f());
        ke.a(parcel, 7, circleOptions.g());
        ke.a(parcel, 8, circleOptions.h());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CircleOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = a.b(parcel);
        LatLng latLng = null;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 3:
                    d = a.m(parcel, a);
                    break;
                case 4:
                    f2 = a.l(parcel, a);
                    break;
                case 5:
                    i2 = a.g(parcel, a);
                    break;
                case 6:
                    i = a.g(parcel, a);
                    break;
                case 7:
                    f = a.l(parcel, a);
                    break;
                case 8:
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
        return new CircleOptions(i3, latLng, d, f2, i2, i, f, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}
