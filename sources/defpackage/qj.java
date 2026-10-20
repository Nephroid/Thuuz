package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.GameEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class qj implements Parcelable.Creator<GameEntity> {
    public static void a(GameEntity gameEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, gameEntity.c_(), false);
        ke.a(parcel, 2, gameEntity.d_(), false);
        ke.a(parcel, 3, gameEntity.e_(), false);
        ke.a(parcel, 4, gameEntity.e(), false);
        ke.a(parcel, 5, gameEntity.f(), false);
        ke.a(parcel, 6, gameEntity.g(), false);
        ke.a(parcel, 7, (Parcelable) gameEntity.h(), i, false);
        ke.a(parcel, 8, (Parcelable) gameEntity.j(), i, false);
        ke.a(parcel, 9, (Parcelable) gameEntity.l(), i, false);
        ke.a(parcel, 10, gameEntity.n());
        ke.a(parcel, 11, gameEntity.q());
        ke.a(parcel, 12, gameEntity.r(), false);
        ke.a(parcel, 13, gameEntity.s());
        ke.a(parcel, 14, gameEntity.t());
        ke.a(parcel, 15, gameEntity.u());
        ke.a(parcel, 17, gameEntity.w());
        ke.a(parcel, 16, gameEntity.v());
        ke.a(parcel, 1000, gameEntity.z());
        ke.a(parcel, 19, gameEntity.k(), false);
        ke.a(parcel, 18, gameEntity.i(), false);
        ke.a(parcel, 21, gameEntity.o());
        ke.a(parcel, 20, gameEntity.m(), false);
        ke.a(parcel, 23, gameEntity.x());
        ke.a(parcel, 22, gameEntity.p());
        ke.a(parcel, 24, gameEntity.y(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GameEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        boolean z = false;
        boolean z2 = false;
        String str7 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        boolean z4 = false;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        String str11 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    str3 = a.o(parcel, a);
                    break;
                case 4:
                    str4 = a.o(parcel, a);
                    break;
                case 5:
                    str5 = a.o(parcel, a);
                    break;
                case 6:
                    str6 = a.o(parcel, a);
                    break;
                case 7:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 8:
                    uri2 = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 9:
                    uri3 = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 10:
                    z = a.c(parcel, a);
                    break;
                case 11:
                    z2 = a.c(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    str7 = a.o(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    i2 = a.g(parcel, a);
                    break;
                case 14:
                    i3 = a.g(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    i4 = a.g(parcel, a);
                    break;
                case 16:
                    z3 = a.c(parcel, a);
                    break;
                case LangUtils.HASH_SEED /* 17 */:
                    z4 = a.c(parcel, a);
                    break;
                case 18:
                    str8 = a.o(parcel, a);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                    str9 = a.o(parcel, a);
                    break;
                case 20:
                    str10 = a.o(parcel, a);
                    break;
                case 21:
                    z5 = a.c(parcel, a);
                    break;
                case 22:
                    z6 = a.c(parcel, a);
                    break;
                case 23:
                    z7 = a.c(parcel, a);
                    break;
                case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                    str11 = a.o(parcel, a);
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
        return new GameEntity(i, str, str2, str3, str4, str5, str6, uri, uri2, uri3, z, z2, str7, i2, i3, i4, z3, z4, str8, str9, str10, z5, z6, z7, str11);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GameEntity[] newArray(int i) {
        return new GameEntity[i];
    }
}
