package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.wallet.Address;
import com.google.android.gms.wallet.InstrumentInfo;
import com.google.android.gms.wallet.LoyaltyWalletObject;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.OfferWalletObject;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class aup implements Parcelable.Creator<MaskedWallet> {
    public static void a(MaskedWallet maskedWallet, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, maskedWallet.a());
        ke.a(parcel, 2, maskedWallet.a, false);
        ke.a(parcel, 3, maskedWallet.b, false);
        ke.a(parcel, 4, maskedWallet.c, false);
        ke.a(parcel, 5, maskedWallet.d, false);
        ke.a(parcel, 6, (Parcelable) maskedWallet.e, i, false);
        ke.a(parcel, 7, (Parcelable) maskedWallet.f, i, false);
        ke.a(parcel, 8, (Parcelable[]) maskedWallet.g, i, false);
        ke.a(parcel, 9, (Parcelable[]) maskedWallet.h, i, false);
        ke.a(parcel, 10, (Parcelable) maskedWallet.i, i, false);
        ke.a(parcel, 11, (Parcelable) maskedWallet.j, i, false);
        ke.a(parcel, 12, (Parcelable[]) maskedWallet.k, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MaskedWallet createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String[] strArr = null;
        String str3 = null;
        Address address = null;
        Address address2 = null;
        LoyaltyWalletObject[] loyaltyWalletObjectArr = null;
        OfferWalletObject[] offerWalletObjectArr = null;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        InstrumentInfo[] instrumentInfoArr = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    strArr = a.A(parcel, a);
                    break;
                case 5:
                    str3 = a.o(parcel, a);
                    break;
                case 6:
                    address = (Address) a.a(parcel, a, Address.CREATOR);
                    break;
                case 7:
                    address2 = (Address) a.a(parcel, a, Address.CREATOR);
                    break;
                case 8:
                    loyaltyWalletObjectArr = (LoyaltyWalletObject[]) a.b(parcel, a, LoyaltyWalletObject.CREATOR);
                    break;
                case 9:
                    offerWalletObjectArr = (OfferWalletObject[]) a.b(parcel, a, OfferWalletObject.CREATOR);
                    break;
                case 10:
                    userAddress = (UserAddress) a.a(parcel, a, UserAddress.CREATOR);
                    break;
                case 11:
                    userAddress2 = (UserAddress) a.a(parcel, a, UserAddress.CREATOR);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
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
        return new MaskedWallet(i, str, str2, strArr, str3, address, address2, loyaltyWalletObjectArr, offerWalletObjectArr, userAddress, userAddress2, instrumentInfoArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MaskedWallet[] newArray(int i) {
        return new MaskedWallet[i];
    }
}
