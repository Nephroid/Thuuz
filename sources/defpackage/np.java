package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEvent;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEventList;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class np implements Parcelable.Creator<ParcelableEventList> {
    public static void a(ParcelableEventList parcelableEventList, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, parcelableEventList.a);
        ke.c(parcel, 2, parcelableEventList.b, false);
        ke.a(parcel, 3, (Parcelable) parcelableEventList.c, i, false);
        ke.a(parcel, 4, parcelableEventList.d);
        ke.b(parcel, 5, parcelableEventList.e, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableEventList createFromParcel(Parcel parcel) {
        boolean z = false;
        ArrayList<String> arrayList = null;
        int b = a.b(parcel);
        DataHolder dataHolder = null;
        ArrayList arrayList2 = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    arrayList2 = a.c(parcel, a, ParcelableEvent.CREATOR);
                    break;
                case 3:
                    dataHolder = (DataHolder) a.a(parcel, a, DataHolder.CREATOR);
                    break;
                case 4:
                    z = a.c(parcel, a);
                    break;
                case 5:
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
        return new ParcelableEventList(i, arrayList2, dataHolder, z, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableEventList[] newArray(int i) {
        return new ParcelableEventList[i];
    }
}
