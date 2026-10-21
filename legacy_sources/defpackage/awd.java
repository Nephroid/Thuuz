package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.internal.m;
import com.google.android.gms.wearable.internal.x;

/* loaded from: classes.dex */
public class awd implements Parcelable.Creator<x> {
    public static void a(x xVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, xVar.a);
        ke.a(parcel, 2, xVar.b);
        ke.a(parcel, 3, (Parcelable) xVar.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public x createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        m mVar = null;
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
                    mVar = (m) a.a(parcel, a, m.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new x(i2, i, mVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public x[] newArray(int i) {
        return new x[i];
    }
}
