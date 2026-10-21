package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class pv implements Parcelable.Creator<SessionInsertRequest> {
    public static void a(SessionInsertRequest sessionInsertRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) sessionInsertRequest.a(), i, false);
        ke.a(parcel, 1000, sessionInsertRequest.d());
        ke.c(parcel, 2, sessionInsertRequest.b(), false);
        ke.c(parcel, 3, sessionInsertRequest.c(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SessionInsertRequest createFromParcel(Parcel parcel) {
        ArrayList c;
        ArrayList arrayList;
        Session session;
        int i;
        ArrayList arrayList2 = null;
        int b = a.b(parcel);
        int i2 = 0;
        ArrayList arrayList3 = null;
        Session session2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = i2;
                    ArrayList arrayList4 = arrayList3;
                    session = (Session) a.a(parcel, a, Session.CREATOR);
                    c = arrayList2;
                    arrayList = arrayList4;
                    break;
                case 2:
                    session = session2;
                    i = i2;
                    ArrayList arrayList5 = arrayList2;
                    arrayList = a.c(parcel, a, DataSet.CREATOR);
                    c = arrayList5;
                    break;
                case 3:
                    c = a.c(parcel, a, DataPoint.CREATOR);
                    arrayList = arrayList3;
                    session = session2;
                    i = i2;
                    break;
                case 1000:
                    ArrayList arrayList6 = arrayList2;
                    arrayList = arrayList3;
                    session = session2;
                    i = a.g(parcel, a);
                    c = arrayList6;
                    break;
                default:
                    a.b(parcel, a);
                    c = arrayList2;
                    arrayList = arrayList3;
                    session = session2;
                    i = i2;
                    break;
            }
            i2 = i;
            session2 = session;
            arrayList3 = arrayList;
            arrayList2 = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new SessionInsertRequest(i2, session2, arrayList3, arrayList2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SessionInsertRequest[] newArray(int i) {
        return new SessionInsertRequest[i];
    }
}
