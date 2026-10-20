package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.query.internal.FilterHolder;
import com.google.android.gms.drive.query.internal.NotFilter;

/* loaded from: classes.dex */
public class nj implements Parcelable.Creator<NotFilter> {
    public static void a(NotFilter notFilter, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, notFilter.b);
        ke.a(parcel, 1, (Parcelable) notFilter.a, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public NotFilter createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    filterHolder = (FilterHolder) a.a(parcel, a, FilterHolder.CREATOR);
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
        return new NotFilter(i, filterHolder);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public NotFilter[] newArray(int i) {
        return new NotFilter[i];
    }
}
