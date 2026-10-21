package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class apz implements Parcelable.Creator<PolygonOptions> {
    public static void a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, polygonOptions.a());
        ke.c(parcel, 2, polygonOptions.c(), false);
        ke.d(parcel, 3, polygonOptions.b(), false);
        ke.a(parcel, 4, polygonOptions.d());
        ke.a(parcel, 5, polygonOptions.e());
        ke.a(parcel, 6, polygonOptions.f());
        ke.a(parcel, 7, polygonOptions.g());
        ke.a(parcel, 8, polygonOptions.h());
        ke.a(parcel, 9, polygonOptions.i());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PolygonOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = a.b(parcel);
        ArrayList arrayList = null;
        ArrayList arrayList2 = new ArrayList();
        boolean z2 = false;
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
                    arrayList = a.c(parcel, a, LatLng.CREATOR);
                    break;
                case 3:
                    a.a(parcel, a, arrayList2, getClass().getClassLoader());
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
                    z2 = a.c(parcel, a);
                    break;
                case 9:
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
        return new PolygonOptions(i3, arrayList, arrayList2, f2, i2, i, f, z2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PolygonOptions[] newArray(int i) {
        return new PolygonOptions[i];
    }
}
