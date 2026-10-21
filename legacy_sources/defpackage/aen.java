package defpackage;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.hm;

/* loaded from: classes.dex */
public class aen implements Parcelable.Creator<hm.a> {
    public static void a(hm.a aVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) aVar.b, i, false);
        ke.a(parcel, 1000, aVar.a);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hm.a createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        Account account = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    account = (Account) a.a(parcel, a, Account.CREATOR);
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
        return new hm.a(i, account);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hm.a[] newArray(int i) {
        return new hm.a[i];
    }
}
