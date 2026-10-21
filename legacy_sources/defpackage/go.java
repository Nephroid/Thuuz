package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.AccountChangeEvent;
import com.google.android.gms.common.internal.safeparcel.a;

/* loaded from: classes.dex */
public class go implements Parcelable.Creator<AccountChangeEvent> {
    public static void a(AccountChangeEvent accountChangeEvent, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, accountChangeEvent.a);
        ke.a(parcel, 2, accountChangeEvent.b);
        ke.a(parcel, 3, accountChangeEvent.c, false);
        ke.a(parcel, 4, accountChangeEvent.d);
        ke.a(parcel, 5, accountChangeEvent.e);
        ke.a(parcel, 6, accountChangeEvent.f, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AccountChangeEvent createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int b = a.b(parcel);
        long j = 0;
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    i = a.g(parcel, a);
                    break;
                case 6:
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
        return new AccountChangeEvent(i3, j, str2, i2, i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AccountChangeEvent[] newArray(int i) {
        return new AccountChangeEvent[i];
    }
}
