package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.OfferWalletObject;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

/* loaded from: classes.dex */
public class aus implements Parcelable.Creator<OfferWalletObject> {
    public static void a(OfferWalletObject offerWalletObject, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, offerWalletObject.a());
        ke.a(parcel, 2, offerWalletObject.a, false);
        ke.a(parcel, 3, offerWalletObject.b, false);
        ke.a(parcel, 4, (Parcelable) offerWalletObject.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OfferWalletObject createFromParcel(Parcel parcel) {
        CommonWalletObject commonWalletObject = null;
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
                    commonWalletObject = (CommonWalletObject) a.a(parcel, a, CommonWalletObject.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new OfferWalletObject(i, str2, str, commonWalletObject);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OfferWalletObject[] newArray(int i) {
        return new OfferWalletObject[i];
    }
}
