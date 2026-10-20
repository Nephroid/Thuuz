package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.v;

/* loaded from: classes.dex */
public class py implements Parcelable.Creator<v> {
    public static void a(v vVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) vVar.a(), i, false);
        ke.a(parcel, 1000, vVar.b());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public v createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        Session session = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    session = (Session) a.a(parcel, a, Session.CREATOR);
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
        return new v(i, session);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public v[] newArray(int i) {
        return new v[i];
    }
}
