package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;

/* loaded from: classes.dex */
public class nb implements Parcelable.Creator<FieldWithSortOrder> {
    public static void a(FieldWithSortOrder fieldWithSortOrder, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1000, fieldWithSortOrder.c);
        ke.a(parcel, 1, fieldWithSortOrder.a, false);
        ke.a(parcel, 2, fieldWithSortOrder.b);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FieldWithSortOrder createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    z = a.c(parcel, a);
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
        return new FieldWithSortOrder(i, str, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public FieldWithSortOrder[] newArray(int i) {
        return new FieldWithSortOrder[i];
    }
}
