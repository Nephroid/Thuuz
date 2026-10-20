package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Value;

/* loaded from: classes.dex */
public class ol implements Parcelable.Creator<DataPoint> {
    public static void a(DataPoint dataPoint, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) dataPoint.b(), i, false);
        ke.a(parcel, 1000, dataPoint.h());
        ke.a(parcel, 3, dataPoint.d());
        ke.a(parcel, 4, dataPoint.g());
        ke.a(parcel, 5, (Parcelable[]) dataPoint.a(), i, false);
        ke.a(parcel, 6, (Parcelable) dataPoint.c(), i, false);
        ke.a(parcel, 7, dataPoint.e());
        ke.a(parcel, 8, dataPoint.f());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataPoint createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        DataSource dataSource = null;
        long j = 0;
        long j2 = 0;
        Value[] valueArr = null;
        DataSource dataSource2 = null;
        long j3 = 0;
        long j4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    dataSource = (DataSource) a.a(parcel, a, DataSource.CREATOR);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    j2 = a.i(parcel, a);
                    break;
                case 5:
                    valueArr = (Value[]) a.b(parcel, a, Value.CREATOR);
                    break;
                case 6:
                    dataSource2 = (DataSource) a.a(parcel, a, DataSource.CREATOR);
                    break;
                case 7:
                    j3 = a.i(parcel, a);
                    break;
                case 8:
                    j4 = a.i(parcel, a);
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
        return new DataPoint(i, dataSource, j, j2, valueArr, dataSource2, j3, j4);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataPoint[] newArray(int i) {
        return new DataPoint[i];
    }
}
