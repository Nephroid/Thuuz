package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;

/* loaded from: classes.dex */
public class on implements Parcelable.Creator<DataSource> {
    public static void a(DataSource dataSource, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) dataSource.a(), i, false);
        ke.a(parcel, 1000, dataSource.k());
        ke.a(parcel, 2, dataSource.c(), false);
        ke.a(parcel, 3, dataSource.b());
        ke.a(parcel, 4, (Parcelable) dataSource.f(), i, false);
        ke.a(parcel, 5, (Parcelable) dataSource.e(), i, false);
        ke.a(parcel, 6, dataSource.g(), false);
        ke.a(parcel, 7, dataSource.h());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSource createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int b = a.b(parcel);
        com.google.android.gms.fitness.data.a aVar = null;
        Device device = null;
        int i = 0;
        String str2 = null;
        DataType dataType = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    dataType = (DataType) a.a(parcel, a, DataType.CREATOR);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
                    break;
                case 4:
                    device = (Device) a.a(parcel, a, Device.CREATOR);
                    break;
                case 5:
                    aVar = (com.google.android.gms.fitness.data.a) a.a(parcel, a, com.google.android.gms.fitness.data.a.CREATOR);
                    break;
                case 6:
                    str = a.o(parcel, a);
                    break;
                case 7:
                    z = a.c(parcel, a);
                    break;
                case 1000:
                    i2 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new DataSource(i2, dataType, str2, i, device, aVar, str, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataSource[] newArray(int i) {
        return new DataSource[i];
    }
}
