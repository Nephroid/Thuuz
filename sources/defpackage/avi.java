package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.c;

/* loaded from: classes.dex */
public class avi implements Parcelable.Creator<c> {
    public static void a(c cVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, cVar.a);
        ke.a(parcel, 2, cVar.a(), false);
        ke.a(parcel, 3, cVar.b(), false);
        ke.a(parcel, 4, cVar.c());
        ke.a(parcel, 5, cVar.d());
        ke.a(parcel, 6, cVar.g());
        ke.a(parcel, 7, cVar.e());
        ke.a(parcel, 8, cVar.f(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int b = a.b(parcel);
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    str3 = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    i = a.g(parcel, a);
                    break;
                case 6:
                    z2 = a.c(parcel, a);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    break;
                case 8:
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
        return new c(i3, str3, str2, i2, i, z2, z, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c[] newArray(int i) {
        return new c[i];
    }
}
