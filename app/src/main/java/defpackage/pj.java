package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class pj implements Parcelable.Creator<DataDeleteRequest> {
    public static void a(DataDeleteRequest dataDeleteRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, dataDeleteRequest.a());
        ke.a(parcel, 1000, dataDeleteRequest.h());
        ke.a(parcel, 2, dataDeleteRequest.b());
        ke.c(parcel, 3, dataDeleteRequest.c(), false);
        ke.c(parcel, 4, dataDeleteRequest.d(), false);
        ke.c(parcel, 5, dataDeleteRequest.e(), false);
        ke.a(parcel, 6, dataDeleteRequest.f());
        ke.a(parcel, 7, dataDeleteRequest.g());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataDeleteRequest createFromParcel(Parcel parcel) {
        long j = 0;
        ArrayList arrayList = null;
        boolean z = false;
        int b = a.b(parcel);
        boolean z2 = false;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        long j2 = 0;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    j2 = a.i(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    arrayList3 = a.c(parcel, a, DataSource.CREATOR);
                    break;
                case 4:
                    arrayList2 = a.c(parcel, a, DataType.CREATOR);
                    break;
                case 5:
                    arrayList = a.c(parcel, a, Session.CREATOR);
                    break;
                case 6:
                    z2 = a.c(parcel, a);
                    break;
                case 7:
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
        return new DataDeleteRequest(i, j2, j, arrayList3, arrayList2, arrayList, z2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataDeleteRequest[] newArray(int i) {
        return new DataDeleteRequest[i];
    }
}
