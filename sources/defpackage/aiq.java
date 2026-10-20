package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class aiq implements Parcelable.Creator<ny.a> {
    public static void a(ny.a aVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = aVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, aVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, aVar.c);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, aVar.d);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.a createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    i2 = a.g(parcel, a);
                    hashSet.add(2);
                    break;
                case 3:
                    i = a.g(parcel, a);
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
        return new ny.a(hashSet, i3, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.a[] newArray(int i) {
        return new ny.a[i];
    }
}
