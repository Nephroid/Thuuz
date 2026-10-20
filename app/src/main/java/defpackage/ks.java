package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.internal.OnContentsResponse;

/* loaded from: classes.dex */
public class ks implements Parcelable.Creator<OnContentsResponse> {
    public static void a(OnContentsResponse onContentsResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, onContentsResponse.a);
        ke.a(parcel, 2, (Parcelable) onContentsResponse.b, i, false);
        ke.a(parcel, 3, onContentsResponse.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnContentsResponse createFromParcel(Parcel parcel) {
        boolean c;
        Contents contents;
        int i;
        boolean z = false;
        int b = a.b(parcel);
        Contents contents2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    boolean z2 = z;
                    contents = contents2;
                    i = a.g(parcel, a);
                    c = z2;
                    break;
                case 2:
                    Contents contents3 = (Contents) a.a(parcel, a, Contents.CREATOR);
                    i = i2;
                    c = z;
                    contents = contents3;
                    break;
                case 3:
                    c = a.c(parcel, a);
                    contents = contents2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    c = z;
                    contents = contents2;
                    i = i2;
                    break;
            }
            i2 = i;
            contents2 = contents;
            z = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new OnContentsResponse(i2, contents2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnContentsResponse[] newArray(int i) {
        return new OnContentsResponse[i];
    }
}
