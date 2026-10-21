package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.CreateFileIntentSenderRequest;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* loaded from: classes.dex */
public class lr implements Parcelable.Creator<CreateFileIntentSenderRequest> {
    public static void a(CreateFileIntentSenderRequest createFileIntentSenderRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, createFileIntentSenderRequest.a);
        ke.a(parcel, 2, (Parcelable) createFileIntentSenderRequest.b, i, false);
        ke.a(parcel, 3, createFileIntentSenderRequest.c);
        ke.a(parcel, 4, createFileIntentSenderRequest.d, false);
        ke.a(parcel, 5, (Parcelable) createFileIntentSenderRequest.e, i, false);
        ke.a(parcel, 6, createFileIntentSenderRequest.f, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CreateFileIntentSenderRequest createFromParcel(Parcel parcel) {
        int i = 0;
        Integer num = null;
        int b = a.b(parcel);
        DriveId driveId = null;
        String str = null;
        MetadataBundle metadataBundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    metadataBundle = (MetadataBundle) a.a(parcel, a, MetadataBundle.CREATOR);
                    break;
                case 3:
                    i = a.g(parcel, a);
                    break;
                case 4:
                    str = a.o(parcel, a);
                    break;
                case 5:
                    driveId = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    break;
                case 6:
                    num = a.h(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CreateFileIntentSenderRequest(i2, metadataBundle, i, str, driveId, num);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CreateFileIntentSenderRequest[] newArray(int i) {
        return new CreateFileIntentSenderRequest[i];
    }
}
