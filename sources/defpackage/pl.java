package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataReadRequest;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class pl implements Parcelable.Creator<DataReadRequest> {
    public static void a(DataReadRequest dataReadRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, dataReadRequest.a(), false);
        ke.a(parcel, 1000, dataReadRequest.n());
        ke.c(parcel, 2, dataReadRequest.b(), false);
        ke.a(parcel, 3, dataReadRequest.c());
        ke.a(parcel, 4, dataReadRequest.d());
        ke.c(parcel, 5, dataReadRequest.e(), false);
        ke.c(parcel, 6, dataReadRequest.f(), false);
        ke.a(parcel, 7, dataReadRequest.g());
        ke.a(parcel, 8, dataReadRequest.h());
        ke.a(parcel, 9, (Parcelable) dataReadRequest.i(), i, false);
        ke.a(parcel, 10, dataReadRequest.j());
        ke.a(parcel, 11, dataReadRequest.k());
        ke.a(parcel, 12, dataReadRequest.m());
        ke.a(parcel, 13, dataReadRequest.l());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataReadRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        long j = 0;
        long j2 = 0;
        ArrayList arrayList3 = null;
        ArrayList arrayList4 = null;
        int i2 = 0;
        long j3 = 0;
        DataSource dataSource = null;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList = a.c(parcel, a, DataType.CREATOR);
                    break;
                case 2:
                    arrayList2 = a.c(parcel, a, DataSource.CREATOR);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    j2 = a.i(parcel, a);
                    break;
                case 5:
                    arrayList3 = a.c(parcel, a, DataType.CREATOR);
                    break;
                case 6:
                    arrayList4 = a.c(parcel, a, DataSource.CREATOR);
                    break;
                case 7:
                    i2 = a.g(parcel, a);
                    break;
                case 8:
                    j3 = a.i(parcel, a);
                    break;
                case 9:
                    dataSource = (DataSource) a.a(parcel, a, DataSource.CREATOR);
                    break;
                case 10:
                    i3 = a.g(parcel, a);
                    break;
                case 11:
                    z = a.c(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    z2 = a.c(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    z3 = a.c(parcel, a);
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
        return new DataReadRequest(i, arrayList, arrayList2, j, j2, arrayList3, arrayList4, i2, j3, dataSource, i3, z, z2, z3);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataReadRequest[] newArray(int i) {
        return new DataReadRequest[i];
    }
}
