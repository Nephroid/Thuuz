package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.internal.CloseContentsAndUpdateMetadataRequest;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* loaded from: classes.dex */
public class lo implements Parcelable.Creator<CloseContentsAndUpdateMetadataRequest> {
    public static void a(CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, closeContentsAndUpdateMetadataRequest.a);
        ke.a(parcel, 2, (Parcelable) closeContentsAndUpdateMetadataRequest.b, i, false);
        ke.a(parcel, 3, (Parcelable) closeContentsAndUpdateMetadataRequest.c, i, false);
        ke.a(parcel, 4, (Parcelable) closeContentsAndUpdateMetadataRequest.d, i, false);
        ke.a(parcel, 5, closeContentsAndUpdateMetadataRequest.e);
        ke.a(parcel, 6, closeContentsAndUpdateMetadataRequest.f, false);
        ke.a(parcel, 7, closeContentsAndUpdateMetadataRequest.g);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CloseContentsAndUpdateMetadataRequest createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = a.b(parcel);
        boolean z = false;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
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
                    z = a.c(parcel, a);
                    break;
                case 6:
                    str = a.o(parcel, a);
                    break;
                case 7:
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
        return new CloseContentsAndUpdateMetadataRequest(i2, driveId, metadataBundle, contents, z, str, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CloseContentsAndUpdateMetadataRequest[] newArray(int i) {
        return new CloseContentsAndUpdateMetadataRequest[i];
    }
}
