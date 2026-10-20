package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.mb;

/* loaded from: classes.dex */
public class ahv implements Parcelable.Creator<mb> {
    public static void a(mb mbVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, mbVar.f(), false);
        ke.a(parcel, 1000, mbVar.a());
        ke.a(parcel, 2, mbVar.g());
        ke.a(parcel, 3, mbVar.b());
        ke.a(parcel, 4, mbVar.c());
        ke.a(parcel, 5, mbVar.d());
        ke.a(parcel, 6, mbVar.e());
        ke.a(parcel, 7, mbVar.h());
        ke.a(parcel, 8, mbVar.i());
        ke.a(parcel, 9, mbVar.j());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mb createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    s = a.f(parcel, a);
                    break;
                case 4:
                    d = a.m(parcel, a);
                    break;
                case 5:
                    d2 = a.m(parcel, a);
                    break;
                case 6:
                    f = a.l(parcel, a);
                    break;
                case 7:
                    i2 = a.g(parcel, a);
                    break;
                case 8:
                    i3 = a.g(parcel, a);
                    break;
                case 9:
                    i4 = a.g(parcel, a);
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
        return new mb(i, str, i2, s, d, d2, f, j, i3, i4);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mb[] newArray(int i) {
        return new mb[i];
    }
}
