package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.om;

/* loaded from: classes.dex */
public class aiz implements Parcelable.Creator<om> {
    public static void a(om omVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, omVar.a());
        ke.a(parcel, 2, omVar.a, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public om createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        int[] iArr = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    iArr = a.u(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new om(i, iArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public om[] newArray(int i) {
        return new om[i];
    }
}
