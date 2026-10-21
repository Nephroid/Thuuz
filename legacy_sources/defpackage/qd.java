package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataTypeResult;

/* loaded from: classes.dex */
public class qd implements Parcelable.Creator<DataTypeResult> {
    public static void a(DataTypeResult dataTypeResult, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) dataTypeResult.a(), i, false);
        ke.a(parcel, 1000, dataTypeResult.c());
        ke.a(parcel, 3, (Parcelable) dataTypeResult.b(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataTypeResult createFromParcel(Parcel parcel) {
        DataType dataType;
        Status status;
        int i;
        DataType dataType2 = null;
        int b = a.b(parcel);
        int i2 = 0;
        Status status2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    Status status3 = (Status) a.a(parcel, a, Status.CREATOR);
                    i = i2;
                    dataType = dataType2;
                    status = status3;
                    break;
                case 3:
                    dataType = (DataType) a.a(parcel, a, DataType.CREATOR);
                    status = status2;
                    i = i2;
                    break;
                case 1000:
                    DataType dataType3 = dataType2;
                    status = status2;
                    i = a.g(parcel, a);
                    dataType = dataType3;
                    break;
                default:
                    a.b(parcel, a);
                    dataType = dataType2;
                    status = status2;
                    i = i2;
                    break;
            }
            i2 = i;
            status2 = status;
            dataType2 = dataType;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new DataTypeResult(i2, status2, dataType2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataTypeResult[] newArray(int i) {
        return new DataTypeResult[i];
    }
}
