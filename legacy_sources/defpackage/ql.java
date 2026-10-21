package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.PlayerLevelInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;

/* loaded from: classes.dex */
public class ql implements Parcelable.Creator<PlayerEntity> {
    public static void a(PlayerEntity playerEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, playerEntity.f_(), false);
        ke.a(parcel, 2, playerEntity.g_(), false);
        ke.a(parcel, 3, (Parcelable) playerEntity.h_(), i, false);
        ke.a(parcel, 4, (Parcelable) playerEntity.f(), i, false);
        ke.a(parcel, 5, playerEntity.h());
        ke.a(parcel, 6, playerEntity.j());
        ke.a(parcel, 7, playerEntity.i());
        ke.a(parcel, 8, playerEntity.e(), false);
        ke.a(parcel, 9, playerEntity.g(), false);
        ke.a(parcel, 14, playerEntity.l(), false);
        ke.a(parcel, 15, (Parcelable) playerEntity.n(), i, false);
        ke.a(parcel, 16, (Parcelable) playerEntity.m(), i, false);
        ke.a(parcel, 1000, playerEntity.o());
        ke.a(parcel, 18, playerEntity.k());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PlayerEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        Uri uri = null;
        Uri uri2 = null;
        long j = 0;
        int i2 = 0;
        long j2 = 0;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        MostRecentGameInfoEntity mostRecentGameInfoEntity = null;
        PlayerLevelInfo playerLevelInfo = null;
        boolean z = false;
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
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 4:
                    uri2 = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 5:
                    j = a.i(parcel, a);
                    break;
                case 6:
                    i2 = a.g(parcel, a);
                    break;
                case 7:
                    j2 = a.i(parcel, a);
                    break;
                case 8:
                    str3 = a.o(parcel, a);
                    break;
                case 9:
                    str4 = a.o(parcel, a);
                    break;
                case 14:
                    str5 = a.o(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    mostRecentGameInfoEntity = (MostRecentGameInfoEntity) a.a(parcel, a, MostRecentGameInfoEntity.CREATOR);
                    break;
                case 16:
                    playerLevelInfo = (PlayerLevelInfo) a.a(parcel, a, PlayerLevelInfo.CREATOR);
                    break;
                case 18:
                    z = a.c(parcel, a);
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
        return new PlayerEntity(i, str, str2, uri, uri2, j, i2, j2, str3, str4, str5, mostRecentGameInfoEntity, playerLevelInfo, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PlayerEntity[] newArray(int i) {
        return new PlayerEntity[i];
    }
}
