package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ay;

/* loaded from: classes.dex */
public class tp implements Parcelable.Creator<ay> {
    public static void a(ay ayVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, ayVar.a);
        ke.a(parcel, 2, ayVar.b, false);
        ke.a(parcel, 3, ayVar.c);
        ke.a(parcel, 4, ayVar.d);
        ke.a(parcel, 5, ayVar.e);
        ke.a(parcel, 6, ayVar.f);
        ke.a(parcel, 7, ayVar.g);
        ke.a(parcel, 8, (Parcelable[]) ayVar.h, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ay createFromParcel(Parcel parcel) {
        ay[] ayVarArr = null;
        int i = 0;
        int b = a.b(parcel);
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        int i5 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i5 = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    i4 = a.g(parcel, a);
                    break;
                case 4:
                    i3 = a.g(parcel, a);
                    break;
                case 5:
                    z = a.c(parcel, a);
                    break;
                case 6:
                    i2 = a.g(parcel, a);
                    break;
                case 7:
                    i = a.g(parcel, a);
                    break;
                case 8:
                    ayVarArr = (ay[]) a.b(parcel, a, ay.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ay(i5, str, i4, i3, z, i2, i, ayVarArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ay[] newArray(int i) {
        return new ay[i];
    }
}
