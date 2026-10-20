package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;

/* loaded from: classes.dex */
public class auj implements Parcelable.Creator<WalletFragmentOptions> {
    public static void a(WalletFragmentOptions walletFragmentOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, walletFragmentOptions.a);
        ke.a(parcel, 2, walletFragmentOptions.a());
        ke.a(parcel, 3, walletFragmentOptions.b());
        ke.a(parcel, 4, (Parcelable) walletFragmentOptions.c(), i, false);
        ke.a(parcel, 5, walletFragmentOptions.d());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalletFragmentOptions createFromParcel(Parcel parcel) {
        int i = 1;
        int i2 = 0;
        int b = a.b(parcel);
        WalletFragmentStyle walletFragmentStyle = null;
        int i3 = 1;
        int i4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i4 = a.g(parcel, a);
                    break;
                case 2:
                    i3 = a.g(parcel, a);
                    break;
                case 3:
                    i2 = a.g(parcel, a);
                    break;
                case 4:
                    walletFragmentStyle = (WalletFragmentStyle) a.a(parcel, a, WalletFragmentStyle.CREATOR);
                    break;
                case 5:
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
        return new WalletFragmentOptions(i4, i3, i2, walletFragmentStyle, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalletFragmentOptions[] newArray(int i) {
        return new WalletFragmentOptions[i];
    }
}
