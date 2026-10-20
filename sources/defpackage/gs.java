package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.GoogleAuthApiResponse;
import com.google.android.gms.common.internal.safeparcel.a;

/* loaded from: classes.dex */
public class gs implements Parcelable.Creator<GoogleAuthApiResponse> {
    public static void a(GoogleAuthApiResponse googleAuthApiResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, googleAuthApiResponse.b);
        ke.a(parcel, 1000, googleAuthApiResponse.a);
        ke.a(parcel, 2, googleAuthApiResponse.c, false);
        ke.a(parcel, 3, googleAuthApiResponse.d, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GoogleAuthApiResponse createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int b = a.b(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    bundle = a.q(parcel, a);
                    break;
                case 3:
                    bArr = a.r(parcel, a);
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
        return new GoogleAuthApiResponse(i2, i, bundle, bArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GoogleAuthApiResponse[] newArray(int i) {
        return new GoogleAuthApiResponse[i];
    }
}
