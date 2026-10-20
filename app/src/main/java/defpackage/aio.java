package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.nt;
import com.google.android.gms.internal.nv;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class aio implements Parcelable.Creator<nv> {
    public static void a(nv nvVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = nvVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, nvVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, nvVar.c, true);
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, (Parcelable) nvVar.d, i, true);
        }
        if (set.contains(5)) {
            ke.a(parcel, 5, nvVar.e, true);
        }
        if (set.contains(6)) {
            ke.a(parcel, 6, (Parcelable) nvVar.f, i, true);
        }
        if (set.contains(7)) {
            ke.a(parcel, 7, nvVar.g, true);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nv createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        nt ntVar = null;
        String str2 = null;
        nt ntVar2 = null;
        String str3 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    str3 = a.o(parcel, a);
                    hashSet.add(2);
                    break;
                case 3:
                default:
                    a.b(parcel, a);
                    break;
                case 4:
                    nt ntVar3 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(4);
                    ntVar2 = ntVar3;
                    break;
                case 5:
                    str2 = a.o(parcel, a);
                    hashSet.add(5);
                    break;
                case 6:
                    nt ntVar4 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(6);
                    ntVar = ntVar4;
                    break;
                case 7:
                    str = a.o(parcel, a);
                    hashSet.add(7);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new nv(hashSet, i, str3, ntVar2, str2, ntVar, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nv[] newArray(int i) {
        return new nv[i];
    }
}
