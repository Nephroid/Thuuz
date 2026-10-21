package defpackage;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;

/* loaded from: classes.dex */
public class kf implements Parcelable.Creator<Contents> {
    public static void a(Contents contents, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, contents.a);
        ke.a(parcel, 2, (Parcelable) contents.b, i, false);
        ke.a(parcel, 3, contents.c);
        ke.a(parcel, 4, contents.d);
        ke.a(parcel, 5, (Parcelable) contents.e, i, false);
        ke.a(parcel, 7, contents.f);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Contents createFromParcel(Parcel parcel) {
        DriveId driveId = null;
        boolean z = false;
        int b = a.b(parcel);
        int i = 0;
        int i2 = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i3 = a.g(parcel, a);
                    break;
                case 2:
                    parcelFileDescriptor = (ParcelFileDescriptor) a.a(parcel, a, ParcelFileDescriptor.CREATOR);
                    break;
                case 3:
                    i2 = a.g(parcel, a);
                    break;
                case 4:
                    i = a.g(parcel, a);
                    break;
                case 5:
                    driveId = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    break;
                case 6:
                default:
                    a.b(parcel, a);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new Contents(i3, parcelFileDescriptor, i2, i, driveId, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Contents[] newArray(int i) {
        return new Contents[i];
    }
}
