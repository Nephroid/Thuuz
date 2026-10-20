package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.RawDataPoint;
import com.google.android.gms.fitness.data.Value;

/* loaded from: classes.dex */
public class ov implements Parcelable.Creator<RawDataPoint> {
    public static void a(RawDataPoint rawDataPoint, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, rawDataPoint.b);
        ke.a(parcel, 1000, rawDataPoint.a);
        ke.a(parcel, 2, rawDataPoint.c);
        ke.a(parcel, 3, (Parcelable[]) rawDataPoint.d, i, false);
        ke.a(parcel, 4, rawDataPoint.e);
        ke.a(parcel, 5, rawDataPoint.f);
        ke.a(parcel, 6, rawDataPoint.g);
        ke.a(parcel, 7, rawDataPoint.h);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RawDataPoint createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        long j = 0;
        long j2 = 0;
        Value[] valueArr = null;
        int i2 = 0;
        int i3 = 0;
        long j3 = 0;
        long j4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    j = a.i(parcel, a);
                    break;
                case 2:
                    j2 = a.i(parcel, a);
                    break;
                case 3:
                    valueArr = (Value[]) a.b(parcel, a, Value.CREATOR);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    i3 = a.g(parcel, a);
                    break;
                case 6:
                    j3 = a.i(parcel, a);
                    break;
                case 7:
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
        return new RawDataPoint(i, j, j2, valueArr, i2, i3, j3, j4);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RawDataPoint[] newArray(int i) {
        return new RawDataPoint[i];
    }
}
