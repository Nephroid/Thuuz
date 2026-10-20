package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.SetResourceParentsRequest;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class lk implements Parcelable.Creator<SetResourceParentsRequest> {
    public static void a(SetResourceParentsRequest setResourceParentsRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, setResourceParentsRequest.a);
        ke.a(parcel, 2, (Parcelable) setResourceParentsRequest.b, i, false);
        ke.c(parcel, 3, setResourceParentsRequest.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SetResourceParentsRequest createFromParcel(Parcel parcel) {
        ArrayList c;
        DriveId driveId;
        int i;
        ArrayList arrayList = null;
        int b = a.b(parcel);
        int i2 = 0;
        DriveId driveId2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    ArrayList arrayList2 = arrayList;
                    driveId = driveId2;
                    i = a.g(parcel, a);
                    c = arrayList2;
                    break;
                case 2:
                    DriveId driveId3 = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    i = i2;
                    c = arrayList;
                    driveId = driveId3;
                    break;
                case 3:
                    c = a.c(parcel, a, DriveId.CREATOR);
                    driveId = driveId2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    c = arrayList;
                    driveId = driveId2;
                    i = i2;
                    break;
            }
            i2 = i;
            driveId2 = driveId;
            arrayList = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new SetResourceParentsRequest(i2, driveId2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SetResourceParentsRequest[] newArray(int i) {
        return new SetResourceParentsRequest[i];
    }
}
