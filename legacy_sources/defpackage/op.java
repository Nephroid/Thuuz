package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.Device;

/* loaded from: classes.dex */
public class op implements Parcelable.Creator<Device> {
    public static void a(Device device, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, device.a(), false);
        ke.a(parcel, 1000, device.h());
        ke.a(parcel, 2, device.b(), false);
        ke.a(parcel, 3, device.c(), false);
        ke.a(parcel, 4, device.g(), false);
        ke.a(parcel, 5, device.d());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Device createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str4 = a.o(parcel, a);
                    break;
                case 2:
                    str3 = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    str = a.o(parcel, a);
                    break;
                case 5:
                    i = a.g(parcel, a);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new Device(i2, str4, str3, str2, str, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Device[] newArray(int i) {
        return new Device[i];
    }
}
