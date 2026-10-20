package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ri extends rj {
    @Override // defpackage.rj, android.os.Parcelable.Creator
    /* renamed from: a */
    public RoomEntity createFromParcel(Parcel parcel) {
        Integer c;
        boolean b;
        boolean a;
        c = RoomEntity.c();
        b = RoomEntity.b(c);
        if (!b) {
            a = RoomEntity.a(RoomEntity.class.getCanonicalName());
            if (!a) {
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                long readLong = parcel.readLong();
                int readInt = parcel.readInt();
                String readString3 = parcel.readString();
                int readInt2 = parcel.readInt();
                Bundle readBundle = parcel.readBundle();
                int readInt3 = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt3);
                for (int i = 0; i < readInt3; i++) {
                    arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
                }
                return new RoomEntity(2, readString, readString2, readLong, readInt, readString3, readInt2, readBundle, arrayList, -1);
            }
        }
        return super.createFromParcel(parcel);
    }
}
