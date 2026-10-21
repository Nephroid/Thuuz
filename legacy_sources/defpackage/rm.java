package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.quest.MilestoneEntity;

/* loaded from: classes.dex */
public class rm implements Parcelable.Creator<MilestoneEntity> {
    public static void a(MilestoneEntity milestoneEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, milestoneEntity.z_(), false);
        ke.a(parcel, 1000, milestoneEntity.h());
        ke.a(parcel, 2, milestoneEntity.c());
        ke.a(parcel, 3, milestoneEntity.f());
        ke.a(parcel, 4, milestoneEntity.g(), false);
        ke.a(parcel, 5, milestoneEntity.e());
        ke.a(parcel, 6, milestoneEntity.d(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MilestoneEntity createFromParcel(Parcel parcel) {
        long j = 0;
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        byte[] bArr = null;
        long j2 = 0;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str2 = a.o(parcel, a);
                    break;
                case 2:
                    j2 = a.i(parcel, a);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    bArr = a.r(parcel, a);
                    break;
                case 5:
                    i = a.g(parcel, a);
                    break;
                case 6:
                    str = a.o(parcel, a);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new MilestoneEntity(i2, str2, j2, j, bArr, i, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MilestoneEntity[] newArray(int i) {
        return new MilestoneEntity[i];
    }
}
