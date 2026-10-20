package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DrivePreferences;
import com.google.android.gms.drive.internal.OnDrivePreferencesResponse;

/* loaded from: classes.dex */
public class kv implements Parcelable.Creator<OnDrivePreferencesResponse> {
    public static void a(OnDrivePreferencesResponse onDrivePreferencesResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, onDrivePreferencesResponse.a);
        ke.a(parcel, 2, (Parcelable) onDrivePreferencesResponse.b, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnDrivePreferencesResponse createFromParcel(Parcel parcel) {
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
        return new OnDrivePreferencesResponse(i, drivePreferences);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnDrivePreferencesResponse[] newArray(int i) {
        return new OnDrivePreferencesResponse[i];
    }
}
