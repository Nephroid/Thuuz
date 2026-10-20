package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class ait implements Parcelable.Creator<ny.b.C0004b> {
    public static void a(ny.b.C0004b c0004b, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = c0004b.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, c0004b.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, c0004b.c);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, c0004b.d, true);
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, c0004b.e);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.b.C0004b createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        String str = null;
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
                    str = a.o(parcel, a);
                    hashSet.add(3);
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
        return new ny.b.C0004b(hashSet, i3, i2, str, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny.b.C0004b[] newArray(int i) {
        return new ny.b.C0004b[i];
    }
}
