package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class iw implements Parcelable.Creator<ClientSettings.ParcelableClientSettings> {
    public static void a(ClientSettings.ParcelableClientSettings parcelableClientSettings, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, parcelableClientSettings.a(), false);
        ke.a(parcel, 1000, parcelableClientSettings.e());
        ke.b(parcel, 2, parcelableClientSettings.d(), false);
        ke.a(parcel, 3, parcelableClientSettings.b());
        ke.a(parcel, 4, parcelableClientSettings.c(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ClientSettings.ParcelableClientSettings createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        ArrayList<String> arrayList = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str2 = a.o(parcel, a);
                    break;
                case 2:
                    arrayList = a.C(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
                    break;
                case 4:
                    str = a.o(parcel, a);
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
        return new ClientSettings.ParcelableClientSettings(i2, str2, arrayList, i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ClientSettings.ParcelableClientSettings[] newArray(int i) {
        return new ClientSettings.ParcelableClientSettings[i];
    }
}
