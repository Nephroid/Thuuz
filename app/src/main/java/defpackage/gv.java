package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.internal.safeparcel.a;

/* loaded from: classes.dex */
public class gv implements Parcelable.Creator<LaunchOptions> {
    public static void a(LaunchOptions launchOptions, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, launchOptions.a());
        ke.a(parcel, 2, launchOptions.b());
        ke.a(parcel, 3, launchOptions.c(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LaunchOptions createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    z = a.c(parcel, a);
                    break;
                case 3:
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
        return new LaunchOptions(i, z, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LaunchOptions[] newArray(int i) {
        return new LaunchOptions[i];
    }
}
