package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.realtime.internal.ParcelableIndexReference;

/* loaded from: classes.dex */
public class ny implements Parcelable.Creator<ParcelableIndexReference> {
    public static void a(ParcelableIndexReference parcelableIndexReference, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, parcelableIndexReference.a);
        ke.a(parcel, 2, parcelableIndexReference.b, false);
        ke.a(parcel, 3, parcelableIndexReference.c);
        ke.a(parcel, 4, parcelableIndexReference.d);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableIndexReference createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
                    break;
                case 4:
                    z = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ParcelableIndexReference(i2, str, i, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableIndexReference[] newArray(int i) {
        return new ParcelableIndexReference[i];
    }
}
