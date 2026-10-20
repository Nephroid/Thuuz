package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.achievement.AchievementEntity;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class qo implements Parcelable.Creator<AchievementEntity> {
    public static void a(AchievementEntity achievementEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, achievementEntity.i_(), false);
        ke.a(parcel, 2, achievementEntity.c());
        ke.a(parcel, 3, achievementEntity.d(), false);
        ke.a(parcel, 4, achievementEntity.e(), false);
        ke.a(parcel, 5, (Parcelable) achievementEntity.f(), i, false);
        ke.a(parcel, 6, achievementEntity.g(), false);
        ke.a(parcel, 7, (Parcelable) achievementEntity.h(), i, false);
        ke.a(parcel, 8, achievementEntity.i(), false);
        ke.a(parcel, 9, achievementEntity.j());
        ke.a(parcel, 10, achievementEntity.k(), false);
        ke.a(parcel, 11, (Parcelable) achievementEntity.l(), i, false);
        ke.a(parcel, 12, achievementEntity.m());
        ke.a(parcel, 13, achievementEntity.n());
        ke.a(parcel, 14, achievementEntity.o(), false);
        ke.a(parcel, 15, achievementEntity.p());
        ke.a(parcel, 16, achievementEntity.q());
        ke.a(parcel, 1000, achievementEntity.r());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AchievementEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        Uri uri = null;
        String str4 = null;
        Uri uri2 = null;
        String str5 = null;
        int i3 = 0;
        String str6 = null;
        PlayerEntity playerEntity = null;
        int i4 = 0;
        int i5 = 0;
        String str7 = null;
        long j = 0;
        long j2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    i2 = a.g(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    str3 = a.o(parcel, a);
                    break;
                case 5:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 6:
                    str4 = a.o(parcel, a);
                    break;
                case 7:
                    uri2 = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 8:
                    str5 = a.o(parcel, a);
                    break;
                case 9:
                    i3 = a.g(parcel, a);
                    break;
                case 10:
                    str6 = a.o(parcel, a);
                    break;
                case 11:
                    playerEntity = (PlayerEntity) a.a(parcel, a, PlayerEntity.CREATOR);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    i4 = a.g(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    i5 = a.g(parcel, a);
                    break;
                case 14:
                    str7 = a.o(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    j = a.i(parcel, a);
                    break;
                case 16:
                    j2 = a.i(parcel, a);
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
        return new AchievementEntity(i, str, i2, str2, str3, uri, str4, uri2, str5, i3, str6, playerEntity, i4, i5, str7, j, j2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AchievementEntity[] newArray(int i) {
        return new AchievementEntity[i];
    }
}
