package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DrivePreferences;

/* loaded from: classes.dex */
public class kh implements Parcelable.Creator<DrivePreferences> {
    public static void a(DrivePreferences drivePreferences, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, drivePreferences.a);
        ke.a(parcel, 2, drivePreferences.b);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DrivePreferences createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    z = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new DrivePreferences(i, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DrivePreferences[] newArray(int i) {
        return new DrivePreferences[i];
    }
}
