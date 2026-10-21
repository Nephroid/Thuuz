package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.RealtimeDocumentSyncRequest;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class kk implements Parcelable.Creator<RealtimeDocumentSyncRequest> {
    public static void a(RealtimeDocumentSyncRequest realtimeDocumentSyncRequest, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, realtimeDocumentSyncRequest.a);
        ke.b(parcel, 2, realtimeDocumentSyncRequest.b, false);
        ke.b(parcel, 3, realtimeDocumentSyncRequest.c, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RealtimeDocumentSyncRequest createFromParcel(Parcel parcel) {
        ArrayList<String> arrayList = null;
        int b = a.b(parcel);
        int i = 0;
        ArrayList<String> arrayList2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    arrayList2 = a.C(parcel, a);
                    break;
                case 3:
                    arrayList = a.C(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new RealtimeDocumentSyncRequest(i, arrayList2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RealtimeDocumentSyncRequest[] newArray(int i) {
        return new RealtimeDocumentSyncRequest[i];
    }
}
