package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.CountrySpecification;

/* loaded from: classes.dex */
public class auf implements Parcelable.Creator<CountrySpecification> {
    public static void a(CountrySpecification countrySpecification, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, countrySpecification.a());
        ke.a(parcel, 2, countrySpecification.a, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CountrySpecification createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
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
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CountrySpecification(i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CountrySpecification[] newArray(int i) {
        return new CountrySpecification[i];
    }
}
