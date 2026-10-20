package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SortOrder;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class mw implements Parcelable.Creator<Query> {
    public static void a(Query query, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, query.e);
        ke.a(parcel, 1, (Parcelable) query.a, i, false);
        ke.a(parcel, 3, query.b, false);
        ke.a(parcel, 4, (Parcelable) query.c, i, false);
        ke.b(parcel, 5, query.d, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Query createFromParcel(Parcel parcel) {
        ArrayList<String> arrayList = null;
        int b = a.b(parcel);
        int i = 0;
        SortOrder sortOrder = null;
        String str = null;
        LogicalFilter logicalFilter = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    logicalFilter = (LogicalFilter) a.a(parcel, a, LogicalFilter.CREATOR);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    sortOrder = (SortOrder) a.a(parcel, a, SortOrder.CREATOR);
                    break;
                case 5:
                    arrayList = a.C(parcel, a);
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
        return new Query(i, logicalFilter, str, sortOrder, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Query[] newArray(int i) {
        return new Query[i];
    }
}
