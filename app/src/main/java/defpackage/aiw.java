package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class aiw implements Parcelable.Creator<ny.f> {
    public static void a(ny.f fVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = fVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, fVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, fVar.c, true);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, fVar.d, true);
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, fVar.e, true);
        }
        if (set.contains(5)) {
            ke.a(parcel, 5, fVar.f, true);
        }
        if (set.contains(6)) {
            ke.a(parcel, 6, fVar.g, true);
        }
        if (set.contains(7)) {
            ke.a(parcel, 7, fVar.h);
        }
        if (set.contains(8)) {
            ke.a(parcel, 8, fVar.i, true);
        }
        if (set.contains(9)) {
            ke.a(parcel, 9, fVar.j, true);
        }
        if (set.contains(10)) {
            ke.a(parcel, 10, fVar.k);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.f createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        String str2 = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    str7 = a.o(parcel, a);
                    hashSet.add(2);
                    break;
                case 3:
                    str6 = a.o(parcel, a);
                    hashSet.add(3);
                    break;
                case 4:
                    str5 = a.o(parcel, a);
                    hashSet.add(4);
                    break;
                case 5:
                    str4 = a.o(parcel, a);
                    hashSet.add(5);
                    break;
                case 6:
                    str3 = a.o(parcel, a);
                    hashSet.add(6);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    hashSet.add(7);
                    break;
                case 8:
                    str2 = a.o(parcel, a);
                    hashSet.add(8);
                    break;
                case 9:
                    str = a.o(parcel, a);
                    hashSet.add(9);
                    break;
                case 10:
                    i = a.g(parcel, a);
                    hashSet.add(10);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ny.f(hashSet, i2, str7, str6, str5, str4, str3, z, str2, str, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.f[] newArray(int i) {
        return new ny.f[i];
    }
}
