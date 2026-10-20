package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ji;
import com.google.android.gms.internal.jm;

/* loaded from: classes.dex */
public class afq implements Parcelable.Creator<jm.b> {
    public static void a(jm.b bVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, bVar.a);
        ke.a(parcel, 2, bVar.b, false);
        ke.a(parcel, 3, (Parcelable) bVar.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jm.b createFromParcel(Parcel parcel) {
        ji.a aVar = null;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    aVar = (ji.a) a.a(parcel, a, ji.a.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new jm.b(i, str, aVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jm.b[] newArray(int i) {
        return new jm.b[i];
    }
}
