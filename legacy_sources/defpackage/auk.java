package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;

/* loaded from: classes.dex */
public class auk implements Parcelable.Creator<WalletFragmentStyle> {
    public static void a(WalletFragmentStyle walletFragmentStyle, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, walletFragmentStyle.a);
        ke.a(parcel, 2, walletFragmentStyle.b, false);
        ke.a(parcel, 3, walletFragmentStyle.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalletFragmentStyle createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    bundle = a.q(parcel, a);
                    break;
                case 3:
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
        return new WalletFragmentStyle(i2, bundle, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalletFragmentStyle[] newArray(int i) {
        return new WalletFragmentStyle[i];
    }
}
