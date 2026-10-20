package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.location.c;

/* loaded from: classes.dex */
public class ako implements Parcelable.Creator<c> {
    public static void a(c cVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, cVar.a);
        ke.a(parcel, 1000, cVar.a());
        ke.a(parcel, 2, cVar.b);
        ke.a(parcel, 3, cVar.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c createFromParcel(Parcel parcel) {
        int i = 1;
        int b = a.b(parcel);
        int i2 = 0;
        long j = 0;
        int i3 = 1;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    i = a.g(parcel, a);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new c(i2, i3, i, j);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c[] newArray(int i) {
        return new c[i];
    }
}
