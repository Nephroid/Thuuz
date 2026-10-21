package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.LineItem;

/* loaded from: classes.dex */
public class aun implements Parcelable.Creator<LineItem> {
    public static void a(LineItem lineItem, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, lineItem.a());
        ke.a(parcel, 2, lineItem.a, false);
        ke.a(parcel, 3, lineItem.b, false);
        ke.a(parcel, 4, lineItem.c, false);
        ke.a(parcel, 5, lineItem.d, false);
        ke.a(parcel, 6, lineItem.e);
        ke.a(parcel, 7, lineItem.f, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LineItem createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    str5 = a.o(parcel, a);
                    break;
                case 3:
                    str4 = a.o(parcel, a);
                    break;
                case 4:
                    str3 = a.o(parcel, a);
                    break;
                case 5:
                    str2 = a.o(parcel, a);
                    break;
                case 6:
                    i = a.g(parcel, a);
                    break;
                case 7:
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
        return new LineItem(i2, str5, str4, str3, str2, i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LineItem[] newArray(int i) {
        return new LineItem[i];
    }
}
