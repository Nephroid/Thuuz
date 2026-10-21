package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.Asset;

/* loaded from: classes.dex */
public class avh implements Parcelable.Creator<Asset> {
    public static void a(Asset asset, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, asset.a);
        ke.a(parcel, 2, asset.a(), false);
        ke.a(parcel, 3, asset.b(), false);
        ke.a(parcel, 4, (Parcelable) asset.b, i, false);
        ke.a(parcel, 5, (Parcelable) asset.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Asset createFromParcel(Parcel parcel) {
        Uri uri = null;
        int b = a.b(parcel);
        int i = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        String str = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    bArr = a.r(parcel, a);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    parcelFileDescriptor = (ParcelFileDescriptor) a.a(parcel, a, ParcelFileDescriptor.CREATOR);
                    break;
                case 5:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new Asset(i, bArr, str, parcelFileDescriptor, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Asset[] newArray(int i) {
        return new Asset[i];
    }
}
