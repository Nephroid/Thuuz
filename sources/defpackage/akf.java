package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.location.DetectedActivity;

/* loaded from: classes.dex */
public class akf implements Parcelable.Creator<DetectedActivity> {
    public static void a(DetectedActivity detectedActivity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, detectedActivity.b);
        ke.a(parcel, 1000, detectedActivity.c());
        ke.a(parcel, 2, detectedActivity.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DetectedActivity createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    i = a.g(parcel, a);
                    break;
                case 1000:
                    i3 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new DetectedActivity(i3, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DetectedActivity[] newArray(int i) {
        return new DetectedActivity[i];
    }
}
