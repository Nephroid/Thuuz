package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.dv;

/* loaded from: classes.dex */
public class ya implements Parcelable.Creator<dv> {
    public static void a(dv dvVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, dvVar.a);
        ke.a(parcel, 2, dvVar.a(), false);
        ke.a(parcel, 3, dvVar.b(), false);
        ke.a(parcel, 4, dvVar.c(), false);
        ke.a(parcel, 5, dvVar.d(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public dv createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int b = a.b(parcel);
        int i = 0;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    iBinder4 = a.p(parcel, a);
                    break;
                case 3:
                    iBinder3 = a.p(parcel, a);
                    break;
                case 4:
                    iBinder2 = a.p(parcel, a);
                    break;
                case 5:
                    iBinder = a.p(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new dv(i, iBinder4, iBinder3, iBinder2, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public dv[] newArray(int i) {
        return new dv[i];
    }
}
