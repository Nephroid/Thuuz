package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.h;

/* loaded from: classes.dex */
public class aqz implements Parcelable.Creator<h> {
    public static void a(h hVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, hVar.b(), false);
        ke.a(parcel, 1000, hVar.a());
        ke.a(parcel, 2, hVar.c(), false);
        ke.a(parcel, 3, hVar.d(), false);
        ke.a(parcel, 4, hVar.e(), false);
        ke.a(parcel, 5, hVar.f(), false);
        ke.a(parcel, 6, hVar.g(), false);
        ke.a(parcel, 7, hVar.h(), false);
        ke.a(parcel, 8, hVar.i(), false);
        ke.a(parcel, 9, (Parcelable) hVar.j(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public h createFromParcel(Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str5 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str5 = a.o(parcel, a);
                    break;
                case 2:
                    strArr3 = a.A(parcel, a);
                    break;
                case 3:
                    strArr2 = a.A(parcel, a);
                    break;
                case 4:
                    strArr = a.A(parcel, a);
                    break;
                case 5:
                    str4 = a.o(parcel, a);
                    break;
                case 6:
                    str3 = a.o(parcel, a);
                    break;
                case 7:
                    str2 = a.o(parcel, a);
                    break;
                case 8:
                    str = a.o(parcel, a);
                    break;
                case 9:
                    plusCommonExtras = (PlusCommonExtras) a.a(parcel, a, PlusCommonExtras.CREATOR);
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
        return new h(i, str5, strArr3, strArr2, strArr, str4, str3, str2, str, plusCommonExtras);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public h[] newArray(int i) {
        return new h[i];
    }
}
