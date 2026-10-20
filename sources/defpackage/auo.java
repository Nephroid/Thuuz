package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.LoyaltyWalletObject;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.j;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class auo implements Parcelable.Creator<LoyaltyWalletObject> {
    public static void a(LoyaltyWalletObject loyaltyWalletObject, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, loyaltyWalletObject.a());
        ke.a(parcel, 2, loyaltyWalletObject.a, false);
        ke.a(parcel, 3, loyaltyWalletObject.b, false);
        ke.a(parcel, 4, loyaltyWalletObject.c, false);
        ke.a(parcel, 5, loyaltyWalletObject.d, false);
        ke.a(parcel, 6, loyaltyWalletObject.e, false);
        ke.a(parcel, 7, loyaltyWalletObject.f, false);
        ke.a(parcel, 8, loyaltyWalletObject.g, false);
        ke.a(parcel, 9, loyaltyWalletObject.h, false);
        ke.a(parcel, 10, loyaltyWalletObject.i, false);
        ke.a(parcel, 11, loyaltyWalletObject.j, false);
        ke.a(parcel, 12, loyaltyWalletObject.k);
        ke.c(parcel, 13, loyaltyWalletObject.l, false);
        ke.a(parcel, 14, (Parcelable) loyaltyWalletObject.m, i, false);
        ke.c(parcel, 15, loyaltyWalletObject.n, false);
        ke.a(parcel, 17, loyaltyWalletObject.p, false);
        ke.a(parcel, 16, loyaltyWalletObject.o, false);
        ke.a(parcel, 19, loyaltyWalletObject.r);
        ke.c(parcel, 18, loyaltyWalletObject.q, false);
        ke.c(parcel, 21, loyaltyWalletObject.t, false);
        ke.c(parcel, 20, loyaltyWalletObject.s, false);
        ke.a(parcel, 23, (Parcelable) loyaltyWalletObject.v, i, false);
        ke.c(parcel, 22, loyaltyWalletObject.u, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LoyaltyWalletObject createFromParcel(Parcel parcel) {
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
        String str9 = null;
        String str10 = null;
        int i2 = 0;
        ArrayList a = afu.a();
        l lVar = null;
        ArrayList a2 = afu.a();
        String str11 = null;
        String str12 = null;
        ArrayList a3 = afu.a();
        boolean z = false;
        ArrayList a4 = afu.a();
        ArrayList a5 = afu.a();
        ArrayList a6 = afu.a();
        f fVar = null;
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
                    str9 = a.o(parcel, a7);
                    break;
                case 11:
                    str10 = a.o(parcel, a7);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    i2 = a.g(parcel, a7);
                    break;
                case HTTP.CR /* 13 */:
                    a = a.c(parcel, a7, p.CREATOR);
                    break;
                case 14:
                    lVar = (l) a.a(parcel, a7, l.CREATOR);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    a2 = a.c(parcel, a7, LatLng.CREATOR);
                    break;
                case 16:
                    str11 = a.o(parcel, a7);
                    break;
                case LangUtils.HASH_SEED /* 17 */:
                    str12 = a.o(parcel, a7);
                    break;
                case 18:
                    a3 = a.c(parcel, a7, d.CREATOR);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                    z = a.c(parcel, a7);
                    break;
                case 20:
                    a4 = a.c(parcel, a7, n.CREATOR);
                    break;
                case 21:
                    a5 = a.c(parcel, a7, j.CREATOR);
                    break;
                case 22:
                    a6 = a.c(parcel, a7, n.CREATOR);
                    break;
                case 23:
                    fVar = (f) a.a(parcel, a7, f.CREATOR);
                    break;
                default:
                    a.b(parcel, a7);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new LoyaltyWalletObject(i, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, i2, a, lVar, a2, str11, str12, a3, z, a4, a5, a6, fVar);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LoyaltyWalletObject[] newArray(int i) {
        return new LoyaltyWalletObject[i];
    }
}
