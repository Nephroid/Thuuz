package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.wallet.Address;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.InstrumentInfo;
import com.google.android.gms.wallet.ProxyCard;

/* loaded from: classes.dex */
public class auh implements Parcelable.Creator<FullWallet> {
    public static void a(FullWallet fullWallet, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, fullWallet.a());
        ke.a(parcel, 2, fullWallet.a, false);
        ke.a(parcel, 3, fullWallet.b, false);
        ke.a(parcel, 4, (Parcelable) fullWallet.c, i, false);
        ke.a(parcel, 5, fullWallet.d, false);
        ke.a(parcel, 6, (Parcelable) fullWallet.e, i, false);
        ke.a(parcel, 7, (Parcelable) fullWallet.f, i, false);
        ke.a(parcel, 8, fullWallet.g, false);
        ke.a(parcel, 9, (Parcelable) fullWallet.h, i, false);
        ke.a(parcel, 10, (Parcelable) fullWallet.i, i, false);
        ke.a(parcel, 11, (Parcelable[]) fullWallet.j, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FullWallet createFromParcel(Parcel parcel) {
        InstrumentInfo[] instrumentInfoArr = null;
        int b = a.b(parcel);
        int i = 0;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        String[] strArr = null;
        Address address = null;
        Address address2 = null;
        String str = null;
        ProxyCard proxyCard = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str3 = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    proxyCard = (ProxyCard) a.a(parcel, a, ProxyCard.CREATOR);
                    break;
                case 5:
                    str = a.o(parcel, a);
                    break;
                case 6:
                    address2 = (Address) a.a(parcel, a, Address.CREATOR);
                    break;
                case 7:
                    address = (Address) a.a(parcel, a, Address.CREATOR);
                    break;
                case 8:
                    strArr = a.A(parcel, a);
                    break;
                case 9:
                    userAddress2 = (UserAddress) a.a(parcel, a, UserAddress.CREATOR);
                    break;
                case 10:
                    userAddress = (UserAddress) a.a(parcel, a, UserAddress.CREATOR);
                    break;
                case 11:
                    instrumentInfoArr = (InstrumentInfo[]) a.b(parcel, a, InstrumentInfo.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new FullWallet(i, str3, str2, proxyCard, str, address2, address, strArr, userAddress2, userAddress, instrumentInfoArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FullWallet[] newArray(int i) {
        return new FullWallet[i];
    }
}
