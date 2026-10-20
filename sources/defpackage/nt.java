package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.realtime.internal.event.ValueChangedDetails;

/* loaded from: classes.dex */
public class nt implements Parcelable.Creator<ValueChangedDetails> {
    public static void a(ValueChangedDetails valueChangedDetails, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, valueChangedDetails.a);
        ke.a(parcel, 2, valueChangedDetails.b);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ValueChangedDetails createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
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
        return new ValueChangedDetails(i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ValueChangedDetails[] newArray(int i) {
        return new ValueChangedDetails[i];
    }
}
