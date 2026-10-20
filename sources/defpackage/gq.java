package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.AccountChangeEvent;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class gq implements Parcelable.Creator<AccountChangeEventsResponse> {
    public static void a(AccountChangeEventsResponse accountChangeEventsResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, accountChangeEventsResponse.a);
        ke.c(parcel, 2, accountChangeEventsResponse.b, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AccountChangeEventsResponse createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    arrayList = a.c(parcel, a, AccountChangeEvent.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new AccountChangeEventsResponse(i, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AccountChangeEventsResponse[] newArray(int i) {
        return new AccountChangeEventsResponse[i];
    }
}
