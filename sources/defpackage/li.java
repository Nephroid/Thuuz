package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DrivePreferences;
import com.google.android.gms.drive.internal.SetDrivePreferencesRequest;

/* loaded from: classes.dex */
public class li implements Parcelable.Creator<SetDrivePreferencesRequest> {
    public static void a(SetDrivePreferencesRequest setDrivePreferencesRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, setDrivePreferencesRequest.a);
        ke.a(parcel, 2, (Parcelable) setDrivePreferencesRequest.b, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SetDrivePreferencesRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        DrivePreferences drivePreferences = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    drivePreferences = (DrivePreferences) a.a(parcel, a, DrivePreferences.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new SetDrivePreferencesRequest(i, drivePreferences);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SetDrivePreferencesRequest[] newArray(int i) {
        return new SetDrivePreferencesRequest[i];
    }
}
