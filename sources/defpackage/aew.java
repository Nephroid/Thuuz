package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.il;

/* loaded from: classes.dex */
public class aew implements Parcelable.Creator<il> {
    public static void a(il ilVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, ilVar.a());
        ke.a(parcel, 2, ilVar.b());
        ke.a(parcel, 3, ilVar.c());
        ke.a(parcel, 4, ilVar.d());
        ke.a(parcel, 5, (Parcelable) ilVar.f(), i, false);
        ke.a(parcel, 6, ilVar.e());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public il createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        double d = 0.0d;
        ApplicationMetadata applicationMetadata = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    d = a.m(parcel, a);
                    break;
                case 3:
                    z = a.c(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    applicationMetadata = (ApplicationMetadata) a.a(parcel, a, ApplicationMetadata.CREATOR);
                    break;
                case 6:
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
        return new il(i3, d, z, i2, applicationMetadata, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public il[] newArray(int i) {
        return new il[i];
    }
}
