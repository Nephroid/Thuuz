package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.oo;

/* loaded from: classes.dex */
public class aja implements Parcelable.Creator<oo> {
    public static void a(oo ooVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, ooVar.a());
        ke.a(parcel, 2, ooVar.a, false);
        ke.a(parcel, 3, ooVar.b, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public oo createFromParcel(Parcel parcel) {
        String[] strArr = null;
        int b = a.b(parcel);
        int i = 0;
        byte[][] bArr = (byte[][]) null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    strArr = a.A(parcel, a);
                    break;
                case 3:
                    bArr = a.s(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new oo(i, strArr, bArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public oo[] newArray(int i) {
        return new oo[i];
    }
}
