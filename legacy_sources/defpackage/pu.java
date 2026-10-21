package defpackage;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.request.p;

/* loaded from: classes.dex */
public class pu implements Parcelable.Creator<p> {
    public static void a(p pVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, pVar.c(), false);
        ke.a(parcel, 1000, pVar.b());
        ke.a(parcel, 2, (Parcelable) pVar.a(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public p createFromParcel(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int b = a.b(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    iBinder = a.p(parcel, a);
                    break;
                case 2:
                    pendingIntent = (PendingIntent) a.a(parcel, a, PendingIntent.CREATOR);
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
        return new p(i, iBinder, pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public p[] newArray(int i) {
        return new p[i];
    }
}
