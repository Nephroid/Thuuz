package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.dj;

/* loaded from: classes.dex */
public class xl implements Parcelable.Creator<dj> {
    public static void a(dj djVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, djVar.a);
        ke.a(parcel, 2, djVar.b, false);
        ke.a(parcel, 3, djVar.c, false);
        ke.a(parcel, 4, djVar.d, false);
        ke.a(parcel, 5, djVar.e, false);
        ke.a(parcel, 6, djVar.f, false);
        ke.a(parcel, 7, djVar.g, false);
        ke.a(parcel, 8, djVar.h, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public dj createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str7 = a.o(parcel, a);
                    break;
                case 3:
                    str6 = a.o(parcel, a);
                    break;
                case 4:
                    str5 = a.o(parcel, a);
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
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new dj(i, str7, str6, str5, str4, str3, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public dj[] newArray(int i) {
        return new dj[i];
    }
}
