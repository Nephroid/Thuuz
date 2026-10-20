package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.mi;
import com.google.android.gms.internal.mo;
import com.google.android.gms.internal.ms;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ahz implements Parcelable.Creator<mi> {
    public static void a(mi miVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, miVar.b, false);
        ke.a(parcel, 1000, miVar.a);
        ke.a(parcel, 2, miVar.a(), false);
        ke.a(parcel, 3, miVar.b());
        ke.c(parcel, 4, miVar.c, false);
        ke.b(parcel, 6, miVar.d, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mi createFromParcel(Parcel parcel) {
        boolean z = false;
        ArrayList<String> arrayList = null;
        int b = a.b(parcel);
        ArrayList arrayList2 = null;
        String str = null;
        ArrayList arrayList3 = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList3 = a.c(parcel, a, mo.CREATOR);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    z = a.c(parcel, a);
                    break;
                case 4:
                    arrayList2 = a.c(parcel, a, ms.CREATOR);
                    break;
                case 6:
                    arrayList = a.C(parcel, a);
                    break;
                case 1000:
                    i = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new mi(i, arrayList3, str, z, arrayList2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mi[] newArray(int i) {
        return new mi[i];
    }
}
