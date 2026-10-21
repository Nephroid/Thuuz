package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class rs implements Parcelable.Creator<SnapshotMetadataEntity> {
    public static void a(SnapshotMetadataEntity snapshotMetadataEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) snapshotMetadataEntity.B_(), i, false);
        ke.a(parcel, 1000, snapshotMetadataEntity.m());
        ke.a(parcel, 2, (Parcelable) snapshotMetadataEntity.c(), i, false);
        ke.a(parcel, 3, snapshotMetadataEntity.d(), false);
        ke.a(parcel, 5, (Parcelable) snapshotMetadataEntity.e(), i, false);
        ke.a(parcel, 6, snapshotMetadataEntity.f(), false);
        ke.a(parcel, 7, snapshotMetadataEntity.i(), false);
        ke.a(parcel, 8, snapshotMetadataEntity.j(), false);
        ke.a(parcel, 9, snapshotMetadataEntity.k());
        ke.a(parcel, 10, snapshotMetadataEntity.l());
        ke.a(parcel, 11, snapshotMetadataEntity.g());
        ke.a(parcel, 12, snapshotMetadataEntity.h(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotMetadataEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        String str = null;
        Uri uri = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        long j = 0;
        long j2 = 0;
        float f = 0.0f;
        String str5 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, a, GameEntity.CREATOR);
                    break;
                case 2:
                    playerEntity = (PlayerEntity) a.a(parcel, a, PlayerEntity.CREATOR);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 5:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 6:
                    str2 = a.o(parcel, a);
                    break;
                case 7:
                    str3 = a.o(parcel, a);
                    break;
                case 8:
                    str4 = a.o(parcel, a);
                    break;
                case 9:
                    j = a.i(parcel, a);
                    break;
                case 10:
                    j2 = a.i(parcel, a);
                    break;
                case 11:
                    f = a.l(parcel, a);
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
        return new SnapshotMetadataEntity(i, gameEntity, playerEntity, str, uri, str2, str3, str4, j, j2, f, str5);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotMetadataEntity[] newArray(int i) {
        return new SnapshotMetadataEntity[i];
    }
}
