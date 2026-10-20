package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.internal.OnDownloadProgressResponse;

/* loaded from: classes.dex */
public class kt implements Parcelable.Creator<OnDownloadProgressResponse> {
    public static void a(OnDownloadProgressResponse onDownloadProgressResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, onDownloadProgressResponse.a);
        ke.a(parcel, 2, onDownloadProgressResponse.b);
        ke.a(parcel, 3, onDownloadProgressResponse.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnDownloadProgressResponse createFromParcel(Parcel parcel) {
        long j = 0;
        int b = a.b(parcel);
        int i = 0;
        long j2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    j2 = a.i(parcel, a);
                    break;
                case 3:
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
        return new OnDownloadProgressResponse(i, j2, j);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnDownloadProgressResponse[] newArray(int i) {
        return new OnDownloadProgressResponse[i];
    }
}
