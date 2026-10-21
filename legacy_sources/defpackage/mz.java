package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.internal.ComparisonFilter;
import com.google.android.gms.drive.query.internal.Operator;

/* loaded from: classes.dex */
public class mz implements Parcelable.Creator<ComparisonFilter> {
    public static void a(ComparisonFilter comparisonFilter, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, comparisonFilter.c);
        ke.a(parcel, 1, (Parcelable) comparisonFilter.a, i, false);
        ke.a(parcel, 2, (Parcelable) comparisonFilter.b, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ComparisonFilter createFromParcel(Parcel parcel) {
        MetadataBundle metadataBundle;
        Operator operator;
        int i;
        MetadataBundle metadataBundle2 = null;
        int b = a.b(parcel);
        int i2 = 0;
        Operator operator2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    Operator operator3 = (Operator) a.a(parcel, a, Operator.CREATOR);
                    i = i2;
                    metadataBundle = metadataBundle2;
                    operator = operator3;
                    break;
                case 2:
                    metadataBundle = (MetadataBundle) a.a(parcel, a, MetadataBundle.CREATOR);
                    operator = operator2;
                    i = i2;
                    break;
                case 1000:
                    MetadataBundle metadataBundle3 = metadataBundle2;
                    operator = operator2;
                    i = a.g(parcel, a);
                    metadataBundle = metadataBundle3;
                    break;
                default:
                    a.b(parcel, a);
                    metadataBundle = metadataBundle2;
                    operator = operator2;
                    i = i2;
                    break;
            }
            i2 = i;
            operator2 = operator;
            metadataBundle2 = metadataBundle;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ComparisonFilter(i2, operator2, metadataBundle2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ComparisonFilter[] newArray(int i) {
        return new ComparisonFilter[i];
    }
}
