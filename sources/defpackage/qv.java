package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.internal.game.GameBadgeEntity;

/* loaded from: classes.dex */
public class qv implements Parcelable.Creator<GameBadgeEntity> {
    public static void a(GameBadgeEntity gameBadgeEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, gameBadgeEntity.n_());
        ke.a(parcel, 1000, gameBadgeEntity.f());
        ke.a(parcel, 2, gameBadgeEntity.o_(), false);
        ke.a(parcel, 3, gameBadgeEntity.p_(), false);
        ke.a(parcel, 4, (Parcelable) gameBadgeEntity.e(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GameBadgeEntity createFromParcel(Parcel parcel) {
        int i = 0;
        Uri uri = null;
        int b = a.b(parcel);
        String str = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new GameBadgeEntity(i2, i, str2, str, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GameBadgeEntity[] newArray(int i) {
        return new GameBadgeEntity[i];
    }
}
