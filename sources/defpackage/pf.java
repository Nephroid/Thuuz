package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.ae;

/* loaded from: classes.dex */
public class pf implements Parcelable.Creator<ae> {
    public static void a(ae aeVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) aeVar.a(), i, false);
        ke.a(parcel, 1000, aeVar.c());
        ke.a(parcel, 2, aeVar.b());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ae createFromParcel(Parcel parcel) {
        boolean c;
        Subscription subscription;
        int i;
        boolean z = false;
        int b = a.b(parcel);
        Subscription subscription2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    Subscription subscription3 = (Subscription) a.a(parcel, a, Subscription.CREATOR);
                    i = i2;
                    c = z;
                    subscription = subscription3;
                    break;
                case 2:
                    c = a.c(parcel, a);
                    subscription = subscription2;
                    i = i2;
                    break;
                case 1000:
                    boolean z2 = z;
                    subscription = subscription2;
                    i = a.g(parcel, a);
                    c = z2;
                    break;
                default:
                    a.b(parcel, a);
                    c = z;
                    subscription = subscription2;
                    i = i2;
                    break;
            }
            i2 = i;
            subscription2 = subscription;
            z = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ae(i2, subscription2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ae[] newArray(int i) {
        return new ae[i];
    }
}
