package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.a;

/* loaded from: classes.dex */
public class oi implements Parcelable.Creator<a> {
    public static void a(a aVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, aVar.a(), false);
        ke.a(parcel, 1000, aVar.e());
        ke.a(parcel, 2, aVar.b(), false);
        ke.a(parcel, 3, aVar.c(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a createFromParcel(Parcel parcel) {
        String str = null;
        int b = com.google.android.gms.common.internal.safeparcel.a.b(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.a(a)) {
                case 1:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, a);
                    break;
                case 2:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, a);
                    break;
                case 3:
                    str = com.google.android.gms.common.internal.safeparcel.a.o(parcel, a);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new com.google.android.gms.fitness.data.a(i, str3, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.google.android.gms.fitness.data.a[] newArray(int i) {
        return new com.google.android.gms.fitness.data.a[i];
    }
}
