package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.internal.ConnectionInfo;

/* loaded from: classes.dex */
public class qq implements Parcelable.Creator<ConnectionInfo> {
    public static void a(ConnectionInfo connectionInfo, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, connectionInfo.b(), false);
        ke.a(parcel, 1000, connectionInfo.a());
        ke.a(parcel, 2, connectionInfo.c());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ConnectionInfo createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
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
        return new ConnectionInfo(i2, str, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ConnectionInfo[] newArray(int i) {
        return new ConnectionInfo[i];
    }
}
