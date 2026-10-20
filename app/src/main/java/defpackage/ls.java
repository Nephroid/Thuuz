package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.CreateFileRequest;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* loaded from: classes.dex */
public class ls implements Parcelable.Creator<CreateFileRequest> {
    public static void a(CreateFileRequest createFileRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, createFileRequest.a);
        ke.a(parcel, 2, (Parcelable) createFileRequest.b, i, false);
        ke.a(parcel, 3, (Parcelable) createFileRequest.c, i, false);
        ke.a(parcel, 4, (Parcelable) createFileRequest.d, i, false);
        ke.a(parcel, 5, createFileRequest.e, false);
        ke.a(parcel, 6, createFileRequest.f);
        ke.a(parcel, 7, createFileRequest.g, false);
        ke.a(parcel, 8, createFileRequest.h);
        ke.a(parcel, 9, createFileRequest.i);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CreateFileRequest createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        int i2 = 0;
        boolean z = false;
        Integer num = null;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    driveId = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    break;
                case 3:
                    metadataBundle = (MetadataBundle) a.a(parcel, a, MetadataBundle.CREATOR);
                    break;
                case 4:
                    contents = (Contents) a.a(parcel, a, Contents.CREATOR);
                    break;
                case 5:
                    num = a.h(parcel, a);
                    break;
                case 6:
                    z = a.c(parcel, a);
                    break;
                case 7:
                    str = a.o(parcel, a);
                    break;
                case 8:
                    i2 = a.g(parcel, a);
                    break;
                case 9:
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
        return new CreateFileRequest(i3, driveId, metadataBundle, contents, num, z, str, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CreateFileRequest[] newArray(int i) {
        return new CreateFileRequest[i];
    }
}
