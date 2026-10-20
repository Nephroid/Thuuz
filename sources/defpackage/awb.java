package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.c;
import com.google.android.gms.wearable.internal.t;

/* loaded from: classes.dex */
public class awb implements Parcelable.Creator<t> {
    public static void a(t tVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, tVar.a);
        ke.a(parcel, 2, tVar.b);
        ke.a(parcel, 3, (Parcelable[]) tVar.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public t createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        c[] cVarArr = null;
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
                    cVarArr = (c[]) a.b(parcel, a, c.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new t(i2, i, cVarArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public t[] newArray(int i) {
        return new t[i];
    }
}
