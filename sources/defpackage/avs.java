package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.internal.ao;
import com.google.android.gms.wearable.internal.m;

/* loaded from: classes.dex */
public class avs implements Parcelable.Creator<ao> {
    public static void a(ao aoVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, aoVar.a);
        ke.a(parcel, 2, aoVar.b);
        ke.a(parcel, 3, (Parcelable) aoVar.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ao createFromParcel(Parcel parcel) {
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
        return new ao(i2, i, mVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ao[] newArray(int i) {
        return new ao[i];
    }
}
