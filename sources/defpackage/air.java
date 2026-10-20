package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class air implements Parcelable.Creator<ny.b> {
    public static void a(ny.b bVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = bVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, bVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, (Parcelable) bVar.c, i, true);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, (Parcelable) bVar.d, i, true);
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, bVar.e);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.b createFromParcel(Parcel parcel) {
        ny.b.C0004b c0004b = null;
        int i = 0;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        ny.b.a aVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    ny.b.a aVar2 = (ny.b.a) a.a(parcel, a, ny.b.a.CREATOR);
                    hashSet.add(2);
                    aVar = aVar2;
                    break;
                case 3:
                    ny.b.C0004b c0004b2 = (ny.b.C0004b) a.a(parcel, a, ny.b.C0004b.CREATOR);
                    hashSet.add(3);
                    c0004b = c0004b2;
                    break;
                case 4:
                    i = a.g(parcel, a);
                    hashSet.add(4);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ny.b(hashSet, i2, aVar, c0004b, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.b[] newArray(int i) {
        return new ny.b[i];
    }
}
