package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.CountrySpecification;
import com.google.android.gms.wallet.MaskedWalletRequest;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class auq implements Parcelable.Creator<MaskedWalletRequest> {
    public static void a(MaskedWalletRequest maskedWalletRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, maskedWalletRequest.a());
        ke.a(parcel, 2, maskedWalletRequest.a, false);
        ke.a(parcel, 3, maskedWalletRequest.b);
        ke.a(parcel, 4, maskedWalletRequest.c);
        ke.a(parcel, 5, maskedWalletRequest.d);
        ke.a(parcel, 6, maskedWalletRequest.e, false);
        ke.a(parcel, 7, maskedWalletRequest.f, false);
        ke.a(parcel, 8, maskedWalletRequest.g, false);
        ke.a(parcel, 9, (Parcelable) maskedWalletRequest.h, i, false);
        ke.a(parcel, 10, maskedWalletRequest.i);
        ke.a(parcel, 11, maskedWalletRequest.j);
        ke.a(parcel, 12, (Parcelable[]) maskedWalletRequest.k, i, false);
        ke.a(parcel, 13, maskedWalletRequest.l);
        ke.a(parcel, 14, maskedWalletRequest.m);
        ke.c(parcel, 15, maskedWalletRequest.n, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MaskedWalletRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Cart cart = null;
        boolean z4 = false;
        boolean z5 = false;
        CountrySpecification[] countrySpecificationArr = null;
        boolean z6 = true;
        boolean z7 = true;
        ArrayList arrayList = null;
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
                    z = a.c(parcel, a);
                    break;
                case 4:
                    z2 = a.c(parcel, a);
                    break;
                case 5:
                    z3 = a.c(parcel, a);
                    break;
                case 6:
                    str2 = a.o(parcel, a);
                    break;
                case 7:
                    str3 = a.o(parcel, a);
                    break;
                case 8:
                    str4 = a.o(parcel, a);
                    break;
                case 9:
                    cart = (Cart) a.a(parcel, a, Cart.CREATOR);
                    break;
                case 10:
                    z4 = a.c(parcel, a);
                    break;
                case 11:
                    z5 = a.c(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    countrySpecificationArr = (CountrySpecification[]) a.b(parcel, a, CountrySpecification.CREATOR);
                    break;
                case HTTP.CR /* 13 */:
                    z6 = a.c(parcel, a);
                    break;
                case 14:
                    z7 = a.c(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    arrayList = a.c(parcel, a, com.google.android.gms.identity.intents.model.CountrySpecification.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new MaskedWalletRequest(i, str, z, z2, z3, str2, str3, str4, cart, z4, z5, countrySpecificationArr, z6, z7, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MaskedWalletRequest[] newArray(int i) {
        return new MaskedWalletRequest[i];
    }
}
