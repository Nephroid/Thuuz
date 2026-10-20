package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.internal.HasFilter;

/* loaded from: classes.dex */
public class nf implements Parcelable.Creator<HasFilter> {
    public static void a(HasFilter hasFilter, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, hasFilter.b);
        ke.a(parcel, 1, (Parcelable) hasFilter.a, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HasFilter createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    metadataBundle = (MetadataBundle) a.a(parcel, a, MetadataBundle.CREATOR);
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
        return new HasFilter(i, metadataBundle);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HasFilter[] newArray(int i) {
        return new HasFilter[i];
    }
}
