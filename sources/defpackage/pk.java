package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.DataInsertRequest;

/* loaded from: classes.dex */
public class pk implements Parcelable.Creator<DataInsertRequest> {
    public static void a(DataInsertRequest dataInsertRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) dataInsertRequest.a(), i, false);
        ke.a(parcel, 1000, dataInsertRequest.b());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataInsertRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        DataSet dataSet = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    dataSet = (DataSet) a.a(parcel, a, DataSet.CREATOR);
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
        return new DataInsertRequest(i, dataSet);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataInsertRequest[] newArray(int i) {
        return new DataInsertRequest[i];
    }
}
