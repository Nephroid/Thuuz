package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class qb implements Parcelable.Creator<DataReadResult> {
    public static void a(DataReadResult dataReadResult, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.d(parcel, 1, dataReadResult.e(), false);
        ke.a(parcel, 1000, dataReadResult.c());
        ke.a(parcel, 2, (Parcelable) dataReadResult.b(), i, false);
        ke.d(parcel, 3, dataReadResult.d(), false);
        ke.a(parcel, 5, dataReadResult.a());
        ke.c(parcel, 6, dataReadResult.f(), false);
        ke.c(parcel, 7, dataReadResult.g(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataReadResult createFromParcel(Parcel parcel) {
        int i = 0;
        ArrayList arrayList = null;
        int b = a.b(parcel);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = null;
        Status status = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    a.a(parcel, a, arrayList2, getClass().getClassLoader());
                    break;
                case 2:
                    status = (Status) a.a(parcel, a, Status.CREATOR);
                    break;
                case 3:
                    a.a(parcel, a, arrayList3, getClass().getClassLoader());
                    break;
                case 5:
                    i = a.g(parcel, a);
                    break;
                case 6:
                    arrayList4 = a.c(parcel, a, DataSource.CREATOR);
                    break;
                case 7:
                    arrayList = a.c(parcel, a, DataType.CREATOR);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new DataReadResult(i2, arrayList2, status, arrayList3, i, arrayList4, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataReadResult[] newArray(int i) {
        return new DataReadResult[i];
    }
}
