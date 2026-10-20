package defpackage;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.n;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class pt implements Parcelable.Creator<n> {
    public static void a(n nVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) nVar.a(), i, false);
        ke.a(parcel, 1000, nVar.j());
        ke.a(parcel, 2, (Parcelable) nVar.b(), i, false);
        ke.a(parcel, 3, nVar.k(), false);
        ke.a(parcel, 4, nVar.a);
        ke.a(parcel, 5, nVar.b);
        ke.a(parcel, 6, nVar.e());
        ke.a(parcel, 7, nVar.f());
        ke.a(parcel, 8, (Parcelable) nVar.c(), i, false);
        ke.a(parcel, 9, nVar.d());
        ke.a(parcel, 10, nVar.h());
        ke.c(parcel, 11, nVar.g(), false);
        ke.a(parcel, 12, nVar.i());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public n createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        DataSource dataSource = null;
        DataType dataType = null;
        IBinder iBinder = null;
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        PendingIntent pendingIntent = null;
        long j3 = 0;
        int i4 = 0;
        ArrayList arrayList = null;
        long j4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    dataSource = (DataSource) a.a(parcel, a, DataSource.CREATOR);
                    break;
                case 2:
                    dataType = (DataType) a.a(parcel, a, DataType.CREATOR);
                    break;
                case 3:
                    iBinder = a.p(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    i3 = a.g(parcel, a);
                    break;
                case 6:
                    j = a.i(parcel, a);
                    break;
                case 7:
                    j2 = a.i(parcel, a);
                    break;
                case 8:
                    pendingIntent = (PendingIntent) a.a(parcel, a, PendingIntent.CREATOR);
                    break;
                case 9:
                    j3 = a.i(parcel, a);
                    break;
                case 10:
                    i4 = a.g(parcel, a);
                    break;
                case 11:
                    arrayList = a.c(parcel, a, LocationRequest.CREATOR);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    j4 = a.i(parcel, a);
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
        return new n(i, dataSource, dataType, iBinder, i2, i3, j, j2, pendingIntent, j3, i4, arrayList, j4);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public n[] newArray(int i) {
        return new n[i];
    }
}
