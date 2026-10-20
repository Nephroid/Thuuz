package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.internal.safeparcel.a;

/* loaded from: classes.dex */
public class gp implements Parcelable.Creator<AccountChangeEventsRequest> {
    public static void a(AccountChangeEventsRequest accountChangeEventsRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, accountChangeEventsRequest.a);
        ke.a(parcel, 2, accountChangeEventsRequest.b);
        ke.a(parcel, 3, accountChangeEventsRequest.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AccountChangeEventsRequest createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    i = a.g(parcel, a);
                    break;
                case 3:
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
        return new AccountChangeEventsRequest(i2, i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AccountChangeEventsRequest[] newArray(int i) {
        return new AccountChangeEventsRequest[i];
    }
}
