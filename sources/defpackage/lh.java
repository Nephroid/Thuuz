package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.RemoveEventListenerRequest;

/* loaded from: classes.dex */
public class lh implements Parcelable.Creator<RemoveEventListenerRequest> {
    public static void a(RemoveEventListenerRequest removeEventListenerRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, removeEventListenerRequest.a);
        ke.a(parcel, 2, (Parcelable) removeEventListenerRequest.b, i, false);
        ke.a(parcel, 3, removeEventListenerRequest.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RemoveEventListenerRequest createFromParcel(Parcel parcel) {
        int g;
        DriveId driveId;
        int i;
        int i2 = 0;
        int b = a.b(parcel);
        DriveId driveId2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    int i4 = i2;
                    driveId = driveId2;
                    i = a.g(parcel, a);
                    g = i4;
                    break;
                case 2:
                    DriveId driveId3 = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    i = i3;
                    g = i2;
                    driveId = driveId3;
                    break;
                case 3:
                    g = a.g(parcel, a);
                    driveId = driveId2;
                    i = i3;
                    break;
                default:
                    a.b(parcel, a);
                    g = i2;
                    driveId = driveId2;
                    i = i3;
                    break;
            }
            i3 = i;
            driveId2 = driveId;
            i2 = g;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new RemoveEventListenerRequest(i3, driveId2, i2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RemoveEventListenerRequest[] newArray(int i) {
        return new RemoveEventListenerRequest[i];
    }
}
