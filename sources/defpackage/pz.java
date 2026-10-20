package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.request.x;

/* loaded from: classes.dex */
public class pz implements Parcelable.Creator<x> {
    public static void a(x xVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, xVar.a(), false);
        ke.a(parcel, 1000, xVar.c());
        ke.a(parcel, 2, xVar.b(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public x createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str2 = a.o(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
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
        return new x(i, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public x[] newArray(int i) {
        return new x[i];
    }
}
