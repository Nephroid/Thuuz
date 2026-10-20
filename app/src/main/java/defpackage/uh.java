package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.bj;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class uh implements Parcelable.Creator<bj> {
    public static void a(bj bjVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, bjVar.a);
        ke.a(parcel, 2, bjVar.b);
        ke.a(parcel, 3, bjVar.c);
        ke.a(parcel, 4, bjVar.d);
        ke.a(parcel, 5, bjVar.e);
        ke.a(parcel, 6, bjVar.f);
        ke.a(parcel, 7, bjVar.g);
        ke.a(parcel, 8, bjVar.h);
        ke.a(parcel, 9, bjVar.i);
        ke.a(parcel, 10, bjVar.j, false);
        ke.a(parcel, 11, bjVar.k);
        ke.a(parcel, 12, bjVar.l, false);
        ke.a(parcel, 13, bjVar.m);
        ke.a(parcel, 14, bjVar.n);
        ke.a(parcel, 15, bjVar.o, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bj createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        String str = null;
        int i10 = 0;
        String str2 = null;
        int i11 = 0;
        int i12 = 0;
        String str3 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    i2 = a.g(parcel, a);
                    break;
                case 3:
                    i3 = a.g(parcel, a);
                    break;
                case 4:
                    i4 = a.g(parcel, a);
                    break;
                case 5:
                    i5 = a.g(parcel, a);
                    break;
                case 6:
                    i6 = a.g(parcel, a);
                    break;
                case 7:
                    i7 = a.g(parcel, a);
                    break;
                case 8:
                    i8 = a.g(parcel, a);
                    break;
                case 9:
                    i9 = a.g(parcel, a);
                    break;
                case 10:
                    str = a.o(parcel, a);
                    break;
                case 11:
                    i10 = a.g(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    str2 = a.o(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    i11 = a.g(parcel, a);
                    break;
                case 14:
                    i12 = a.g(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    str3 = a.o(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new bj(i, i2, i3, i4, i5, i6, i7, i8, i9, str, i10, str2, i11, i12, str3);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bj[] newArray(int i) {
        return new bj[i];
    }
}
