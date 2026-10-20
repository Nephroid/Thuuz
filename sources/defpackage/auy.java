package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.wobs.b;
import com.google.android.gms.wallet.wobs.d;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class auy implements Parcelable.Creator<d> {
    public static void a(d dVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, dVar.a());
        ke.a(parcel, 2, dVar.a, false);
        ke.a(parcel, 3, dVar.b, false);
        ke.c(parcel, 4, dVar.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList a = afu.a();
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a2 = a.a(parcel);
            switch (a.a(a2)) {
                case 1:
                    i = a.g(parcel, a2);
                    break;
                case 2:
                    str2 = a.o(parcel, a2);
                    break;
                case 3:
                    str = a.o(parcel, a2);
                    break;
                case 4:
                    a = a.c(parcel, a2, b.CREATOR);
                    break;
                default:
                    a.b(parcel, a2);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new d(i, str2, str, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d[] newArray(int i) {
        return new d[i];
    }
}
