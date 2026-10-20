package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.internal.QueryRequest;
import com.google.android.gms.drive.query.Query;

/* loaded from: classes.dex */
public class lg implements Parcelable.Creator<QueryRequest> {
    public static void a(QueryRequest queryRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, queryRequest.a);
        ke.a(parcel, 2, (Parcelable) queryRequest.b, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public QueryRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        Query query = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    query = (Query) a.a(parcel, a, Query.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new QueryRequest(i, query);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public QueryRequest[] newArray(int i) {
        return new QueryRequest[i];
    }
}
