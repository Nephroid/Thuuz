package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.internal.ah;

/* loaded from: classes.dex */
public class avp implements Parcelable.Creator<ah> {
    public static void a(ah ahVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, ahVar.a);
        ke.a(parcel, 2, ahVar.a());
        ke.a(parcel, 3, ahVar.b(), false);
        ke.a(parcel, 4, ahVar.c(), false);
        ke.a(parcel, 5, ahVar.d(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ah createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        byte[] bArr = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    i = a.g(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    bArr = a.r(parcel, a);
                    break;
                case 5:
                    str = a.o(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ah(i2, i, str2, bArr, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ah[] newArray(int i) {
        return new ah[i];
    }
}
