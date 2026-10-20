package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.GoogleAuthApiRequest;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class gr implements Parcelable.Creator<GoogleAuthApiRequest> {
    public static void a(GoogleAuthApiRequest googleAuthApiRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, googleAuthApiRequest.b, false);
        ke.a(parcel, 1000, googleAuthApiRequest.a);
        ke.a(parcel, 2, googleAuthApiRequest.c, false);
        ke.a(parcel, 3, googleAuthApiRequest.d, false);
        ke.a(parcel, 4, googleAuthApiRequest.e, false);
        ke.a(parcel, 5, googleAuthApiRequest.f, false);
        ke.a(parcel, 6, googleAuthApiRequest.g, false);
        ke.b(parcel, 7, googleAuthApiRequest.h, false);
        ke.a(parcel, 8, googleAuthApiRequest.i, false);
        ke.a(parcel, 9, googleAuthApiRequest.j);
        ke.a(parcel, 10, googleAuthApiRequest.k, false);
        ke.a(parcel, 11, googleAuthApiRequest.l, false);
        ke.a(parcel, 12, googleAuthApiRequest.m);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GoogleAuthApiRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Bundle bundle = null;
        String str5 = null;
        ArrayList<String> arrayList = null;
        String str6 = null;
        int i2 = 0;
        Bundle bundle2 = null;
        byte[] bArr = null;
        long j = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    str3 = a.o(parcel, a);
                    break;
                case 4:
                    str4 = a.o(parcel, a);
                    break;
                case 5:
                    bundle = a.q(parcel, a);
                    break;
                case 6:
                    str5 = a.o(parcel, a);
                    break;
                case 7:
                    arrayList = a.C(parcel, a);
                    break;
                case 8:
                    str6 = a.o(parcel, a);
                    break;
                case 9:
                    i2 = a.g(parcel, a);
                    break;
                case 10:
                    bundle2 = a.q(parcel, a);
                    break;
                case 11:
                    bArr = a.r(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    j = a.i(parcel, a);
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
        return new GoogleAuthApiRequest(i, str, str2, str3, str4, bundle, str5, arrayList, str6, i2, bundle2, bArr, j);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GoogleAuthApiRequest[] newArray(int i) {
        return new GoogleAuthApiRequest[i];
    }
}
