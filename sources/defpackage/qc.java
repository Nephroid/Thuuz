package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.result.DataSourcesResult;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class qc implements Parcelable.Creator<DataSourcesResult> {
    public static void a(DataSourcesResult dataSourcesResult, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, dataSourcesResult.a(), false);
        ke.a(parcel, 1000, dataSourcesResult.c());
        ke.a(parcel, 2, (Parcelable) dataSourcesResult.b(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSourcesResult createFromParcel(Parcel parcel) {
        Status status = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList = a.c(parcel, a, DataSource.CREATOR);
                    break;
                case 2:
                    status = (Status) a.a(parcel, a, Status.CREATOR);
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
        return new DataSourcesResult(i, arrayList, status);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSourcesResult[] newArray(int i) {
        return new DataSourcesResult[i];
    }
}
