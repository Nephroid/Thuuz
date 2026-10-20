package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.request.UnclaimBleDeviceRequest;

/* loaded from: classes.dex */
public class pg implements Parcelable.Creator<UnclaimBleDeviceRequest> {
    public static void a(UnclaimBleDeviceRequest unclaimBleDeviceRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, unclaimBleDeviceRequest.b());
        ke.a(parcel, 2, unclaimBleDeviceRequest.a(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public UnclaimBleDeviceRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 2:
                    str = a.o(parcel, a);
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
        return new UnclaimBleDeviceRequest(i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public UnclaimBleDeviceRequest[] newArray(int i) {
        return new UnclaimBleDeviceRequest[i];
    }
}
