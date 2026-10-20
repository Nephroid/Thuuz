package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;

/* loaded from: classes.dex */
public class aur implements Parcelable.Creator<NotifyTransactionStatusRequest> {
    public static void a(NotifyTransactionStatusRequest notifyTransactionStatusRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, notifyTransactionStatusRequest.a);
        ke.a(parcel, 2, notifyTransactionStatusRequest.b, false);
        ke.a(parcel, 3, notifyTransactionStatusRequest.c);
        ke.a(parcel, 4, notifyTransactionStatusRequest.d, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public NotifyTransactionStatusRequest createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int b = a.b(parcel);
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
                    break;
                case 4:
                    str = a.o(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new NotifyTransactionStatusRequest(i2, str2, i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public NotifyTransactionStatusRequest[] newArray(int i) {
        return new NotifyTransactionStatusRequest[i];
    }
}
