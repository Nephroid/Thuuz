package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.mg;
import com.google.android.gms.internal.mi;

/* loaded from: classes.dex */
public class ahy implements Parcelable.Creator<mg> {
    public static void a(mg mgVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, mgVar.b());
        ke.a(parcel, 1000, mgVar.a());
        ke.a(parcel, 2, mgVar.c());
        ke.a(parcel, 3, (Parcelable) mgVar.d(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mg createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        int i2 = -1;
        mi miVar = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    i2 = a.g(parcel, a);
                    break;
                case 3:
                    miVar = (mi) a.a(parcel, a, mi.CREATOR);
                    break;
                case 1000:
                    i3 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new mg(i3, i, i2, miVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mg[] newArray(int i) {
        return new mg[i];
    }
}
