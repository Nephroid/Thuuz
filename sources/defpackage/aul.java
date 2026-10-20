package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWalletRequest;

/* loaded from: classes.dex */
public class aul implements Parcelable.Creator<FullWalletRequest> {
    public static void a(FullWalletRequest fullWalletRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, fullWalletRequest.a());
        ke.a(parcel, 2, fullWalletRequest.a, false);
        ke.a(parcel, 3, fullWalletRequest.b, false);
        ke.a(parcel, 4, (Parcelable) fullWalletRequest.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FullWalletRequest createFromParcel(Parcel parcel) {
        Cart cart = null;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    cart = (Cart) a.a(parcel, a, Cart.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new FullWalletRequest(i, str2, str, cart);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FullWalletRequest[] newArray(int i) {
        return new FullWalletRequest[i];
    }
}
