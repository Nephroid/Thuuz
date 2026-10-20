package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.q;
import com.google.android.gms.fitness.result.SessionReadResult;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class qf implements Parcelable.Creator<SessionReadResult> {
    public static void a(SessionReadResult sessionReadResult, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, sessionReadResult.a(), false);
        ke.a(parcel, 1000, sessionReadResult.d());
        ke.c(parcel, 2, sessionReadResult.b(), false);
        ke.a(parcel, 3, (Parcelable) sessionReadResult.c(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SessionReadResult createFromParcel(Parcel parcel) {
        Status status = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList2 = a.c(parcel, a, Session.CREATOR);
                    break;
                case 2:
                    arrayList = a.c(parcel, a, q.CREATOR);
                    break;
                case 3:
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
        return new SessionReadResult(i, arrayList2, arrayList, status);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SessionReadResult[] newArray(int i) {
        return new SessionReadResult[i];
    }
}
