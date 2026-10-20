package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class qe implements Parcelable.Creator<ListSubscriptionsResult> {
    public static void a(ListSubscriptionsResult listSubscriptionsResult, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, listSubscriptionsResult.a(), false);
        ke.a(parcel, 1000, listSubscriptionsResult.c());
        ke.a(parcel, 2, (Parcelable) listSubscriptionsResult.b(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ListSubscriptionsResult createFromParcel(Parcel parcel) {
        Status status = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList = a.c(parcel, a, Subscription.CREATOR);
                    break;
                case 2:
                    status = (Status) a.a(parcel, a, Status.CREATOR);
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
        return new ListSubscriptionsResult(i, arrayList, status);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ListSubscriptionsResult[] newArray(int i) {
        return new ListSubscriptionsResult[i];
    }
}
