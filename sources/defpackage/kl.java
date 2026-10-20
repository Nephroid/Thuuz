package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.StorageStats;

/* loaded from: classes.dex */
public class kl implements Parcelable.Creator<StorageStats> {
    public static void a(StorageStats storageStats, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, storageStats.a);
        ke.a(parcel, 2, storageStats.b);
        ke.a(parcel, 3, storageStats.c);
        ke.a(parcel, 4, storageStats.d);
        ke.a(parcel, 5, storageStats.e);
        ke.a(parcel, 6, storageStats.f);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StorageStats createFromParcel(Parcel parcel) {
        int i = 0;
        long j = 0;
        int b = a.b(parcel);
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    j4 = a.i(parcel, a);
                    break;
                case 3:
                    j3 = a.i(parcel, a);
                    break;
                case 4:
                    j2 = a.i(parcel, a);
                    break;
                case 5:
                    j = a.i(parcel, a);
                    break;
                case 6:
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
        return new StorageStats(i2, j4, j3, j2, j, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public StorageStats[] newArray(int i) {
        return new StorageStats[i];
    }
}
