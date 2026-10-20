package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.Address;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class aud implements Parcelable.Creator<Address> {
    public static void a(Address address, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, address.a());
        ke.a(parcel, 2, address.a, false);
        ke.a(parcel, 3, address.b, false);
        ke.a(parcel, 4, address.c, false);
        ke.a(parcel, 5, address.d, false);
        ke.a(parcel, 6, address.e, false);
        ke.a(parcel, 7, address.f, false);
        ke.a(parcel, 8, address.g, false);
        ke.a(parcel, 9, address.h, false);
        ke.a(parcel, 10, address.i, false);
        ke.a(parcel, 11, address.j);
        ke.a(parcel, 12, address.k, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Address createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        boolean z = false;
        String str10 = null;
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
                    str3 = a.o(parcel, a);
                    break;
                case 5:
                    str4 = a.o(parcel, a);
                    break;
                case 6:
                    str5 = a.o(parcel, a);
                    break;
                case 7:
                    str6 = a.o(parcel, a);
                    break;
                case 8:
                    str7 = a.o(parcel, a);
                    break;
                case 9:
                    str8 = a.o(parcel, a);
                    break;
                case 10:
                    str9 = a.o(parcel, a);
                    break;
                case 11:
                    z = a.c(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    str10 = a.o(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new Address(i, str, str2, str3, str4, str5, str6, str7, str8, str9, z, str10);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Address[] newArray(int i) {
        return new Address[i];
    }
}
