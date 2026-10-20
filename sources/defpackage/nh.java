package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.query.internal.FilterHolder;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.Operator;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class nh implements Parcelable.Creator<LogicalFilter> {
    public static void a(LogicalFilter logicalFilter, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, logicalFilter.c);
        ke.a(parcel, 1, (Parcelable) logicalFilter.a, i, false);
        ke.c(parcel, 2, logicalFilter.b, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LogicalFilter createFromParcel(Parcel parcel) {
        ArrayList c;
        Operator operator;
        int i;
        ArrayList arrayList = null;
        int b = a.b(parcel);
        int i2 = 0;
        Operator operator2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    Operator operator3 = (Operator) a.a(parcel, a, Operator.CREATOR);
                    i = i2;
                    c = arrayList;
                    operator = operator3;
                    break;
                case 2:
                    c = a.c(parcel, a, FilterHolder.CREATOR);
                    operator = operator2;
                    i = i2;
                    break;
                case 1000:
                    ArrayList arrayList2 = arrayList;
                    operator = operator2;
                    i = a.g(parcel, a);
                    c = arrayList2;
                    break;
                default:
                    a.b(parcel, a);
                    c = arrayList;
                    operator = operator2;
                    i = i2;
                    break;
            }
            i2 = i;
            operator2 = operator;
            arrayList = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new LogicalFilter(i2, operator2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LogicalFilter[] newArray(int i) {
        return new LogicalFilter[i];
    }
}
