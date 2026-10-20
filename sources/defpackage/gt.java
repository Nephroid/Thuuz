package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class gt implements Parcelable.Creator<ApplicationMetadata> {
    public static void a(ApplicationMetadata applicationMetadata, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, applicationMetadata.a());
        ke.a(parcel, 2, applicationMetadata.b(), false);
        ke.a(parcel, 3, applicationMetadata.c(), false);
        ke.c(parcel, 4, applicationMetadata.f(), false);
        ke.b(parcel, 5, applicationMetadata.d, false);
        ke.a(parcel, 6, applicationMetadata.d(), false);
        ke.a(parcel, 7, (Parcelable) applicationMetadata.e(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ApplicationMetadata createFromParcel(Parcel parcel) {
        Uri uri = null;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        ArrayList<String> arrayList = null;
        ArrayList arrayList2 = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str3 = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    arrayList2 = a.c(parcel, a, WebImage.CREATOR);
                    break;
                case 5:
                    arrayList = a.C(parcel, a);
                    break;
                case 6:
                    str = a.o(parcel, a);
                    break;
                case 7:
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
        return new ApplicationMetadata(i, str3, str2, arrayList2, arrayList, str, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ApplicationMetadata[] newArray(int i) {
        return new ApplicationMetadata[i];
    }
}
