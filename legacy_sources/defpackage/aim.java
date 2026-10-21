package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.nl;

/* loaded from: classes.dex */
public class aim implements Parcelable.Creator<nl> {
    public static void a(nl nlVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, nlVar.a);
        ke.a(parcel, 2, nlVar.b, false);
        ke.a(parcel, 3, nlVar.c);
        ke.a(parcel, 4, nlVar.d);
        ke.a(parcel, 5, nlVar.e, false);
        ke.a(parcel, 6, nlVar.f, false);
        ke.a(parcel, 7, nlVar.g);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nl createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int b = a.b(parcel);
        boolean z = true;
        String str2 = null;
        int i2 = 0;
        String str3 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    str3 = a.o(parcel, a);
                    break;
                case 3:
                    i2 = a.g(parcel, a);
                    break;
                case 4:
                    i = a.g(parcel, a);
                    break;
                case 5:
                    str2 = a.o(parcel, a);
                    break;
                case 6:
                    str = a.o(parcel, a);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new nl(i3, str3, i2, i, str2, str, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nl[] newArray(int i) {
        return new nl[i];
    }
}
