package defpackage;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.he;
import com.google.android.gms.internal.hi;

/* loaded from: classes.dex */
public class aej implements Parcelable.Creator<he> {
    public static void a(he heVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable[]) heVar.b, i, false);
        ke.a(parcel, 1000, heVar.a);
        ke.a(parcel, 2, heVar.c, false);
        ke.a(parcel, 3, heVar.d);
        ke.a(parcel, 4, (Parcelable) heVar.e, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public he createFromParcel(Parcel parcel) {
        boolean z = false;
        Account account = null;
        int b = a.b(parcel);
        String str = null;
        hi[] hiVarArr = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    hiVarArr = (hi[]) a.b(parcel, a, hi.CREATOR);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    z = a.c(parcel, a);
                    break;
                case 4:
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
        return new he(i, hiVarArr, str, z, account);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public he[] newArray(int i) {
        return new he[i];
    }
}
