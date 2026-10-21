package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.jm;
import com.google.android.gms.internal.jp;

/* loaded from: classes.dex */
public class aft implements Parcelable.Creator<jp> {
    public static void a(jp jpVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, jpVar.a());
        ke.a(parcel, 2, jpVar.e(), false);
        ke.a(parcel, 3, (Parcelable) jpVar.f(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jp createFromParcel(Parcel parcel) {
        jm jmVar = null;
        int b = a.b(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    parcel2 = a.D(parcel, a);
                    break;
                case 3:
                    jmVar = (jm) a.a(parcel, a, jm.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new jp(i, parcel2, jmVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jp[] newArray(int i) {
        return new jp[i];
    }
}
