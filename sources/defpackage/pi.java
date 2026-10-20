package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.b;

/* loaded from: classes.dex */
public class pi implements Parcelable.Creator<b> {
    public static void a(b bVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, bVar.a(), false);
        ke.a(parcel, 1000, bVar.c());
        ke.a(parcel, 2, (Parcelable) bVar.b(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b createFromParcel(Parcel parcel) {
        BleDevice bleDevice = null;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    bleDevice = (BleDevice) a.a(parcel, a, BleDevice.CREATOR);
                    break;
                case 1000:
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
        return new b(i, str, bleDevice);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b[] newArray(int i) {
        return new b[i];
    }
}
