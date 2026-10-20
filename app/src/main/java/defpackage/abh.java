package defpackage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.gt;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class abh implements Parcelable.Creator<fi> {
    public static void a(fi fiVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, fiVar.a);
        ke.a(parcel, 2, fiVar.b, false);
        ke.a(parcel, 3, (Parcelable) fiVar.c, i, false);
        ke.a(parcel, 4, (Parcelable) fiVar.d, i, false);
        ke.a(parcel, 5, fiVar.e, false);
        ke.a(parcel, 6, (Parcelable) fiVar.f, i, false);
        ke.a(parcel, 7, (Parcelable) fiVar.g, i, false);
        ke.a(parcel, 8, fiVar.h, false);
        ke.a(parcel, 9, fiVar.i, false);
        ke.a(parcel, 10, fiVar.j, false);
        ke.a(parcel, 11, (Parcelable) fiVar.k, i, false);
        ke.a(parcel, 12, fiVar.l, false);
        ke.a(parcel, 13, fiVar.m);
        ke.b(parcel, 14, fiVar.n, false);
        ke.a(parcel, 15, fiVar.o, false);
        ke.a(parcel, 16, fiVar.p);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public fi createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        Bundle bundle = null;
        av avVar = null;
        ay ayVar = null;
        String str = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        gt gtVar = null;
        Bundle bundle2 = null;
        int i2 = 0;
        ArrayList<String> arrayList = null;
        Bundle bundle3 = null;
        boolean z = false;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    bundle = a.q(parcel, a);
                    break;
                case 3:
                    avVar = (av) a.a(parcel, a, av.CREATOR);
                    break;
                case 4:
                    ayVar = (ay) a.a(parcel, a, ay.CREATOR);
                    break;
                case 5:
                    str = a.o(parcel, a);
                    break;
                case 6:
                    applicationInfo = (ApplicationInfo) a.a(parcel, a, ApplicationInfo.CREATOR);
                    break;
                case 7:
                    packageInfo = (PackageInfo) a.a(parcel, a, PackageInfo.CREATOR);
                    break;
                case 8:
                    str2 = a.o(parcel, a);
                    break;
                case 9:
                    str3 = a.o(parcel, a);
                    break;
                case 10:
                    str4 = a.o(parcel, a);
                    break;
                case 11:
                    gtVar = (gt) a.a(parcel, a, gt.CREATOR);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    bundle2 = a.q(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    i2 = a.g(parcel, a);
                    break;
                case 14:
                    arrayList = a.C(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    bundle3 = a.q(parcel, a);
                    break;
                case 16:
                    z = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new fi(i, bundle, avVar, ayVar, str, applicationInfo, packageInfo, str2, str3, str4, gtVar, bundle2, i2, arrayList, bundle3, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public fi[] newArray(int i) {
        return new fi[i];
    }
}
