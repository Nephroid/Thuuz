package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.internal.ab;
import com.google.android.gms.wearable.internal.ak;

/* loaded from: classes.dex */
public class avl implements Parcelable.Creator<ab> {
    public static void a(ab abVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, abVar.a);
        ke.a(parcel, 2, abVar.b);
        ke.a(parcel, 3, (Parcelable) abVar.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ab createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        ak akVar = null;
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
                    akVar = (ak) a.a(parcel, a, ak.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ab(i2, i, akVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ab[] newArray(int i) {
        return new ab[i];
    }
}
