package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.nh;

/* loaded from: classes.dex */
public class ail implements Parcelable.Creator<nh> {
    public static void a(nh nhVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, nhVar.a);
        ke.a(parcel, 2, nhVar.b);
        ke.a(parcel, 3, nhVar.c, false);
        ke.a(parcel, 4, nhVar.d, false);
        ke.a(parcel, 5, nhVar.e, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nh createFromParcel(Parcel parcel) {
        Bundle bundle = null;
        int b = a.b(parcel);
        int i = 0;
        long j = 0;
        byte[] bArr = null;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    bArr = a.r(parcel, a);
                    break;
                case 5:
                    bundle = a.q(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new nh(i, j, str, bArr, bundle);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nh[] newArray(int i) {
        return new nh[i];
    }
}
