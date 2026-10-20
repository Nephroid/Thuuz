package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class oj implements Parcelable.Creator<BleDevice> {
    public static void a(BleDevice bleDevice, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, bleDevice.a(), false);
        ke.a(parcel, 1000, bleDevice.e());
        ke.a(parcel, 2, bleDevice.b(), false);
        ke.b(parcel, 3, bleDevice.c(), false);
        ke.c(parcel, 4, bleDevice.d(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public BleDevice createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList<String> arrayList2 = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str2 = a.o(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    arrayList2 = a.C(parcel, a);
                    break;
                case 4:
                    arrayList = a.c(parcel, a, DataType.CREATOR);
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
        return new BleDevice(i, str2, str, arrayList2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public BleDevice[] newArray(int i) {
        return new BleDevice[i];
    }
}
