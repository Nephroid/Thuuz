package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.hm;
import com.google.android.gms.internal.hs;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class aeo implements Parcelable.Creator<hm.b> {
    public static void a(hm.b bVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, bVar.c);
        ke.a(parcel, 1, (Parcelable) bVar.a, i, false);
        ke.c(parcel, 2, bVar.b, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hm.b createFromParcel(Parcel parcel) {
        ArrayList c;
        Status status;
        int i;
        ArrayList arrayList = null;
        int b = a.b(parcel);
        int i2 = 0;
        Status status2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    Status status3 = (Status) a.a(parcel, a, Status.CREATOR);
                    i = i2;
                    c = arrayList;
                    status = status3;
                    break;
                case 2:
                    c = a.c(parcel, a, hs.CREATOR);
                    status = status2;
                    i = i2;
                    break;
                case 1000:
                    ArrayList arrayList2 = arrayList;
                    status = status2;
                    i = a.g(parcel, a);
                    c = arrayList2;
                    break;
                default:
                    a.b(parcel, a);
                    c = arrayList;
                    status = status2;
                    i = i2;
                    break;
            }
            i2 = i;
            status2 = status;
            arrayList = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new hm.b(i2, status2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hm.b[] newArray(int i) {
        return new hm.b[i];
    }
}
