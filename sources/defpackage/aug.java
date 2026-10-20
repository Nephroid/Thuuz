package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.LoyaltyWalletObject;
import com.google.android.gms.wallet.OfferWalletObject;
import com.google.android.gms.wallet.d;

/* loaded from: classes.dex */
public class aug implements Parcelable.Creator<d> {
    public static void a(d dVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, dVar.a());
        ke.a(parcel, 2, (Parcelable) dVar.a, i, false);
        ke.a(parcel, 3, (Parcelable) dVar.b, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d createFromParcel(Parcel parcel) {
        OfferWalletObject offerWalletObject;
        LoyaltyWalletObject loyaltyWalletObject;
        int i;
        OfferWalletObject offerWalletObject2 = null;
        int b = a.b(parcel);
        int i2 = 0;
        LoyaltyWalletObject loyaltyWalletObject2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    OfferWalletObject offerWalletObject3 = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    i = a.g(parcel, a);
                    offerWalletObject = offerWalletObject3;
                    break;
                case 2:
                    LoyaltyWalletObject loyaltyWalletObject3 = (LoyaltyWalletObject) a.a(parcel, a, LoyaltyWalletObject.CREATOR);
                    i = i2;
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject3;
                    break;
                case 3:
                    offerWalletObject = (OfferWalletObject) a.a(parcel, a, OfferWalletObject.CREATOR);
                    loyaltyWalletObject = loyaltyWalletObject2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    i = i2;
                    break;
            }
            i2 = i;
            loyaltyWalletObject2 = loyaltyWalletObject;
            offerWalletObject2 = offerWalletObject;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new d(i2, loyaltyWalletObject2, offerWalletObject2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d[] newArray(int i) {
        return new d[i];
    }
}
