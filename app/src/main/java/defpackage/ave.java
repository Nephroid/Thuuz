package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;

/* loaded from: classes.dex */
public class ave implements Parcelable.Creator<p> {
    public static void a(p pVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, pVar.a());
        ke.a(parcel, 2, pVar.a, false);
        ke.a(parcel, 3, pVar.b, false);
        ke.a(parcel, 4, (Parcelable) pVar.c, i, false);
        ke.a(parcel, 5, (Parcelable) pVar.d, i, false);
        ke.a(parcel, 6, (Parcelable) pVar.e, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public p createFromParcel(Parcel parcel) {
        n nVar = null;
        int b = a.b(parcel);
        int i = 0;
        n nVar2 = null;
        l lVar = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    lVar = (l) a.a(parcel, a, l.CREATOR);
                    break;
                case 5:
                    nVar2 = (n) a.a(parcel, a, n.CREATOR);
                    break;
                case 6:
                    nVar = (n) a.a(parcel, a, n.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new p(i, str2, str, lVar, nVar2, nVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public p[] newArray(int i) {
        return new p[i];
    }
}
