package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.nt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class ain implements Parcelable.Creator<nt> {
    public static void a(nt ntVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        Set<Integer> set = ntVar.a;
        if (set.contains(1)) {
            ke.a(parcel, 1, ntVar.b);
        }
        if (set.contains(2)) {
            ke.a(parcel, 2, (Parcelable) ntVar.c, i, true);
        }
        if (set.contains(3)) {
            ke.b(parcel, 3, ntVar.d, true);
        }
        if (set.contains(4)) {
            ke.a(parcel, 4, (Parcelable) ntVar.e, i, true);
        }
        if (set.contains(5)) {
            ke.a(parcel, 5, ntVar.f, true);
        }
        if (set.contains(6)) {
            ke.a(parcel, 6, ntVar.g, true);
        }
        if (set.contains(7)) {
            ke.a(parcel, 7, ntVar.h, true);
        }
        if (set.contains(8)) {
            ke.c(parcel, 8, ntVar.i, true);
        }
        if (set.contains(9)) {
            ke.a(parcel, 9, ntVar.j);
        }
        if (set.contains(10)) {
            ke.c(parcel, 10, ntVar.k, true);
        }
        if (set.contains(11)) {
            ke.a(parcel, 11, (Parcelable) ntVar.l, i, true);
        }
        if (set.contains(12)) {
            ke.c(parcel, 12, ntVar.m, true);
        }
        if (set.contains(13)) {
            ke.a(parcel, 13, ntVar.n, true);
        }
        if (set.contains(14)) {
            ke.a(parcel, 14, ntVar.o, true);
        }
        if (set.contains(15)) {
            ke.a(parcel, 15, (Parcelable) ntVar.p, i, true);
        }
        if (set.contains(17)) {
            ke.a(parcel, 17, ntVar.r, true);
        }
        if (set.contains(16)) {
            ke.a(parcel, 16, ntVar.q, true);
        }
        if (set.contains(19)) {
            ke.c(parcel, 19, ntVar.t, true);
        }
        if (set.contains(18)) {
            ke.a(parcel, 18, ntVar.s, true);
        }
        if (set.contains(21)) {
            ke.a(parcel, 21, ntVar.v, true);
        }
        if (set.contains(20)) {
            ke.a(parcel, 20, ntVar.u, true);
        }
        if (set.contains(23)) {
            ke.a(parcel, 23, ntVar.x, true);
        }
        if (set.contains(22)) {
            ke.a(parcel, 22, ntVar.w, true);
        }
        if (set.contains(25)) {
            ke.a(parcel, 25, ntVar.z, true);
        }
        if (set.contains(24)) {
            ke.a(parcel, 24, ntVar.y, true);
        }
        if (set.contains(27)) {
            ke.a(parcel, 27, ntVar.B, true);
        }
        if (set.contains(26)) {
            ke.a(parcel, 26, ntVar.A, true);
        }
        if (set.contains(29)) {
            ke.a(parcel, 29, (Parcelable) ntVar.D, i, true);
        }
        if (set.contains(28)) {
            ke.a(parcel, 28, ntVar.C, true);
        }
        if (set.contains(31)) {
            ke.a(parcel, 31, ntVar.F, true);
        }
        if (set.contains(30)) {
            ke.a(parcel, 30, ntVar.E, true);
        }
        if (set.contains(34)) {
            ke.a(parcel, 34, (Parcelable) ntVar.I, i, true);
        }
        if (set.contains(32)) {
            ke.a(parcel, 32, ntVar.G, true);
        }
        if (set.contains(33)) {
            ke.a(parcel, 33, ntVar.H, true);
        }
        if (set.contains(38)) {
            ke.a(parcel, 38, ntVar.L);
        }
        if (set.contains(39)) {
            ke.a(parcel, 39, ntVar.M, true);
        }
        if (set.contains(36)) {
            ke.a(parcel, 36, ntVar.J);
        }
        if (set.contains(37)) {
            ke.a(parcel, 37, (Parcelable) ntVar.K, i, true);
        }
        if (set.contains(42)) {
            ke.a(parcel, 42, ntVar.P, true);
        }
        if (set.contains(43)) {
            ke.a(parcel, 43, ntVar.Q, true);
        }
        if (set.contains(40)) {
            ke.a(parcel, 40, (Parcelable) ntVar.N, i, true);
        }
        if (set.contains(41)) {
            ke.c(parcel, 41, ntVar.O, true);
        }
        if (set.contains(46)) {
            ke.a(parcel, 46, (Parcelable) ntVar.T, i, true);
        }
        if (set.contains(47)) {
            ke.a(parcel, 47, ntVar.U, true);
        }
        if (set.contains(44)) {
            ke.a(parcel, 44, ntVar.R, true);
        }
        if (set.contains(45)) {
            ke.a(parcel, 45, ntVar.S, true);
        }
        if (set.contains(51)) {
            ke.a(parcel, 51, ntVar.Y, true);
        }
        if (set.contains(50)) {
            ke.a(parcel, 50, (Parcelable) ntVar.X, i, true);
        }
        if (set.contains(49)) {
            ke.a(parcel, 49, ntVar.W, true);
        }
        if (set.contains(48)) {
            ke.a(parcel, 48, ntVar.V, true);
        }
        if (set.contains(55)) {
            ke.a(parcel, 55, ntVar.ac, true);
        }
        if (set.contains(54)) {
            ke.a(parcel, 54, ntVar.ab, true);
        }
        if (set.contains(53)) {
            ke.a(parcel, 53, ntVar.aa, true);
        }
        if (set.contains(52)) {
            ke.a(parcel, 52, ntVar.Z, true);
        }
        if (set.contains(56)) {
            ke.a(parcel, 56, ntVar.ad, true);
        }
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nt createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        nt ntVar = null;
        ArrayList<String> arrayList = null;
        nt ntVar2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        ArrayList arrayList2 = null;
        int i2 = 0;
        ArrayList arrayList3 = null;
        nt ntVar3 = null;
        ArrayList arrayList4 = null;
        String str4 = null;
        String str5 = null;
        nt ntVar4 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        ArrayList arrayList5 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        String str15 = null;
        String str16 = null;
        String str17 = null;
        nt ntVar5 = null;
        String str18 = null;
        String str19 = null;
        String str20 = null;
        String str21 = null;
        nt ntVar6 = null;
        double d = 0.0d;
        nt ntVar7 = null;
        double d2 = 0.0d;
        String str22 = null;
        nt ntVar8 = null;
        ArrayList arrayList6 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        nt ntVar9 = null;
        String str27 = null;
        String str28 = null;
        String str29 = null;
        nt ntVar10 = null;
        String str30 = null;
        String str31 = null;
        String str32 = null;
        String str33 = null;
        String str34 = null;
        String str35 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    hashSet.add(1);
                    break;
                case 2:
                    nt ntVar11 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(2);
                    ntVar = ntVar11;
                    break;
                case 3:
                    arrayList = a.C(parcel, a);
                    hashSet.add(3);
                    break;
                case 4:
                    nt ntVar12 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(4);
                    ntVar2 = ntVar12;
                    break;
                case 5:
                    str = a.o(parcel, a);
                    hashSet.add(5);
                    break;
                case 6:
                    str2 = a.o(parcel, a);
                    hashSet.add(6);
                    break;
                case 7:
                    str3 = a.o(parcel, a);
                    hashSet.add(7);
                    break;
                case 8:
                    arrayList2 = a.c(parcel, a, nt.CREATOR);
                    hashSet.add(8);
                    break;
                case 9:
                    i2 = a.g(parcel, a);
                    hashSet.add(9);
                    break;
                case 10:
                    arrayList3 = a.c(parcel, a, nt.CREATOR);
                    hashSet.add(10);
                    break;
                case 11:
                    nt ntVar13 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(11);
                    ntVar3 = ntVar13;
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    arrayList4 = a.c(parcel, a, nt.CREATOR);
                    hashSet.add(12);
                    break;
                case HTTP.CR /* 13 */:
                    str4 = a.o(parcel, a);
                    hashSet.add(13);
                    break;
                case 14:
                    str5 = a.o(parcel, a);
                    hashSet.add(14);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    nt ntVar14 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(15);
                    ntVar4 = ntVar14;
                    break;
                case 16:
                    str6 = a.o(parcel, a);
                    hashSet.add(16);
                    break;
                case LangUtils.HASH_SEED /* 17 */:
                    str7 = a.o(parcel, a);
                    hashSet.add(17);
                    break;
                case 18:
                    str8 = a.o(parcel, a);
                    hashSet.add(18);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                    arrayList5 = a.c(parcel, a, nt.CREATOR);
                    hashSet.add(19);
                    break;
                case 20:
                    str9 = a.o(parcel, a);
                    hashSet.add(20);
                    break;
                case 21:
                    str10 = a.o(parcel, a);
                    hashSet.add(21);
                    break;
                case 22:
                    str11 = a.o(parcel, a);
                    hashSet.add(22);
                    break;
                case 23:
                    str12 = a.o(parcel, a);
                    hashSet.add(23);
                    break;
                case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                    str13 = a.o(parcel, a);
                    hashSet.add(24);
                    break;
                case 25:
                    str14 = a.o(parcel, a);
                    hashSet.add(25);
                    break;
                case 26:
                    str15 = a.o(parcel, a);
                    hashSet.add(26);
                    break;
                case 27:
                    str16 = a.o(parcel, a);
                    hashSet.add(27);
                    break;
                case 28:
                    str17 = a.o(parcel, a);
                    hashSet.add(28);
                    break;
                case 29:
                    nt ntVar15 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(29);
                    ntVar5 = ntVar15;
                    break;
                case 30:
                    str18 = a.o(parcel, a);
                    hashSet.add(30);
                    break;
                case 31:
                    str19 = a.o(parcel, a);
                    hashSet.add(31);
                    break;
                case 32:
                    str20 = a.o(parcel, a);
                    hashSet.add(32);
                    break;
                case 33:
                    str21 = a.o(parcel, a);
                    hashSet.add(33);
                    break;
                case 34:
                    nt ntVar16 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(34);
                    ntVar6 = ntVar16;
                    break;
                case 35:
                default:
                    a.b(parcel, a);
                    break;
                case 36:
                    d = a.m(parcel, a);
                    hashSet.add(36);
                    break;
                case LangUtils.HASH_OFFSET /* 37 */:
                    nt ntVar17 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(37);
                    ntVar7 = ntVar17;
                    break;
                case 38:
                    d2 = a.m(parcel, a);
                    hashSet.add(38);
                    break;
                case 39:
                    str22 = a.o(parcel, a);
                    hashSet.add(39);
                    break;
                case 40:
                    nt ntVar18 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(40);
                    ntVar8 = ntVar18;
                    break;
                case 41:
                    arrayList6 = a.c(parcel, a, nt.CREATOR);
                    hashSet.add(41);
                    break;
                case 42:
                    str23 = a.o(parcel, a);
                    hashSet.add(42);
                    break;
                case 43:
                    str24 = a.o(parcel, a);
                    hashSet.add(43);
                    break;
                case 44:
                    str25 = a.o(parcel, a);
                    hashSet.add(44);
                    break;
                case 45:
                    str26 = a.o(parcel, a);
                    hashSet.add(45);
                    break;
                case 46:
                    nt ntVar19 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(46);
                    ntVar9 = ntVar19;
                    break;
                case 47:
                    str27 = a.o(parcel, a);
                    hashSet.add(47);
                    break;
                case 48:
                    str28 = a.o(parcel, a);
                    hashSet.add(48);
                    break;
                case 49:
                    str29 = a.o(parcel, a);
                    hashSet.add(49);
                    break;
                case 50:
                    nt ntVar20 = (nt) a.a(parcel, a, nt.CREATOR);
                    hashSet.add(50);
                    ntVar10 = ntVar20;
                    break;
                case 51:
                    str30 = a.o(parcel, a);
                    hashSet.add(51);
                    break;
                case 52:
                    str31 = a.o(parcel, a);
                    hashSet.add(52);
                    break;
                case 53:
                    str32 = a.o(parcel, a);
                    hashSet.add(53);
                    break;
                case 54:
                    str33 = a.o(parcel, a);
                    hashSet.add(54);
                    break;
                case 55:
                    str34 = a.o(parcel, a);
                    hashSet.add(55);
                    break;
                case 56:
                    str35 = a.o(parcel, a);
                    hashSet.add(56);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new nt(hashSet, i, ntVar, arrayList, ntVar2, str, str2, str3, arrayList2, i2, arrayList3, ntVar3, arrayList4, str4, str5, ntVar4, str6, str7, str8, arrayList5, str9, str10, str11, str12, str13, str14, str15, str16, str17, ntVar5, str18, str19, str20, str21, ntVar6, d, ntVar7, d2, str22, ntVar8, arrayList6, str23, str24, str25, str26, ntVar9, str27, str28, str29, ntVar10, str30, str31, str32, str33, str34, str35);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nt[] newArray(int i) {
        return new nt[i];
    }
}
