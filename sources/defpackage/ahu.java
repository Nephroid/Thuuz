package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.lr;
import com.google.android.gms.internal.lz;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

/* loaded from: classes.dex */
public class ahu implements Parcelable.Creator<lz> {
    public static void a(lz lzVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) lzVar.b, i, false);
        ke.a(parcel, 1000, lzVar.a());
        ke.a(parcel, 2, lzVar.c);
        ke.a(parcel, 3, lzVar.d);
        ke.a(parcel, 4, lzVar.e);
        ke.c(parcel, 5, lzVar.f, false);
        ke.a(parcel, 6, lzVar.g, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public lz createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = true;
        boolean z2 = false;
        int b = a.b(parcel);
        List<lr> list = lz.a;
        boolean z3 = true;
        LocationRequest locationRequest = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    locationRequest = (LocationRequest) a.a(parcel, a, LocationRequest.CREATOR);
                    break;
                case 2:
                    z2 = a.c(parcel, a);
                    break;
                case 3:
                    z3 = a.c(parcel, a);
                    break;
                case 4:
                    z = a.c(parcel, a);
                    break;
                case 5:
                    list = a.c(parcel, a, lr.CREATOR);
                    break;
                case 6:
                    str = a.o(parcel, a);
                    break;
                case 1000:
                    i = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new lz(i, locationRequest, z2, z3, z, list, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public lz[] newArray(int i) {
        return new lz[i];
    }
}
