package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.c;
import com.google.android.gms.wearable.internal.r;

/* loaded from: classes.dex */
public class awa implements Parcelable.Creator<r> {
    public static void a(r rVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, rVar.a);
        ke.a(parcel, 2, rVar.b);
        ke.a(parcel, 3, (Parcelable) rVar.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public r createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        c cVar = null;
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
                    cVar = (c) a.a(parcel, a, c.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new r(i2, i, cVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public r[] newArray(int i) {
        return new r[i];
    }
}
