package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class rb implements Parcelable.Creator<InvitationEntity> {
    public static void a(InvitationEntity invitationEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) invitationEntity.r_(), i, false);
        ke.a(parcel, 1000, invitationEntity.k());
        ke.a(parcel, 2, invitationEntity.e(), false);
        ke.a(parcel, 3, invitationEntity.g());
        ke.a(parcel, 4, invitationEntity.h());
        ke.a(parcel, 5, (Parcelable) invitationEntity.f(), i, false);
        ke.c(parcel, 6, invitationEntity.l(), false);
        ke.a(parcel, 7, invitationEntity.i());
        ke.a(parcel, 8, invitationEntity.j());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public InvitationEntity createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
        int i = 0;
        int b = a.b(parcel);
        long j = 0;
        int i2 = 0;
        ParticipantEntity participantEntity = null;
        int i3 = 0;
        String str = null;
        GameEntity gameEntity = null;
        int i4 = 0;
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
                    j = a.i(parcel, a);
                    break;
                case 4:
                    i3 = a.g(parcel, a);
                    break;
                case 5:
                    participantEntity = (ParticipantEntity) a.a(parcel, a, ParticipantEntity.CREATOR);
                    break;
                case 6:
                    arrayList = a.c(parcel, a, ParticipantEntity.CREATOR);
                    break;
                case 7:
                    i2 = a.g(parcel, a);
                    break;
                case 8:
                    i = a.g(parcel, a);
                    break;
                case 1000:
                    i4 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new InvitationEntity(i4, gameEntity, str, j, i3, participantEntity, arrayList, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public InvitationEntity[] newArray(int i) {
        return new InvitationEntity[i];
    }
}
