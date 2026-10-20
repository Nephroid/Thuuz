package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hq;

/* loaded from: classes.dex */
public class aer implements Parcelable.Creator<hq> {
    public static void a(hq hqVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, hqVar.b, false);
        ke.a(parcel, 1000, hqVar.a);
        ke.a(parcel, 2, hqVar.c, false);
        ke.a(parcel, 3, hqVar.d);
        ke.a(parcel, 4, hqVar.e);
        ke.a(parcel, 5, hqVar.f);
        ke.a(parcel, 6, hqVar.g, false);
        ke.a(parcel, 7, (Parcelable[]) hqVar.h, i, false);
        ke.a(parcel, 8, hqVar.i, false);
        ke.a(parcel, 11, hqVar.j, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hq createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int b = a.b(parcel);
        int i = 1;
        int[] iArr = null;
        hk[] hkVarArr = null;
        String str2 = null;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str4 = a.o(parcel, a);
                    break;
                case 2:
                    str3 = a.o(parcel, a);
                    break;
                case 3:
                    z2 = a.c(parcel, a);
                    break;
                case 4:
                    i = a.g(parcel, a);
                    break;
                case 5:
                    z = a.c(parcel, a);
                    break;
                case 6:
                    str2 = a.o(parcel, a);
                    break;
                case 7:
                    hkVarArr = (hk[]) a.b(parcel, a, hk.CREATOR);
                    break;
                case 8:
                    iArr = a.u(parcel, a);
                    break;
                case 11:
                    str = a.o(parcel, a);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new hq(i2, str4, str3, z2, i, z, str2, hkVarArr, iArr, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hq[] newArray(int i) {
        return new hq[i];
    }
}
