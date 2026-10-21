package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;

/* loaded from: classes.dex */
public class kg implements Parcelable.Creator<DriveId> {
    public static void a(DriveId driveId, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, driveId.a);
        ke.a(parcel, 2, driveId.b, false);
        ke.a(parcel, 3, driveId.c);
        ke.a(parcel, 4, driveId.d);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DriveId createFromParcel(Parcel parcel) {
        long j = 0;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        long j2 = 0;
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
                    j2 = a.i(parcel, a);
                    break;
                case 4:
                    j = a.i(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new DriveId(i, str, j2, j);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DriveId[] newArray(int i) {
        return new DriveId[i];
    }
}
