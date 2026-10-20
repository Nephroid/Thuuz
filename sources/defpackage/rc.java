package defpackage;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;

/* loaded from: classes.dex */
public final class rc extends rd {
    @Override // defpackage.rd, android.os.Parcelable.Creator
    /* renamed from: a */
    public ParticipantEntity createFromParcel(Parcel parcel) {
        Integer c;
        boolean b;
        boolean a;
        c = ParticipantEntity.c();
        b = ParticipantEntity.b(c);
        if (!b) {
            a = ParticipantEntity.a(ParticipantEntity.class.getCanonicalName());
            if (!a) {
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                Uri parse = readString3 == null ? null : Uri.parse(readString3);
                String readString4 = parcel.readString();
                return new ParticipantEntity(3, readString, readString2, parse, readString4 == null ? null : Uri.parse(readString4), parcel.readInt(), parcel.readString(), parcel.readInt() > 0, parcel.readInt() > 0 ? PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, null, null, null);
            }
        }
        return super.createFromParcel(parcel);
    }
}
