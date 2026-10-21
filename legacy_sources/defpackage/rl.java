package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchEntity;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class rl implements Parcelable.Creator<TurnBasedMatchEntity> {
    public static void a(TurnBasedMatchEntity turnBasedMatchEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) turnBasedMatchEntity.y_(), i, false);
        ke.a(parcel, 2, turnBasedMatchEntity.c(), false);
        ke.a(parcel, 3, turnBasedMatchEntity.d(), false);
        ke.a(parcel, 4, turnBasedMatchEntity.e());
        ke.a(parcel, 5, turnBasedMatchEntity.j(), false);
        ke.a(parcel, 6, turnBasedMatchEntity.k());
        ke.a(parcel, 7, turnBasedMatchEntity.m(), false);
        ke.a(parcel, 8, turnBasedMatchEntity.f());
        ke.a(parcel, 10, turnBasedMatchEntity.i());
        ke.a(parcel, 11, turnBasedMatchEntity.o());
        ke.a(parcel, 12, turnBasedMatchEntity.n(), false);
        ke.c(parcel, 13, turnBasedMatchEntity.l(), false);
        ke.a(parcel, 14, turnBasedMatchEntity.p(), false);
        ke.a(parcel, 15, turnBasedMatchEntity.q(), false);
        ke.a(parcel, 17, turnBasedMatchEntity.s(), false);
        ke.a(parcel, 16, turnBasedMatchEntity.r());
        ke.a(parcel, 1000, turnBasedMatchEntity.w());
        ke.a(parcel, 19, turnBasedMatchEntity.u());
        ke.a(parcel, 18, turnBasedMatchEntity.g());
        ke.a(parcel, 21, turnBasedMatchEntity.v(), false);
        ke.a(parcel, 20, turnBasedMatchEntity.h(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TurnBasedMatchEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        String str = null;
        String str2 = null;
        long j = 0;
        String str3 = null;
        long j2 = 0;
        String str4 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        byte[] bArr = null;
        ArrayList arrayList = null;
        String str5 = null;
        byte[] bArr2 = null;
        int i5 = 0;
        Bundle bundle = null;
        int i6 = 0;
        boolean z = false;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, a, GameEntity.CREATOR);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    j = a.i(parcel, a);
                    break;
                case 5:
                    str3 = a.o(parcel, a);
                    break;
                case 6:
                    j2 = a.i(parcel, a);
                    break;
                case 7:
                    str4 = a.o(parcel, a);
                    break;
                case 8:
                    i2 = a.g(parcel, a);
                    break;
                case 10:
                    i3 = a.g(parcel, a);
                    break;
                case 11:
                    i4 = a.g(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    bArr = a.r(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    arrayList = a.c(parcel, a, ParticipantEntity.CREATOR);
                    break;
                case 14:
                    str5 = a.o(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    bArr2 = a.r(parcel, a);
                    break;
                case 16:
                    i5 = a.g(parcel, a);
                    break;
                case LangUtils.HASH_SEED /* 17 */:
                    bundle = a.q(parcel, a);
                    break;
                case 18:
                    i6 = a.g(parcel, a);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                    z = a.c(parcel, a);
                    break;
                case 20:
                    str6 = a.o(parcel, a);
                    break;
                case 21:
                    str7 = a.o(parcel, a);
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
        return new TurnBasedMatchEntity(i, gameEntity, str, str2, j, str3, j2, str4, i2, i3, i4, bArr, arrayList, str5, bArr2, i5, bundle, i6, z, str6, str7);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TurnBasedMatchEntity[] newArray(int i) {
        return new TurnBasedMatchEntity[i];
    }
}
