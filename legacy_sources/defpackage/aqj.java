package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* loaded from: classes.dex */
public class aqj implements Parcelable.Creator<TileOverlayOptions> {
    public static void a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, tileOverlayOptions.a());
        ke.a(parcel, 2, tileOverlayOptions.b(), false);
        ke.a(parcel, 3, tileOverlayOptions.d());
        ke.a(parcel, 4, tileOverlayOptions.c());
        ke.a(parcel, 5, tileOverlayOptions.e());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TileOverlayOptions createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        IBinder iBinder = null;
        float f = 0.0f;
        boolean z2 = true;
        int i = 0;
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
                    z = a.c(parcel, a);
                    break;
                case 4:
                    f = a.l(parcel, a);
                    break;
                case 5:
                    z2 = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new TileOverlayOptions(i, iBinder, z, f, z2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TileOverlayOptions[] newArray(int i) {
        return new TileOverlayOptions[i];
    }
}
