package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.mi;
import com.google.android.gms.internal.mm;
import org.apache.http.HttpStatus;

/* loaded from: classes.dex */
public class aib implements Parcelable.Creator<mm> {
    public static void a(mm mmVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, mmVar.b);
        ke.a(parcel, 2, (Parcelable) mmVar.a(), i, false);
        ke.a(parcel, 3, mmVar.b());
        ke.a(parcel, 4, mmVar.c());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mm createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        mi miVar = null;
        long j = mm.a;
        int i2 = HttpStatus.SC_PROCESSING;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 2:
                    miVar = (mi) a.a(parcel, a, mi.CREATOR);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
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
        return new mm(i, miVar, j, i2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mm[] newArray(int i) {
        return new mm[i];
    }
}
