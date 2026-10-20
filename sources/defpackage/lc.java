package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.StorageStats;
import com.google.android.gms.drive.internal.OnStorageStatsResponse;

/* loaded from: classes.dex */
public class lc implements Parcelable.Creator<OnStorageStatsResponse> {
    public static void a(OnStorageStatsResponse onStorageStatsResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, onStorageStatsResponse.a);
        ke.a(parcel, 2, (Parcelable) onStorageStatsResponse.b, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnStorageStatsResponse createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        StorageStats storageStats = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    storageStats = (StorageStats) a.a(parcel, a, StorageStats.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new OnStorageStatsResponse(i, storageStats);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnStorageStatsResponse[] newArray(int i) {
        return new OnStorageStatsResponse[i];
    }
}
