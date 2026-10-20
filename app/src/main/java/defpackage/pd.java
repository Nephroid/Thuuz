package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class pd implements Parcelable.Creator<StartBleScanRequest> {
    public static void a(StartBleScanRequest startBleScanRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, startBleScanRequest.a(), false);
        ke.a(parcel, 1000, startBleScanRequest.d());
        ke.a(parcel, 2, startBleScanRequest.b(), false);
        ke.a(parcel, 3, startBleScanRequest.c());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StartBleScanRequest createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int i = 0;
        int b = a.b(parcel);
        ArrayList arrayList = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList = a.c(parcel, a, DataType.CREATOR);
                    break;
                case 2:
                    iBinder = a.p(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
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
        return new StartBleScanRequest(i2, arrayList, iBinder, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StartBleScanRequest[] newArray(int i) {
        return new StartBleScanRequest[i];
    }
}
