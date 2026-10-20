package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class gu implements Parcelable.Creator<CastDevice> {
    public static void a(CastDevice castDevice, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, castDevice.a());
        ke.a(parcel, 2, castDevice.b(), false);
        ke.a(parcel, 3, castDevice.a, false);
        ke.a(parcel, 4, castDevice.c(), false);
        ke.a(parcel, 5, castDevice.d(), false);
        ke.a(parcel, 6, castDevice.e(), false);
        ke.a(parcel, 7, castDevice.f());
        ke.c(parcel, 8, castDevice.g(), false);
        ke.a(parcel, 9, castDevice.h());
        ke.a(parcel, 10, castDevice.i());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CastDevice createFromParcel(Parcel parcel) {
        int i = 0;
        ArrayList arrayList = null;
        int b = a.b(parcel);
        int i2 = 0;
        int i3 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int i4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i4 = a.g(parcel, a);
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
                    str = a.o(parcel, a);
                    break;
                case 7:
                    i3 = a.g(parcel, a);
                    break;
                case 8:
                    arrayList = a.c(parcel, a, WebImage.CREATOR);
                    break;
                case 9:
                    i2 = a.g(parcel, a);
                    break;
                case 10:
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
        return new CastDevice(i4, str5, str4, str3, str2, str, i3, arrayList, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CastDevice[] newArray(int i) {
        return new CastDevice[i];
    }
}
