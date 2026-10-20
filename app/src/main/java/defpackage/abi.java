package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.fk;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class abi implements Parcelable.Creator<fk> {
    public static void a(fk fkVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, fkVar.a);
        ke.a(parcel, 2, fkVar.b, false);
        ke.a(parcel, 3, fkVar.c, false);
        ke.b(parcel, 4, fkVar.d, false);
        ke.a(parcel, 5, fkVar.e);
        ke.b(parcel, 6, fkVar.f, false);
        ke.a(parcel, 7, fkVar.g);
        ke.a(parcel, 8, fkVar.h);
        ke.a(parcel, 9, fkVar.i);
        ke.b(parcel, 10, fkVar.j, false);
        ke.a(parcel, 11, fkVar.k);
        ke.a(parcel, 12, fkVar.l);
        ke.a(parcel, 13, fkVar.m, false);
        ke.a(parcel, 14, fkVar.n);
        ke.a(parcel, 15, fkVar.o, false);
        ke.a(parcel, 19, fkVar.q, false);
        ke.a(parcel, 18, fkVar.p);
        ke.a(parcel, 21, fkVar.r, false);
        ke.a(parcel, 23, fkVar.t);
        ke.a(parcel, 22, fkVar.s);
        ke.a(parcel, 25, fkVar.v);
        ke.a(parcel, 24, fkVar.u);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public fk createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        ArrayList<String> arrayList = null;
        int i2 = 0;
        ArrayList<String> arrayList2 = null;
        long j = 0;
        boolean z = false;
        long j2 = 0;
        ArrayList<String> arrayList3 = null;
        long j3 = 0;
        int i3 = 0;
        String str3 = null;
        long j4 = 0;
        String str4 = null;
        boolean z2 = false;
        String str5 = null;
        String str6 = null;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    arrayList = a.C(parcel, a);
                    break;
                case 5:
                    i2 = a.g(parcel, a);
                    break;
                case 6:
                    arrayList2 = a.C(parcel, a);
                    break;
                case 7:
                    j = a.i(parcel, a);
                    break;
                case 8:
                    z = a.c(parcel, a);
                    break;
                case 9:
                    j2 = a.i(parcel, a);
                    break;
                case 10:
                    arrayList3 = a.C(parcel, a);
                    break;
                case 11:
                    j3 = a.i(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    i3 = a.g(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    str3 = a.o(parcel, a);
                    break;
                case 14:
                    j4 = a.i(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    str4 = a.o(parcel, a);
                    break;
                case 16:
                case LangUtils.HASH_SEED /* 17 */:
                case 20:
                default:
                    a.b(parcel, a);
                    break;
                case 18:
                    z2 = a.c(parcel, a);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                    str5 = a.o(parcel, a);
                    break;
                case 21:
                    str6 = a.o(parcel, a);
                    break;
                case 22:
                    z3 = a.c(parcel, a);
                    break;
                case 23:
                    z4 = a.c(parcel, a);
                    break;
                case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                    z5 = a.c(parcel, a);
                    break;
                case 25:
                    z6 = a.c(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new fk(i, str, str2, arrayList, i2, arrayList2, j, z, j2, arrayList3, j3, i3, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public fk[] newArray(int i) {
        return new fk[i];
    }
}
