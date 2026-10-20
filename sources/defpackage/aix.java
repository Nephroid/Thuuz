package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class aix implements Parcelable.Creator<ny.g> {
    public static void a(ny.g gVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = gVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, gVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, gVar.c);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, gVar.d, true);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.g createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    z = a.c(parcel, a);
                    hashSet.add(2);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    hashSet.add(3);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ny.g(hashSet, i, z, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.g[] newArray(int i) {
        return new ny.g[i];
    }
}
