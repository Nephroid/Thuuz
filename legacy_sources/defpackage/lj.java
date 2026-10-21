package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.AuthorizeAccessRequest;

/* loaded from: classes.dex */
public class lj implements Parcelable.Creator<AuthorizeAccessRequest> {
    public static void a(AuthorizeAccessRequest authorizeAccessRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, authorizeAccessRequest.a);
        ke.a(parcel, 2, authorizeAccessRequest.b);
        ke.a(parcel, 3, (Parcelable) authorizeAccessRequest.c, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AuthorizeAccessRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        long j = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    driveId = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new AuthorizeAccessRequest(i, j, driveId);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public AuthorizeAccessRequest[] newArray(int i) {
        return new AuthorizeAccessRequest[i];
    }
}
