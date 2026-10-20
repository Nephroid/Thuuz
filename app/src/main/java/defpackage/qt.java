package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.game.ExtendedGameEntity;
import com.google.android.gms.games.internal.game.GameBadgeEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class qt implements Parcelable.Creator<ExtendedGameEntity> {
    public static void a(ExtendedGameEntity extendedGameEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) extendedGameEntity.m_(), i, false);
        ke.a(parcel, 1000, extendedGameEntity.m());
        ke.a(parcel, 2, extendedGameEntity.l_());
        ke.a(parcel, 3, extendedGameEntity.e());
        ke.a(parcel, 4, extendedGameEntity.f());
        ke.a(parcel, 5, extendedGameEntity.g());
        ke.a(parcel, 6, extendedGameEntity.h());
        ke.a(parcel, 7, extendedGameEntity.i(), false);
        ke.a(parcel, 8, extendedGameEntity.j());
        ke.a(parcel, 9, extendedGameEntity.k(), false);
        ke.c(parcel, 10, extendedGameEntity.k_(), false);
        ke.a(parcel, 11, (Parcelable) extendedGameEntity.l(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ExtendedGameEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        String str = null;
        long j3 = 0;
        String str2 = null;
        ArrayList arrayList = null;
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, a, GameEntity.CREATOR);
                    break;
                case 2:
                    i2 = a.g(parcel, a);
                    break;
                case 3:
                    z = a.c(parcel, a);
                    break;
                case 4:
                    i3 = a.g(parcel, a);
                    break;
                case 5:
                    j = a.i(parcel, a);
                    break;
                case 6:
                    j2 = a.i(parcel, a);
                    break;
                case 7:
                    str = a.o(parcel, a);
                    break;
                case 8:
                    j3 = a.i(parcel, a);
                    break;
                case 9:
                    str2 = a.o(parcel, a);
                    break;
                case 10:
                    arrayList = a.c(parcel, a, GameBadgeEntity.CREATOR);
                    break;
                case 11:
                    snapshotMetadataEntity = (SnapshotMetadataEntity) a.a(parcel, a, SnapshotMetadataEntity.CREATOR);
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
        return new ExtendedGameEntity(i, gameEntity, i2, z, i3, j, j2, str, j3, str2, arrayList, snapshotMetadataEntity);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ExtendedGameEntity[] newArray(int i) {
        return new ExtendedGameEntity[i];
    }
}
