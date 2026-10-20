package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.query.internal.MatchAllFilter;

/* loaded from: classes.dex */
public class ni implements Parcelable.Creator<MatchAllFilter> {
    public static void a(MatchAllFilter matchAllFilter, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, matchAllFilter.a);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MatchAllFilter createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
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
        return new MatchAllFilter(i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MatchAllFilter[] newArray(int i) {
        return new MatchAllFilter[i];
    }
}
