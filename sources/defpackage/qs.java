package defpackage;

import android.os.Parcel;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.game.ExtendedGameEntity;
import com.google.android.gms.games.internal.game.GameBadgeEntity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class qs extends qt {
    @Override // defpackage.qt, android.os.Parcelable.Creator
    /* renamed from: a */
    public ExtendedGameEntity createFromParcel(Parcel parcel) {
        Integer c;
        boolean b;
        boolean a;
        c = ExtendedGameEntity.c();
        b = ExtendedGameEntity.b(c);
        if (!b) {
            a = ExtendedGameEntity.a(ExtendedGameEntity.class.getCanonicalName());
            if (!a) {
                GameEntity createFromParcel = GameEntity.CREATOR.createFromParcel(parcel);
                int readInt = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                int readInt2 = parcel.readInt();
                long readLong = parcel.readLong();
                long readLong2 = parcel.readLong();
                String readString = parcel.readString();
                long readLong3 = parcel.readLong();
                String readString2 = parcel.readString();
                int readInt3 = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt3);
                for (int i = 0; i < readInt3; i++) {
                    arrayList.add(GameBadgeEntity.CREATOR.createFromParcel(parcel));
                }
                return new ExtendedGameEntity(2, createFromParcel, readInt, z, readInt2, readLong, readLong2, readString, readLong3, readString2, arrayList, null);
            }
        }
        return super.createFromParcel(parcel);
    }
}
