package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.wallet.wobs.j;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class auw implements Parcelable.Creator<CommonWalletObject> {
    public static void a(CommonWalletObject commonWalletObject, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, commonWalletObject.b());
        ke.a(parcel, 2, commonWalletObject.a, false);
        ke.a(parcel, 3, commonWalletObject.b, false);
        ke.a(parcel, 4, commonWalletObject.c, false);
        ke.a(parcel, 5, commonWalletObject.d, false);
        ke.a(parcel, 6, commonWalletObject.e, false);
        ke.a(parcel, 7, commonWalletObject.f, false);
        ke.a(parcel, 8, commonWalletObject.g, false);
        ke.a(parcel, 9, commonWalletObject.h, false);
        ke.a(parcel, 10, commonWalletObject.i);
        ke.c(parcel, 11, commonWalletObject.j, false);
        ke.a(parcel, 12, (Parcelable) commonWalletObject.k, i, false);
        ke.c(parcel, 13, commonWalletObject.l, false);
        ke.a(parcel, 14, commonWalletObject.m, false);
        ke.a(parcel, 15, commonWalletObject.n, false);
        ke.a(parcel, 17, commonWalletObject.p);
        ke.c(parcel, 16, commonWalletObject.o, false);
        ke.c(parcel, 19, commonWalletObject.r, false);
        ke.c(parcel, 18, commonWalletObject.q, false);
        ke.c(parcel, 20, commonWalletObject.s, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CommonWalletObject createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        int i2 = 0;
        ArrayList a = afu.a();
        l lVar = null;
        ArrayList a2 = afu.a();
        String str9 = null;
        String str10 = null;
        ArrayList a3 = afu.a();
        boolean z = false;
        ArrayList a4 = afu.a();
        ArrayList a5 = afu.a();
        ArrayList a6 = afu.a();
        while (parcel.dataPosition() < b) {
            int a7 = a.a(parcel);
            switch (a.a(a7)) {
                case 1:
                    i = a.g(parcel, a7);
                    break;
                case 2:
                    str = a.o(parcel, a7);
                    break;
                case 3:
                    str2 = a.o(parcel, a7);
                    break;
                case 4:
                    str3 = a.o(parcel, a7);
                    break;
                case 5:
                    str4 = a.o(parcel, a7);
                    break;
                case 6:
                    str5 = a.o(parcel, a7);
                    break;
                case 7:
                    str6 = a.o(parcel, a7);
                    break;
                case 8:
                    str7 = a.o(parcel, a7);
                    break;
                case 9:
                    str8 = a.o(parcel, a7);
                    break;
                case 10:
                    i2 = a.g(parcel, a7);
                    break;
                case 11:
                    a = a.c(parcel, a7, p.CREATOR);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    lVar = (l) a.a(parcel, a7, l.CREATOR);
                    break;
                case HTTP.CR /* 13 */:
                    a2 = a.c(parcel, a7, LatLng.CREATOR);
                    break;
                case 14:
                    str9 = a.o(parcel, a7);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    str10 = a.o(parcel, a7);
                    break;
                case 16:
                    a3 = a.c(parcel, a7, d.CREATOR);
                    break;
                case LangUtils.HASH_SEED /* 17 */:
                    z = a.c(parcel, a7);
                    break;
                case 18:
                    a4 = a.c(parcel, a7, n.CREATOR);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                    a5 = a.c(parcel, a7, j.CREATOR);
                    break;
                case 20:
                    a6 = a.c(parcel, a7, n.CREATOR);
                    break;
                default:
                    a.b(parcel, a7);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CommonWalletObject(i, str, str2, str3, str4, str5, str6, str7, str8, i2, a, lVar, a2, str9, str10, a3, z, a4, a5, a6);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CommonWalletObject[] newArray(int i) {
        return new CommonWalletObject[i];
    }
}
