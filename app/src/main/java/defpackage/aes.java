package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.he;
import com.google.android.gms.internal.hg;
import com.google.android.gms.internal.hs;

/* loaded from: classes.dex */
public class aes implements Parcelable.Creator<hs> {
    public static void a(hs hsVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) hsVar.b, i, false);
        ke.a(parcel, 1000, hsVar.a);
        ke.a(parcel, 2, hsVar.c);
        ke.a(parcel, 3, hsVar.d);
        ke.a(parcel, 4, hsVar.e, false);
        ke.a(parcel, 5, (Parcelable) hsVar.f, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hs createFromParcel(Parcel parcel) {
        int i = 0;
        he heVar = null;
        int b = a.b(parcel);
        long j = 0;
        String str = null;
        hg hgVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    hgVar = (hg) a.a(parcel, a, hg.CREATOR);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
                    break;
                case 4:
                    str = a.o(parcel, a);
                    break;
                case 5:
                    heVar = (he) a.a(parcel, a, he.CREATOR);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new hs(i2, hgVar, j, i, str, heVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hs[] newArray(int i) {
        return new hs[i];
    }
}
