package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.realtime.internal.event.ValuesRemovedDetails;

/* loaded from: classes.dex */
public class nv implements Parcelable.Creator<ValuesRemovedDetails> {
    public static void a(ValuesRemovedDetails valuesRemovedDetails, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, valuesRemovedDetails.a);
        ke.a(parcel, 2, valuesRemovedDetails.b);
        ke.a(parcel, 3, valuesRemovedDetails.c);
        ke.a(parcel, 4, valuesRemovedDetails.d);
        ke.a(parcel, 5, valuesRemovedDetails.e, false);
        ke.a(parcel, 6, valuesRemovedDetails.f);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ValuesRemovedDetails createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        String str = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i5 = a.g(parcel, a);
                    break;
                case 2:
                    i4 = a.g(parcel, a);
                    break;
                case 3:
                    i3 = a.g(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    str = a.o(parcel, a);
                    break;
                case 6:
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
        return new ValuesRemovedDetails(i5, i4, i3, i2, str, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ValuesRemovedDetails[] newArray(int i) {
        return new ValuesRemovedDetails[i];
    }
}
