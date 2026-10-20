package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.jd;
import com.google.android.gms.internal.ji;

/* loaded from: classes.dex */
public class afp implements Parcelable.Creator<ji.a> {
    public static void a(ji.a aVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, aVar.a());
        ke.a(parcel, 2, aVar.b());
        ke.a(parcel, 3, aVar.c());
        ke.a(parcel, 4, aVar.d());
        ke.a(parcel, 5, aVar.e());
        ke.a(parcel, 6, aVar.f(), false);
        ke.a(parcel, 7, aVar.g());
        ke.a(parcel, 8, aVar.i(), false);
        ke.a(parcel, 9, (Parcelable) aVar.k(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ji.a createFromParcel(Parcel parcel) {
        jd jdVar = null;
        int i = 0;
        int b = a.b(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i4 = a.g(parcel, a);
                    break;
                case 2:
                    i3 = a.g(parcel, a);
                    break;
                case 3:
                    z2 = a.c(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    z = a.c(parcel, a);
                    break;
                case 6:
                    str2 = a.o(parcel, a);
                    break;
                case 7:
                    i = a.g(parcel, a);
                    break;
                case 8:
                    str = a.o(parcel, a);
                    break;
                case 9:
                    jdVar = (jd) a.a(parcel, a, jd.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ji.a(i4, i3, z2, i2, z, str2, i, str, jdVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ji.a[] newArray(int i) {
        return new ji.a[i];
    }
}
