package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;

/* loaded from: classes.dex */
public class aui implements Parcelable.Creator<WalletFragmentInitParams> {
    public static void a(WalletFragmentInitParams walletFragmentInitParams, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, walletFragmentInitParams.a);
        ke.a(parcel, 2, walletFragmentInitParams.a(), false);
        ke.a(parcel, 3, (Parcelable) walletFragmentInitParams.b(), i, false);
        ke.a(parcel, 4, walletFragmentInitParams.c());
        ke.a(parcel, 5, (Parcelable) walletFragmentInitParams.d(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalletFragmentInitParams createFromParcel(Parcel parcel) {
        MaskedWallet maskedWallet = null;
        int b = a.b(parcel);
        int i = 0;
        int i2 = -1;
        MaskedWalletRequest maskedWalletRequest = null;
        String str = null;
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
                    maskedWalletRequest = (MaskedWalletRequest) a.a(parcel, a, MaskedWalletRequest.CREATOR);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    maskedWallet = (MaskedWallet) a.a(parcel, a, MaskedWallet.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new WalletFragmentInitParams(i, str, maskedWalletRequest, i2, maskedWallet);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalletFragmentInitParams[] newArray(int i) {
        return new WalletFragmentInitParams[i];
    }
}
