package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.SessionReadRequest;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class pw implements Parcelable.Creator<SessionReadRequest> {
    public static void a(SessionReadRequest sessionReadRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, sessionReadRequest.a(), false);
        ke.a(parcel, 1000, sessionReadRequest.j());
        ke.a(parcel, 2, sessionReadRequest.b(), false);
        ke.a(parcel, 3, sessionReadRequest.c());
        ke.a(parcel, 4, sessionReadRequest.d());
        ke.c(parcel, 5, sessionReadRequest.e(), false);
        ke.c(parcel, 6, sessionReadRequest.f(), false);
        ke.a(parcel, 7, sessionReadRequest.g());
        ke.a(parcel, 8, sessionReadRequest.i());
        ke.b(parcel, 9, sessionReadRequest.h(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SessionReadRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        long j = 0;
        long j2 = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        boolean z = false;
        boolean z2 = false;
        ArrayList<String> arrayList3 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    j2 = a.i(parcel, a);
                    break;
                case 5:
                    arrayList = a.c(parcel, a, DataType.CREATOR);
                    break;
                case 6:
                    arrayList2 = a.c(parcel, a, DataSource.CREATOR);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    break;
                case 8:
                    z2 = a.c(parcel, a);
                    break;
                case 9:
                    arrayList3 = a.C(parcel, a);
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
        return new SessionReadRequest(i, str, str2, j, j2, arrayList, arrayList2, z, z2, arrayList3);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SessionReadRequest[] newArray(int i) {
        return new SessionReadRequest[i];
    }
}
