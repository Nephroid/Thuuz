package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class aiv implements Parcelable.Creator<ny.d> {
    public static void a(ny.d dVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = dVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, dVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, dVar.c, true);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, dVar.d, true);
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, dVar.e, true);
        }
        if (set.contains(5)) {
            ke.a(parcel, 5, dVar.f, true);
        }
        if (set.contains(6)) {
            ke.a(parcel, 6, dVar.g, true);
        }
        if (set.contains(7)) {
            ke.a(parcel, 7, dVar.h, true);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.d createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    str6 = a.o(parcel, a);
                    hashSet.add(2);
                    break;
                case 3:
                    str5 = a.o(parcel, a);
                    hashSet.add(3);
                    break;
                case 4:
                    str4 = a.o(parcel, a);
                    hashSet.add(4);
                    break;
                case 5:
                    str3 = a.o(parcel, a);
                    hashSet.add(5);
                    break;
                case 6:
                    str2 = a.o(parcel, a);
                    hashSet.add(6);
                    break;
                case 7:
                    str = a.o(parcel, a);
                    hashSet.add(7);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ny.d(hashSet, i, str6, str5, str4, str3, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.d[] newArray(int i) {
        return new ny.d[i];
    }
}
