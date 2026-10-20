package defpackage;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.bj;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class tn implements Parcelable.Creator<av> {
    public static void a(av avVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, avVar.a);
        ke.a(parcel, 2, avVar.b);
        ke.a(parcel, 3, avVar.c, false);
        ke.a(parcel, 4, avVar.d);
        ke.b(parcel, 5, avVar.e, false);
        ke.a(parcel, 6, avVar.f);
        ke.a(parcel, 7, avVar.g);
        ke.a(parcel, 8, avVar.h);
        ke.a(parcel, 9, avVar.i, false);
        ke.a(parcel, 10, (Parcelable) avVar.j, i, false);
        ke.a(parcel, 11, (Parcelable) avVar.k, i, false);
        ke.a(parcel, 12, avVar.l, false);
        ke.a(parcel, 13, avVar.m, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public av createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        ArrayList<String> arrayList = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        bj bjVar = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    bundle = a.q(parcel, a);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    arrayList = a.C(parcel, a);
                    break;
                case 6:
                    z = a.c(parcel, a);
                    break;
                case 7:
                    i3 = a.g(parcel, a);
                    break;
                case 8:
                    z2 = a.c(parcel, a);
                    break;
                case 9:
                    str = a.o(parcel, a);
                    break;
                case 10:
                    bjVar = (bj) a.a(parcel, a, bj.CREATOR);
                    break;
                case 11:
                    location = (Location) a.a(parcel, a, Location.CREATOR);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    str2 = a.o(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    bundle2 = a.q(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new av(i, j, bundle, i2, arrayList, z, i3, z2, str, bjVar, location, str2, bundle2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public av[] newArray(int i) {
        return new av[i];
    }
}
