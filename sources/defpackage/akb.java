package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.x;

/* loaded from: classes.dex */
public class akb implements Parcelable.Creator<x> {
    public static void a(x xVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, xVar.a);
        ke.a(parcel, 2, xVar.b);
        ke.a(parcel, 3, xVar.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public x createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    z2 = a.c(parcel, a);
                    break;
                case 3:
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
        return new x(i, z2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public x[] newArray(int i) {
        return new x[i];
    }
}
