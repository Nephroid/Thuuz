package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.a;

/* loaded from: classes.dex */
public class iv implements Parcelable.Creator<WebImage> {
    public static void a(WebImage webImage, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, webImage.a());
        ke.a(parcel, 2, (Parcelable) webImage.b(), i, false);
        ke.a(parcel, 3, webImage.c());
        ke.a(parcel, 4, webImage.d());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WebImage createFromParcel(Parcel parcel) {
        int g;
        int i;
        Uri uri;
        int i2;
        int i3 = 0;
        int b = a.b(parcel);
        Uri uri2 = null;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    int i6 = i3;
                    i = i4;
                    uri = uri2;
                    i2 = a.g(parcel, a);
                    g = i6;
                    break;
                case 2:
                    i2 = i5;
                    int i7 = i4;
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    g = i3;
                    i = i7;
                    break;
                case 3:
                    uri = uri2;
                    i2 = i5;
                    int i8 = i3;
                    i = a.g(parcel, a);
                    g = i8;
                    break;
                case 4:
                    g = a.g(parcel, a);
                    i = i4;
                    uri = uri2;
                    i2 = i5;
                    break;
                default:
                    a.b(parcel, a);
                    g = i3;
                    i = i4;
                    uri = uri2;
                    i2 = i5;
                    break;
            }
            i5 = i2;
            uri2 = uri;
            i4 = i;
            i3 = g;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new WebImage(i5, uri2, i4, i3);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public WebImage[] newArray(int i) {
        return new WebImage[i];
    }
}
