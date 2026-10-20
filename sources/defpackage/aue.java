package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.LineItem;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class aue implements Parcelable.Creator<Cart> {
    public static void a(Cart cart, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, cart.a());
        ke.a(parcel, 2, cart.a, false);
        ke.a(parcel, 3, cart.b, false);
        ke.c(parcel, 4, cart.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Cart createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = new ArrayList();
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
                    arrayList = a.c(parcel, a, LineItem.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new Cart(i, str2, str, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Cart[] newArray(int i) {
        return new Cart[i];
    }
}
