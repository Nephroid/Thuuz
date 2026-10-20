package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.gt;

/* loaded from: classes.dex */
public class ado implements Parcelable.Creator<gt> {
    public static void a(gt gtVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, gtVar.a);
        ke.a(parcel, 2, gtVar.b, false);
        ke.a(parcel, 3, gtVar.c);
        ke.a(parcel, 4, gtVar.d);
        ke.a(parcel, 5, gtVar.e);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public gt createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    i2 = a.g(parcel, a);
                    break;
                case 4:
                    i = a.g(parcel, a);
                    break;
                case 5:
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
        return new gt(i3, str, i2, i, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public gt[] newArray(int i) {
        return new gt[i];
    }
}
