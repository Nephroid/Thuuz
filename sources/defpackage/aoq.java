package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class aoq implements Parcelable.Creator<GroundOverlayOptions> {
    public static void a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, groundOverlayOptions.b());
        ke.a(parcel, 2, groundOverlayOptions.a(), false);
        ke.a(parcel, 3, (Parcelable) groundOverlayOptions.c(), i, false);
        ke.a(parcel, 4, groundOverlayOptions.d());
        ke.a(parcel, 5, groundOverlayOptions.e());
        ke.a(parcel, 6, (Parcelable) groundOverlayOptions.f(), i, false);
        ke.a(parcel, 7, groundOverlayOptions.g());
        ke.a(parcel, 8, groundOverlayOptions.h());
        ke.a(parcel, 9, groundOverlayOptions.l());
        ke.a(parcel, 10, groundOverlayOptions.i());
        ke.a(parcel, 11, groundOverlayOptions.j());
        ke.a(parcel, 12, groundOverlayOptions.k());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GroundOverlayOptions createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        IBinder iBinder = null;
        LatLng latLng = null;
        float f = 0.0f;
        float f2 = 0.0f;
        LatLngBounds latLngBounds = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        boolean z = false;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    iBinder = a.p(parcel, a);
                    break;
                case 3:
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 4:
                    f = a.l(parcel, a);
                    break;
                case 5:
                    f2 = a.l(parcel, a);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) a.a(parcel, a, LatLngBounds.CREATOR);
                    break;
                case 7:
                    f3 = a.l(parcel, a);
                    break;
                case 8:
                    f4 = a.l(parcel, a);
                    break;
                case 9:
                    z = a.c(parcel, a);
                    break;
                case 10:
                    f5 = a.l(parcel, a);
                    break;
                case 11:
                    f6 = a.l(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    f7 = a.l(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new GroundOverlayOptions(i, iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GroundOverlayOptions[] newArray(int i) {
        return new GroundOverlayOptions[i];
    }
}
