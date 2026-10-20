package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.internal.CloseContentsRequest;

/* loaded from: classes.dex */
public class lp implements Parcelable.Creator<CloseContentsRequest> {
    public static void a(CloseContentsRequest closeContentsRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, closeContentsRequest.a);
        ke.a(parcel, 2, (Parcelable) closeContentsRequest.b, i, false);
        ke.a(parcel, 3, closeContentsRequest.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CloseContentsRequest createFromParcel(Parcel parcel) {
        Boolean d;
        Contents contents;
        int i;
        Boolean bool = null;
        int b = a.b(parcel);
        int i2 = 0;
        Contents contents2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    Boolean bool2 = bool;
                    contents = contents2;
                    i = a.g(parcel, a);
                    d = bool2;
                    break;
                case 2:
                    Contents contents3 = (Contents) a.a(parcel, a, Contents.CREATOR);
                    i = i2;
                    d = bool;
                    contents = contents3;
                    break;
                case 3:
                    d = a.d(parcel, a);
                    contents = contents2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    d = bool;
                    contents = contents2;
                    i = i2;
                    break;
            }
            i2 = i;
            contents2 = contents;
            bool = d;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CloseContentsRequest(i2, contents2, bool);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CloseContentsRequest[] newArray(int i) {
        return new CloseContentsRequest[i];
    }
}
