package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.a;

/* loaded from: classes.dex */
public class ox implements Parcelable.Creator<Session> {
    public static void a(Session session, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, session.a());
        ke.a(parcel, 1000, session.h());
        ke.a(parcel, 2, session.b());
        ke.a(parcel, 3, session.c(), false);
        ke.a(parcel, 4, session.d(), false);
        ke.a(parcel, 5, session.e(), false);
        ke.a(parcel, 7, session.f());
        ke.a(parcel, 8, (Parcelable) session.g(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Session createFromParcel(Parcel parcel) {
        long j = 0;
        int i = 0;
        a aVar = null;
        int b = com.google.android.gms.common.internal.safeparcel.a.b(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        long j2 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.a(a)) {
                case 1:
                    j2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, a);
                    break;
                case 2:
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, a);
                    break;
                case 3:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, a);
                    break;
                case 4:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, a);
                    break;
                case 5:
                    str = com.google.android.gms.common.internal.safeparcel.a.o(parcel, a);
                    break;
                case 7:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, a);
                    break;
                case 8:
                    aVar = (a) com.google.android.gms.common.internal.safeparcel.a.a(parcel, a, a.CREATOR);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new Session(i2, j2, j, str3, str2, str, i, aVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Session[] newArray(int i) {
        return new Session[i];
    }
}
