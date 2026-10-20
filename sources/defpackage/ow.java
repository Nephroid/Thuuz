package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.RawDataPoint;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ow implements Parcelable.Creator<RawDataSet> {
    public static void a(RawDataSet rawDataSet, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, rawDataSet.b);
        ke.a(parcel, 1000, rawDataSet.a);
        ke.a(parcel, 2, rawDataSet.c);
        ke.c(parcel, 3, rawDataSet.d, false);
        ke.a(parcel, 4, rawDataSet.e);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RawDataSet createFromParcel(Parcel parcel) {
        boolean z = false;
        int b = a.b(parcel);
        ArrayList arrayList = null;
        int i = 0;
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
                case 3:
                    arrayList = a.c(parcel, a, RawDataPoint.CREATOR);
                    break;
                case 4:
                    z = a.c(parcel, a);
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
        return new RawDataSet(i3, i2, i, arrayList, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RawDataSet[] newArray(int i) {
        return new RawDataSet[i];
    }
}
