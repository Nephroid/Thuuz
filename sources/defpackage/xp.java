package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.dj;
import com.google.android.gms.internal.dm;
import com.google.android.gms.internal.gt;
import com.google.android.gms.internal.x;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class xp implements Parcelable.Creator<dm> {
    public static void a(dm dmVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, dmVar.a);
        ke.a(parcel, 2, (Parcelable) dmVar.b, i, false);
        ke.a(parcel, 3, dmVar.a(), false);
        ke.a(parcel, 4, dmVar.b(), false);
        ke.a(parcel, 5, dmVar.c(), false);
        ke.a(parcel, 6, dmVar.d(), false);
        ke.a(parcel, 7, dmVar.g, false);
        ke.a(parcel, 8, dmVar.h);
        ke.a(parcel, 9, dmVar.i, false);
        ke.a(parcel, 10, dmVar.f(), false);
        ke.a(parcel, 11, dmVar.k);
        ke.a(parcel, 12, dmVar.l);
        ke.a(parcel, 13, dmVar.m, false);
        ke.a(parcel, 14, (Parcelable) dmVar.n, i, false);
        ke.a(parcel, 15, dmVar.e(), false);
        ke.a(parcel, 17, (Parcelable) dmVar.q, i, false);
        ke.a(parcel, 16, dmVar.p, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public dm createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        dj djVar = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        String str = null;
        boolean z = false;
        String str2 = null;
        IBinder iBinder5 = null;
        int i2 = 0;
        int i3 = 0;
        String str3 = null;
        gt gtVar = null;
        IBinder iBinder6 = null;
        String str4 = null;
        x xVar = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    djVar = (dj) a.a(parcel, a, dj.CREATOR);
                    break;
                case 3:
                    iBinder = a.p(parcel, a);
                    break;
                case 4:
                    iBinder2 = a.p(parcel, a);
                    break;
                case 5:
                    iBinder3 = a.p(parcel, a);
                    break;
                case 6:
                    iBinder4 = a.p(parcel, a);
                    break;
                case 7:
                    str = a.o(parcel, a);
                    break;
                case 8:
                    z = a.c(parcel, a);
                    break;
                case 9:
                    str2 = a.o(parcel, a);
                    break;
                case 10:
                    iBinder5 = a.p(parcel, a);
                    break;
                case 11:
                    i2 = a.g(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    i3 = a.g(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    str3 = a.o(parcel, a);
                    break;
                case 14:
                    gtVar = (gt) a.a(parcel, a, gt.CREATOR);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    iBinder6 = a.p(parcel, a);
                    break;
                case 16:
                    str4 = a.o(parcel, a);
                    break;
                case LangUtils.HASH_SEED /* 17 */:
                    xVar = (x) a.a(parcel, a, x.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new dm(i, djVar, iBinder, iBinder2, iBinder3, iBinder4, str, z, str2, iBinder5, i2, i3, str3, gtVar, iBinder6, str4, xVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public dm[] newArray(int i) {
        return new dm[i];
    }
}
