package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.internal.CreateContentsRequest;

/* loaded from: classes.dex */
public class lq implements Parcelable.Creator<CreateContentsRequest> {
    public static void a(CreateContentsRequest createContentsRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, createContentsRequest.a);
        ke.a(parcel, 2, createContentsRequest.b);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CreateContentsRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        int i2 = 536870912;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CreateContentsRequest(i, i2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CreateContentsRequest[] newArray(int i) {
        return new CreateContentsRequest[i];
    }
}
