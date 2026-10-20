package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.OpenFileIntentSenderRequest;

/* loaded from: classes.dex */
public class lf implements Parcelable.Creator<OpenFileIntentSenderRequest> {
    public static void a(OpenFileIntentSenderRequest openFileIntentSenderRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, openFileIntentSenderRequest.a);
        ke.a(parcel, 2, openFileIntentSenderRequest.b, false);
        ke.a(parcel, 3, openFileIntentSenderRequest.c, false);
        ke.a(parcel, 4, (Parcelable) openFileIntentSenderRequest.d, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OpenFileIntentSenderRequest createFromParcel(Parcel parcel) {
        DriveId driveId = null;
        int b = a.b(parcel);
        int i = 0;
        String[] strArr = null;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    strArr = a.A(parcel, a);
                    break;
                case 4:
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
        return new OpenFileIntentSenderRequest(i, str, strArr, driveId);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OpenFileIntentSenderRequest[] newArray(int i) {
        return new OpenFileIntentSenderRequest[i];
    }
}
