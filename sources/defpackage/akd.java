package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class akd implements Parcelable.Creator<ActivityRecognitionResult> {
    public static void a(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, activityRecognitionResult.a, false);
        ke.a(parcel, 1000, activityRecognitionResult.a());
        ke.a(parcel, 2, activityRecognitionResult.b);
        ke.a(parcel, 3, activityRecognitionResult.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ActivityRecognitionResult createFromParcel(Parcel parcel) {
        long j = 0;
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        long j2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList = a.c(parcel, a, DetectedActivity.CREATOR);
                    break;
                case 2:
                    j2 = a.i(parcel, a);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 1000:
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
        return new ActivityRecognitionResult(i, arrayList, j2, j);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ActivityRecognitionResult[] newArray(int i) {
        return new ActivityRecognitionResult[i];
    }
}
