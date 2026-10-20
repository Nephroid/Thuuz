package defpackage;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.PutDataRequest;

/* loaded from: classes.dex */
public class avj implements Parcelable.Creator<PutDataRequest> {
    public static void a(PutDataRequest putDataRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, putDataRequest.a);
        ke.a(parcel, 2, (Parcelable) putDataRequest.a(), i, false);
        ke.a(parcel, 4, putDataRequest.c(), false);
        ke.a(parcel, 5, putDataRequest.b(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PutDataRequest createFromParcel(Parcel parcel) {
        byte[] r;
        Bundle bundle;
        Uri uri;
        int i;
        byte[] bArr = null;
        int b = a.b(parcel);
        int i2 = 0;
        Bundle bundle2 = null;
        Uri uri2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    byte[] bArr2 = bArr;
                    bundle = bundle2;
                    uri = uri2;
                    i = a.g(parcel, a);
                    r = bArr2;
                    break;
                case 2:
                    i = i2;
                    Bundle bundle3 = bundle2;
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    r = bArr;
                    bundle = bundle3;
                    break;
                case 3:
                default:
                    a.b(parcel, a);
                    r = bArr;
                    bundle = bundle2;
                    uri = uri2;
                    i = i2;
                    break;
                case 4:
                    uri = uri2;
                    i = i2;
                    byte[] bArr3 = bArr;
                    bundle = a.q(parcel, a);
                    r = bArr3;
                    break;
                case 5:
                    r = a.r(parcel, a);
                    bundle = bundle2;
                    uri = uri2;
                    i = i2;
                    break;
            }
            i2 = i;
            uri2 = uri;
            bundle2 = bundle;
            bArr = r;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new PutDataRequest(i2, uri2, bundle2, bArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PutDataRequest[] newArray(int i) {
        return new PutDataRequest[i];
    }
}
