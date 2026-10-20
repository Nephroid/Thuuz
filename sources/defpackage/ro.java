package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class ro implements Parcelable.Creator<GameRequestEntity> {
    public static void a(GameRequestEntity gameRequestEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) gameRequestEntity.e(), i, false);
        ke.a(parcel, 1000, gameRequestEntity.b());
        ke.a(parcel, 2, (Parcelable) gameRequestEntity.f(), i, false);
        ke.a(parcel, 3, gameRequestEntity.h(), false);
        ke.a(parcel, 4, gameRequestEntity.d(), false);
        ke.c(parcel, 5, gameRequestEntity.n(), false);
        ke.a(parcel, 7, gameRequestEntity.i());
        ke.a(parcel, 9, gameRequestEntity.j());
        ke.a(parcel, 10, gameRequestEntity.k());
        ke.a(parcel, 11, gameRequestEntity.c(), false);
        ke.a(parcel, 12, gameRequestEntity.l());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GameRequestEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        byte[] bArr = null;
        String str = null;
        ArrayList arrayList = null;
        int i2 = 0;
        long j = 0;
        long j2 = 0;
        Bundle bundle = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, a, GameEntity.CREATOR);
                    break;
                case 2:
                    playerEntity = (PlayerEntity) a.a(parcel, a, PlayerEntity.CREATOR);
                    break;
                case 3:
                    bArr = a.r(parcel, a);
                    break;
                case 4:
                    str = a.o(parcel, a);
                    break;
                case 5:
                    arrayList = a.c(parcel, a, PlayerEntity.CREATOR);
                    break;
                case 7:
                    i2 = a.g(parcel, a);
                    break;
                case 9:
                    j = a.i(parcel, a);
                    break;
                case 10:
                    j2 = a.i(parcel, a);
                    break;
                case 11:
                    bundle = a.q(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    i3 = a.g(parcel, a);
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
        return new GameRequestEntity(i, gameEntity, playerEntity, bArr, str, arrayList, i2, j, j2, bundle, i3);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GameRequestEntity[] newArray(int i) {
        return new GameRequestEntity[i];
    }
}
