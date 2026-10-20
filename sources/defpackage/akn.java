package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.location.LocationRequest;
import org.apache.http.HttpStatus;

/* loaded from: classes.dex */
public class akn implements Parcelable.Creator<LocationRequest> {
    public static void a(LocationRequest locationRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, locationRequest.a);
        ke.a(parcel, 1000, locationRequest.a());
        ke.a(parcel, 2, locationRequest.b);
        ke.a(parcel, 3, locationRequest.c);
        ke.a(parcel, 4, locationRequest.d);
        ke.a(parcel, 5, locationRequest.e);
        ke.a(parcel, 6, locationRequest.f);
        ke.a(parcel, 7, locationRequest.g);
        ke.a(parcel, 8, locationRequest.h);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LocationRequest createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        int i2 = HttpStatus.SC_PROCESSING;
        long j = 3600000;
        long j2 = 600000;
        boolean z = false;
        long j3 = Long.MAX_VALUE;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        float f = 0.0f;
        long j4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    j2 = a.i(parcel, a);
                    break;
                case 4:
                    z = a.c(parcel, a);
                    break;
                case 5:
                    j3 = a.i(parcel, a);
                    break;
                case 6:
                    i3 = a.g(parcel, a);
                    break;
                case 7:
                    f = a.l(parcel, a);
                    break;
                case 8:
                    j4 = a.i(parcel, a);
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
        return new LocationRequest(i, i2, j, j2, z, j3, i3, f, j4);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LocationRequest[] newArray(int i) {
        return new LocationRequest[i];
    }
}
