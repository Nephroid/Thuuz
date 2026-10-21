package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.internal.ak;
import com.google.android.gms.wearable.internal.v;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class awc implements Parcelable.Creator<v> {
    public static void a(v vVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, vVar.a);
        ke.a(parcel, 2, vVar.b);
        ke.c(parcel, 3, vVar.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public v createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        ArrayList arrayList = null;
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
                    arrayList = a.c(parcel, a, ak.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new v(i2, i, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public v[] newArray(int i) {
        return new v[i];
    }
}
