package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class apx implements Parcelable.Creator<MarkerOptions> {
    public static void a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, markerOptions.a());
        ke.a(parcel, 2, (Parcelable) markerOptions.c(), i, false);
        ke.a(parcel, 3, markerOptions.d(), false);
        ke.a(parcel, 4, markerOptions.e(), false);
        ke.a(parcel, 5, markerOptions.b(), false);
        ke.a(parcel, 6, markerOptions.f());
        ke.a(parcel, 7, markerOptions.g());
        ke.a(parcel, 8, markerOptions.h());
        ke.a(parcel, 9, markerOptions.i());
        ke.a(parcel, 10, markerOptions.j());
        ke.a(parcel, 11, markerOptions.k());
        ke.a(parcel, 12, markerOptions.l());
        ke.a(parcel, 13, markerOptions.m());
        ke.a(parcel, 14, markerOptions.n());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MarkerOptions createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        float f = 0.0f;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.5f;
        float f5 = 0.0f;
        float f6 = 1.0f;
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
                    str = a.o(parcel, a);
                    break;
                case 4:
                    str2 = a.o(parcel, a);
                    break;
                case 5:
                    iBinder = a.p(parcel, a);
                    break;
                case 6:
                    f = a.l(parcel, a);
                    break;
                case 7:
                    f2 = a.l(parcel, a);
                    break;
                case 8:
                    z = a.c(parcel, a);
                    break;
                case 9:
                    z2 = a.c(parcel, a);
                    break;
                case 10:
                    z3 = a.c(parcel, a);
                    break;
                case 11:
                    f3 = a.l(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    f4 = a.l(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    f5 = a.l(parcel, a);
                    break;
                case 14:
                    f6 = a.l(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new MarkerOptions(i, latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MarkerOptions[] newArray(int i) {
        return new MarkerOptions[i];
    }
}
