package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.q;

/* loaded from: classes.dex */
public class oy implements Parcelable.Creator<q> {
    public static void a(q qVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) qVar.a(), i, false);
        ke.a(parcel, 1000, qVar.a);
        ke.a(parcel, 2, (Parcelable) qVar.b(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public q createFromParcel(Parcel parcel) {
        DataSet dataSet;
        Session session;
        int i;
        DataSet dataSet2 = null;
        int b = a.b(parcel);
        int i2 = 0;
        Session session2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    Session session3 = (Session) a.a(parcel, a, Session.CREATOR);
                    i = i2;
                    dataSet = dataSet2;
                    session = session3;
                    break;
                case 2:
                    dataSet = (DataSet) a.a(parcel, a, DataSet.CREATOR);
                    session = session2;
                    i = i2;
                    break;
                case 1000:
                    DataSet dataSet3 = dataSet2;
                    session = session2;
                    i = a.g(parcel, a);
                    dataSet = dataSet3;
                    break;
                default:
                    a.b(parcel, a);
                    dataSet = dataSet2;
                    session = session2;
                    i = i2;
                    break;
            }
            i2 = i;
            session2 = session;
            dataSet2 = dataSet;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new q(i2, session2, dataSet2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public q[] newArray(int i) {
        return new q[i];
    }
}
