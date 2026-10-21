package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.OpenContentsRequest;

/* loaded from: classes.dex */
public class le implements Parcelable.Creator<OpenContentsRequest> {
    public static void a(OpenContentsRequest openContentsRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, openContentsRequest.a);
        ke.a(parcel, 2, (Parcelable) openContentsRequest.b, i, false);
        ke.a(parcel, 3, openContentsRequest.c);
        ke.a(parcel, 4, openContentsRequest.d);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OpenContentsRequest createFromParcel(Parcel parcel) {
        int g;
        int i;
        DriveId driveId;
        int i2;
        int i3 = 0;
        int b = a.b(parcel);
        DriveId driveId2 = null;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    int i6 = i3;
                    i = i4;
                    driveId = driveId2;
                    i2 = a.g(parcel, a);
                    g = i6;
                    break;
                case 2:
                    i2 = i5;
                    int i7 = i4;
                    driveId = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    g = i3;
                    i = i7;
                    break;
                case 3:
                    driveId = driveId2;
                    i2 = i5;
                    int i8 = i3;
                    i = a.g(parcel, a);
                    g = i8;
                    break;
                case 4:
                    g = a.g(parcel, a);
                    i = i4;
                    driveId = driveId2;
                    i2 = i5;
                    break;
                default:
                    a.b(parcel, a);
                    g = i3;
                    i = i4;
                    driveId = driveId2;
                    i2 = i5;
                    break;
            }
            i5 = i2;
            driveId2 = driveId;
            i4 = i;
            i3 = g;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new OpenContentsRequest(i5, driveId2, i4, i3);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OpenContentsRequest[] newArray(int i) {
        return new OpenContentsRequest[i];
    }
}
