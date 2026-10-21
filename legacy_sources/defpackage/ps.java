package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.l;

/* loaded from: classes.dex */
public class ps implements Parcelable.Creator<l> {
    public static void a(l lVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) lVar.a(), i, false);
        ke.a(parcel, 1000, lVar.b());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public l createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        DataType dataType = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    dataType = (DataType) a.a(parcel, a, DataType.CREATOR);
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
        return new l(i, dataType);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public l[] newArray(int i) {
        return new l[i];
    }
}
