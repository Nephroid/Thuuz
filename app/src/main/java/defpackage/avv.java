package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.internal.am;
import com.google.android.gms.wearable.internal.au;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class avv implements Parcelable.Creator<au> {
    public static void a(au auVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, auVar.a);
        ke.a(parcel, 2, auVar.b);
        ke.a(parcel, 3, auVar.c);
        ke.c(parcel, 4, auVar.d, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public au createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        long j = 0;
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
                    j = a.i(parcel, a);
                    break;
                case 4:
                    arrayList = a.c(parcel, a, am.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new au(i2, i, j, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public au[] newArray(int i) {
        return new au[i];
    }
}
