package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.wobs.g;

/* loaded from: classes.dex */
public class auz implements Parcelable.Creator<g> {
    public static void a(g gVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, gVar.a());
        ke.a(parcel, 2, gVar.a);
        ke.a(parcel, 3, gVar.b, false);
        ke.a(parcel, 4, gVar.c);
        ke.a(parcel, 5, gVar.d, false);
        ke.a(parcel, 6, gVar.e);
        ke.a(parcel, 7, gVar.f);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public g createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int b = a.b(parcel);
        double d = 0.0d;
        long j = 0;
        int i2 = -1;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    i = a.g(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    d = a.m(parcel, a);
                    break;
                case 5:
                    str = a.o(parcel, a);
                    break;
                case 6:
                    j = a.i(parcel, a);
                    break;
                case 7:
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
        return new g(i3, i, str2, d, str, j, i2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public g[] newArray(int i) {
        return new g[i];
    }
}
