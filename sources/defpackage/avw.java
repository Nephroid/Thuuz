package defpackage;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wearable.internal.b;

/* loaded from: classes.dex */
public class avw implements Parcelable.Creator<b> {
    public static void a(b bVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, bVar.a);
        ke.a(parcel, 2, bVar.a(), false);
        ke.a(parcel, 3, (Parcelable[]) bVar.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b createFromParcel(Parcel parcel) {
        IntentFilter[] intentFilterArr = null;
        int b = a.b(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    iBinder = a.p(parcel, a);
                    break;
                case 3:
                    intentFilterArr = (IntentFilter[]) a.b(parcel, a, IntentFilter.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new b(i, iBinder, intentFilterArr);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b[] newArray(int i) {
        return new b[i];
    }
}
