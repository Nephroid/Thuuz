package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.LoadRealtimeRequest;

/* loaded from: classes.dex */
public class kq implements Parcelable.Creator<LoadRealtimeRequest> {
    public static void a(LoadRealtimeRequest loadRealtimeRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, loadRealtimeRequest.a);
        ke.a(parcel, 2, (Parcelable) loadRealtimeRequest.b, i, false);
        ke.a(parcel, 3, loadRealtimeRequest.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LoadRealtimeRequest createFromParcel(Parcel parcel) {
        boolean c;
        DriveId driveId;
        int i;
        boolean z = false;
        int b = a.b(parcel);
        DriveId driveId2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    boolean z2 = z;
                    driveId = driveId2;
                    i = a.g(parcel, a);
                    c = z2;
                    break;
                case 2:
                    DriveId driveId3 = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    i = i2;
                    c = z;
                    driveId = driveId3;
                    break;
                case 3:
                    c = a.c(parcel, a);
                    driveId = driveId2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    c = z;
                    driveId = driveId2;
                    i = i2;
                    break;
            }
            i2 = i;
            driveId2 = driveId;
            z = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new LoadRealtimeRequest(i2, driveId2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LoadRealtimeRequest[] newArray(int i) {
        return new LoadRealtimeRequest[i];
    }
}
