package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.mo;
import com.google.android.gms.internal.mq;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class aid implements Parcelable.Creator<mq> {
    public static void a(mq mqVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, mqVar.a(), false);
        ke.a(parcel, 1000, mqVar.a);
        ke.a(parcel, 2, (Parcelable) mqVar.b(), i, false);
        ke.a(parcel, 3, mqVar.c(), false);
        ke.c(parcel, 4, mqVar.d(), false);
        ke.a(parcel, 5, mqVar.e(), false);
        ke.a(parcel, 6, mqVar.f(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mq createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        int i = 0;
        String str2 = null;
        ArrayList arrayList = null;
        String str3 = null;
        LatLng latLng = null;
        String str4 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str4 = a.o(parcel, a);
                    break;
                case 2:
                    latLng = (LatLng) a.a(parcel, a, LatLng.CREATOR);
                    break;
                case 3:
                    str3 = a.o(parcel, a);
                    break;
                case 4:
                    arrayList = a.c(parcel, a, mo.CREATOR);
                    break;
                case 5:
                    str2 = a.o(parcel, a);
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
        return new mq(i, str4, latLng, str3, arrayList, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mq[] newArray(int i) {
        return new mq[i];
    }
}
