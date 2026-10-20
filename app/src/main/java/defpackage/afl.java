package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.jd;
import com.google.android.gms.internal.jf;

/* loaded from: classes.dex */
public class afl implements Parcelable.Creator<jd> {
    public static void a(jd jdVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, jdVar.a());
        ke.a(parcel, 2, (Parcelable) jdVar.b(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jd createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        jf jfVar = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    jfVar = (jf) a.a(parcel, a, jf.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new jd(i, jfVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jd[] newArray(int i) {
        return new jd[i];
    }
}
