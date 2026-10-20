package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class om implements Parcelable.Creator<DataSet> {
    public static void a(DataSet dataSet, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) dataSet.a(), i, false);
        ke.a(parcel, 1000, dataSet.d());
        ke.a(parcel, 2, (Parcelable) dataSet.b(), i, false);
        ke.d(parcel, 3, dataSet.e(), false);
        ke.c(parcel, 4, dataSet.f(), false);
        ke.a(parcel, 5, dataSet.c());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSet createFromParcel(Parcel parcel) {
        boolean z = false;
        ArrayList arrayList = null;
        int b = a.b(parcel);
        ArrayList arrayList2 = new ArrayList();
        DataType dataType = null;
        DataSource dataSource = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    dataSource = (DataSource) a.a(parcel, a, DataSource.CREATOR);
                    break;
                case 2:
                    dataType = (DataType) a.a(parcel, a, DataType.CREATOR);
                    break;
                case 3:
                    a.a(parcel, a, arrayList2, getClass().getClassLoader());
                    break;
                case 4:
                    arrayList = a.c(parcel, a, DataSource.CREATOR);
                    break;
                case 5:
                    z = a.c(parcel, a);
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
        return new DataSet(i, dataSource, dataType, arrayList2, arrayList, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSet[] newArray(int i) {
        return new DataSet[i];
    }
}
