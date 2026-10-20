package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class aiy implements Parcelable.Creator<ny.h> {
    public static void a(ny.h hVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = hVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, hVar.b);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, hVar.e());
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, hVar.e, true);
        }
        if (set.contains(5)) {
            ke.a(parcel, 5, hVar.c, true);
        }
        if (set.contains(6)) {
            ke.a(parcel, 6, hVar.d);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.h createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                default:
                    a.b(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
                    hashSet.add(3);
                    break;
                case 4:
                    str = a.o(parcel, a);
                    hashSet.add(4);
                    break;
                case 5:
                    str2 = a.o(parcel, a);
                    hashSet.add(5);
                    break;
                case 6:
                    i2 = a.g(parcel, a);
                    hashSet.add(6);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ny.h(hashSet, i3, str2, i2, str, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.h[] newArray(int i) {
        return new ny.h[i];
    }
}
