package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class pm implements Parcelable.Creator<DataSourcesRequest> {
    public static void a(DataSourcesRequest dataSourcesRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, dataSourcesRequest.a(), false);
        ke.a(parcel, 1000, dataSourcesRequest.c());
        ke.a(parcel, 2, dataSourcesRequest.d(), false);
        ke.a(parcel, 3, dataSourcesRequest.b());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSourcesRequest createFromParcel(Parcel parcel) {
        ArrayList<Integer> arrayList = null;
        boolean z = false;
        int b = a.b(parcel);
        ArrayList arrayList2 = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList2 = a.c(parcel, a, DataType.CREATOR);
                    break;
                case 2:
                    arrayList = a.B(parcel, a);
                    break;
                case 3:
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
        return new DataSourcesRequest(i, arrayList2, arrayList, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSourcesRequest[] newArray(int i) {
        return new DataSourcesRequest[i];
    }
}
