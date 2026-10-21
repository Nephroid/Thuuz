package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.hi;
import com.google.android.gms.internal.hq;

/* loaded from: classes.dex */
public class ael implements Parcelable.Creator<hi> {
    public static void a(hi hiVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, hiVar.c, false);
        ke.a(parcel, 1000, hiVar.b);
        ke.a(parcel, 3, (Parcelable) hiVar.d, i, false);
        ke.a(parcel, 4, hiVar.e);
        ke.a(parcel, 5, hiVar.f, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hi createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int b = a.b(parcel);
        int i = 0;
        int i2 = -1;
        hq hqVar = null;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    hqVar = (hq) a.a(parcel, a, hq.CREATOR);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    bArr = a.r(parcel, a);
                    break;
                case 1000:
                    i = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new hi(i, str, hqVar, i2, bArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hi[] newArray(int i) {
        return new hi[i];
    }
}
