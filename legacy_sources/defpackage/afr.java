package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.jm;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class afr implements Parcelable.Creator<jm> {
    public static void a(jm jmVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, jmVar.b());
        ke.c(parcel, 2, jmVar.c(), false);
        ke.a(parcel, 3, jmVar.d(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jm createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    arrayList = a.c(parcel, a, jm.a.CREATOR);
                    break;
                case 3:
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
        return new jm(i, arrayList, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jm[] newArray(int i) {
        return new jm[i];
    }
}
