package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ny;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class aip implements Parcelable.Creator<ny> {
    public static void a(ny nyVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = nyVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, nyVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, nyVar.c, true);
        }
        if (set.contains(3)) {
            ke.a(parcel, 3, (Parcelable) nyVar.d, i, true);
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, nyVar.e, true);
        }
        if (set.contains(5)) {
            ke.a(parcel, 5, nyVar.f, true);
        }
        if (set.contains(6)) {
            ke.a(parcel, 6, nyVar.g);
        }
        if (set.contains(7)) {
            ke.a(parcel, 7, (Parcelable) nyVar.h, i, true);
        }
        if (set.contains(8)) {
            ke.a(parcel, 8, nyVar.i, true);
        }
        if (set.contains(9)) {
            ke.a(parcel, 9, nyVar.j, true);
        }
        if (set.contains(12)) {
            ke.a(parcel, 12, nyVar.k);
        }
        if (set.contains(14)) {
            ke.a(parcel, 14, nyVar.l, true);
        }
        if (set.contains(15)) {
            ke.a(parcel, 15, (Parcelable) nyVar.m, i, true);
        }
        if (set.contains(16)) {
            ke.a(parcel, 16, nyVar.n);
        }
        if (set.contains(19)) {
            ke.a(parcel, 19, (Parcelable) nyVar.p, i, true);
        }
        if (set.contains(18)) {
            ke.a(parcel, 18, nyVar.o, true);
        }
        if (set.contains(21)) {
            ke.a(parcel, 21, nyVar.r);
        }
        if (set.contains(20)) {
            ke.a(parcel, 20, nyVar.q, true);
        }
        if (set.contains(23)) {
            ke.c(parcel, 23, nyVar.t, true);
        }
        if (set.contains(22)) {
            ke.c(parcel, 22, nyVar.s, true);
        }
        if (set.contains(25)) {
            ke.a(parcel, 25, nyVar.v);
        }
        if (set.contains(24)) {
            ke.a(parcel, 24, nyVar.u);
        }
        if (set.contains(27)) {
            ke.a(parcel, 27, nyVar.x, true);
        }
        if (set.contains(26)) {
            ke.a(parcel, 26, nyVar.w, true);
        }
        if (set.contains(29)) {
            ke.a(parcel, 29, nyVar.z);
        }
        if (set.contains(28)) {
            ke.c(parcel, 28, nyVar.y, true);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str = null;
        ny.a aVar = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        ny.b bVar = null;
        String str4 = null;
        String str5 = null;
        int i3 = 0;
        String str6 = null;
        ny.c cVar = null;
        boolean z = false;
        String str7 = null;
        ny.d dVar = null;
        String str8 = null;
        int i4 = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        int i5 = 0;
        int i6 = 0;
        String str9 = null;
        String str10 = null;
        ArrayList arrayList3 = null;
        boolean z2 = false;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    hashSet.add(2);
                    break;
                case 3:
                    ny.a aVar2 = (ny.a) a.a(parcel, a, ny.a.CREATOR);
                    hashSet.add(3);
                    aVar = aVar2;
                    break;
                case 4:
                    str2 = a.o(parcel, a);
                    hashSet.add(4);
                    break;
                case 5:
                    str3 = a.o(parcel, a);
                    hashSet.add(5);
                    break;
                case 6:
                    i2 = a.g(parcel, a);
                    hashSet.add(6);
                    break;
                case 7:
                    ny.b bVar2 = (ny.b) a.a(parcel, a, ny.b.CREATOR);
                    hashSet.add(7);
                    bVar = bVar2;
                    break;
                case 8:
                    str4 = a.o(parcel, a);
                    hashSet.add(8);
                    break;
                case 9:
                    str5 = a.o(parcel, a);
                    hashSet.add(9);
                    break;
                case 10:
                case 11:
                case HTTP.CR /* 13 */:
                case LangUtils.HASH_SEED /* 17 */:
                default:
                    a.b(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    i3 = a.g(parcel, a);
                    hashSet.add(12);
                    break;
                case 14:
                    str6 = a.o(parcel, a);
                    hashSet.add(14);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    ny.c cVar2 = (ny.c) a.a(parcel, a, ny.c.CREATOR);
                    hashSet.add(15);
                    cVar = cVar2;
                    break;
                case 16:
                    z = a.c(parcel, a);
                    hashSet.add(16);
                    break;
                case 18:
                    str7 = a.o(parcel, a);
                    hashSet.add(18);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                    ny.d dVar2 = (ny.d) a.a(parcel, a, ny.d.CREATOR);
                    hashSet.add(19);
                    dVar = dVar2;
                    break;
                case 20:
                    str8 = a.o(parcel, a);
                    hashSet.add(20);
                    break;
                case 21:
                    i4 = a.g(parcel, a);
                    hashSet.add(21);
                    break;
                case 22:
                    arrayList = a.c(parcel, a, ny.f.CREATOR);
                    hashSet.add(22);
                    break;
                case 23:
                    arrayList2 = a.c(parcel, a, ny.g.CREATOR);
                    hashSet.add(23);
                    break;
                case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                    i5 = a.g(parcel, a);
                    hashSet.add(24);
                    break;
                case 25:
                    i6 = a.g(parcel, a);
                    hashSet.add(25);
                    break;
                case 26:
                    str9 = a.o(parcel, a);
                    hashSet.add(26);
                    break;
                case 27:
                    str10 = a.o(parcel, a);
                    hashSet.add(27);
                    break;
                case 28:
                    arrayList3 = a.c(parcel, a, ny.h.CREATOR);
                    hashSet.add(28);
                    break;
                case 29:
                    z2 = a.c(parcel, a);
                    hashSet.add(29);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ny(hashSet, i, str, aVar, str2, str3, i2, bVar, str4, str5, i3, str6, cVar, z, str7, dVar, str8, i4, arrayList, arrayList2, i5, i6, str9, str10, arrayList3, z2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ny[] newArray(int i) {
        return new ny[i];
    }
}
