package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;

/* loaded from: classes.dex */
public class rq implements Parcelable.Creator<SnapshotEntity> {
    public static void a(SnapshotEntity snapshotEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) snapshotEntity.b(), i, false);
        ke.a(parcel, 1000, snapshotEntity.d());
        ke.a(parcel, 3, (Parcelable) snapshotEntity.c(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotEntity createFromParcel(Parcel parcel) {
        SnapshotContents snapshotContents;
        SnapshotMetadataEntity snapshotMetadataEntity;
        int i;
        SnapshotContents snapshotContents2 = null;
        int b = a.b(parcel);
        int i2 = 0;
        SnapshotMetadataEntity snapshotMetadataEntity2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    SnapshotMetadataEntity snapshotMetadataEntity3 = (SnapshotMetadataEntity) a.a(parcel, a, SnapshotMetadataEntity.CREATOR);
                    i = i2;
                    snapshotContents = snapshotContents2;
                    snapshotMetadataEntity = snapshotMetadataEntity3;
                    break;
                case 3:
                    snapshotContents = (SnapshotContents) a.a(parcel, a, SnapshotContents.CREATOR);
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    i = i2;
                    break;
                case 1000:
                    SnapshotContents snapshotContents3 = snapshotContents2;
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    i = a.g(parcel, a);
                    snapshotContents = snapshotContents3;
                    break;
                default:
                    a.b(parcel, a);
                    snapshotContents = snapshotContents2;
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    i = i2;
                    break;
            }
            i2 = i;
            snapshotMetadataEntity2 = snapshotMetadataEntity;
            snapshotContents2 = snapshotContents;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new SnapshotEntity(i2, snapshotMetadataEntity2, snapshotContents2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotEntity[] newArray(int i) {
        return new SnapshotEntity[i];
    }
}
