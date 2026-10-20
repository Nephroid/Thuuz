package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.PlayerLevel;
import com.google.android.gms.games.PlayerLevelInfo;

/* loaded from: classes.dex */
public class qn implements Parcelable.Creator<PlayerLevelInfo> {
    public static void a(PlayerLevelInfo playerLevelInfo, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, playerLevelInfo.b());
        ke.a(parcel, 1000, playerLevelInfo.a());
        ke.a(parcel, 2, playerLevelInfo.c());
        ke.a(parcel, 3, (Parcelable) playerLevelInfo.d(), i, false);
        ke.a(parcel, 4, (Parcelable) playerLevelInfo.e(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PlayerLevelInfo createFromParcel(Parcel parcel) {
        long j = 0;
        PlayerLevel playerLevel = null;
        int b = a.b(parcel);
        int i = 0;
        PlayerLevel playerLevel2 = null;
        long j2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    j2 = a.i(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    playerLevel2 = (PlayerLevel) a.a(parcel, a, PlayerLevel.CREATOR);
                    break;
                case 4:
                    playerLevel = (PlayerLevel) a.a(parcel, a, PlayerLevel.CREATOR);
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
        return new PlayerLevelInfo(i, j2, j, playerLevel2, playerLevel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PlayerLevelInfo[] newArray(int i) {
        return new PlayerLevelInfo[i];
    }
}
