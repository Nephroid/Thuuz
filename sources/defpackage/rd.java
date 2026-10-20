package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class rd implements Parcelable.Creator<ParticipantEntity> {
    public static void a(ParticipantEntity participantEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, participantEntity.k(), false);
        ke.a(parcel, 1000, participantEntity.n());
        ke.a(parcel, 2, participantEntity.f(), false);
        ke.a(parcel, 3, (Parcelable) participantEntity.g(), i, false);
        ke.a(parcel, 4, (Parcelable) participantEntity.i(), i, false);
        ke.a(parcel, 5, participantEntity.s_());
        ke.a(parcel, 6, participantEntity.t_(), false);
        ke.a(parcel, 7, participantEntity.e());
        ke.a(parcel, 8, (Parcelable) participantEntity.l(), i, false);
        ke.a(parcel, 9, participantEntity.u_());
        ke.a(parcel, 10, (Parcelable) participantEntity.m(), i, false);
        ke.a(parcel, 11, participantEntity.h(), false);
        ke.a(parcel, 12, participantEntity.j(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParticipantEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        Uri uri = null;
        Uri uri2 = null;
        int i2 = 0;
        String str3 = null;
        boolean z = false;
        PlayerEntity playerEntity = null;
        int i3 = 0;
        ParticipantResult participantResult = null;
        String str4 = null;
        String str5 = null;
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
                    i2 = a.g(parcel, a);
                    break;
                case 6:
                    str3 = a.o(parcel, a);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    break;
                case 8:
                    playerEntity = (PlayerEntity) a.a(parcel, a, PlayerEntity.CREATOR);
                    break;
                case 9:
                    i3 = a.g(parcel, a);
                    break;
                case 10:
                    participantResult = (ParticipantResult) a.a(parcel, a, ParticipantResult.CREATOR);
                    break;
                case 11:
                    str4 = a.o(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    str5 = a.o(parcel, a);
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
        return new ParticipantEntity(i, str, str2, uri, uri2, i2, str3, z, playerEntity, i3, participantResult, str4, str5);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParticipantEntity[] newArray(int i) {
        return new ParticipantEntity[i];
    }
}
