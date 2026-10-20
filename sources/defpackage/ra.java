package defpackage;

import android.os.Parcel;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ra extends rb {
    @Override // defpackage.rb, android.os.Parcelable.Creator
    /* renamed from: a */
    public InvitationEntity createFromParcel(Parcel parcel) {
        Integer c;
        boolean b;
        boolean a;
        c = InvitationEntity.c();
        b = InvitationEntity.b(c);
        if (!b) {
            a = InvitationEntity.a(InvitationEntity.class.getCanonicalName());
            if (!a) {
                GameEntity createFromParcel = GameEntity.CREATOR.createFromParcel(parcel);
                String readString = parcel.readString();
                long readLong = parcel.readLong();
                int readInt = parcel.readInt();
                ParticipantEntity createFromParcel2 = ParticipantEntity.CREATOR.createFromParcel(parcel);
                int readInt2 = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt2);
                for (int i = 0; i < readInt2; i++) {
                    arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
                }
                return new InvitationEntity(2, createFromParcel, readString, readLong, readInt, createFromParcel2, arrayList, -1, 0);
            }
        }
        return super.createFromParcel(parcel);
    }
}
