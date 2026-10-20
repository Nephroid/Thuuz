package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.mo;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ahx implements Parcelable.Creator<me> {
    public static void a(me meVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, meVar.a());
        ke.a(parcel, 1000, meVar.a);
        ke.c(parcel, 2, meVar.b(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public me createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        ArrayList arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    z = a.c(parcel, a);
                    break;
                case 2:
                    arrayList = a.c(parcel, a, mo.CREATOR);
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
        return new me(i, z, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public me[] newArray(int i) {
        return new me[i];
    }
}
