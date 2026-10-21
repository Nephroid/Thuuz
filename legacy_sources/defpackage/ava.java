package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.g;
import com.google.android.gms.wallet.wobs.l;

/* loaded from: classes.dex */
public class ava implements Parcelable.Creator<f> {
    public static void a(f fVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, fVar.a());
        ke.a(parcel, 2, fVar.a, false);
        ke.a(parcel, 3, (Parcelable) fVar.b, i, false);
        ke.a(parcel, 4, fVar.c, false);
        ke.a(parcel, 5, (Parcelable) fVar.d, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public f createFromParcel(Parcel parcel) {
        l lVar = null;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        g gVar = null;
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
                    gVar = (g) a.a(parcel, a, g.CREATOR);
                    break;
                case 4:
                    str = a.o(parcel, a);
                    break;
                case 5:
                    lVar = (l) a.a(parcel, a, l.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new f(i, str2, gVar, str, lVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public f[] newArray(int i) {
        return new f[i];
    }
}
