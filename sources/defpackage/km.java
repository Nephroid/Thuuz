package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.UserMetadata;

/* loaded from: classes.dex */
public class km implements Parcelable.Creator<UserMetadata> {
    public static void a(UserMetadata userMetadata, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, userMetadata.a);
        ke.a(parcel, 2, userMetadata.b, false);
        ke.a(parcel, 3, userMetadata.c, false);
        ke.a(parcel, 4, userMetadata.d, false);
        ke.a(parcel, 5, userMetadata.e);
        ke.a(parcel, 6, userMetadata.f, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public UserMetadata createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int b = a.b(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str4 = a.o(parcel, a);
                    break;
                case 3:
                    str3 = a.o(parcel, a);
                    break;
                case 4:
                    str2 = a.o(parcel, a);
                    break;
                case 5:
                    z = a.c(parcel, a);
                    break;
                case 6:
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
        return new UserMetadata(i, str4, str3, str2, z, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public UserMetadata[] newArray(int i) {
        return new UserMetadata[i];
    }
}
