package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.internal.OnListEntriesResponse;

/* loaded from: classes.dex */
public class kx implements Parcelable.Creator<OnListEntriesResponse> {
    public static void a(OnListEntriesResponse onListEntriesResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, onListEntriesResponse.a);
        ke.a(parcel, 2, (Parcelable) onListEntriesResponse.b, i, false);
        ke.a(parcel, 3, onListEntriesResponse.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnListEntriesResponse createFromParcel(Parcel parcel) {
        boolean c;
        DataHolder dataHolder;
        int i;
        boolean z = false;
        int b = a.b(parcel);
        DataHolder dataHolder2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    boolean z2 = z;
                    dataHolder = dataHolder2;
                    i = a.g(parcel, a);
                    c = z2;
                    break;
                case 2:
                    DataHolder dataHolder3 = (DataHolder) a.a(parcel, a, DataHolder.CREATOR);
                    i = i2;
                    c = z;
                    dataHolder = dataHolder3;
                    break;
                case 3:
                    c = a.c(parcel, a);
                    dataHolder = dataHolder2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, a);
                    c = z;
                    dataHolder = dataHolder2;
                    i = i2;
                    break;
            }
            i2 = i;
            dataHolder2 = dataHolder;
            z = c;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new OnListEntriesResponse(i2, dataHolder2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnListEntriesResponse[] newArray(int i) {
        return new OnListEntriesResponse[i];
    }
}
