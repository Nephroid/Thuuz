package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.ah;

/* loaded from: classes.dex */
public class ph implements Parcelable.Creator<ah> {
    public static void a(ah ahVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) ahVar.b(), i, false);
        ke.a(parcel, 1000, ahVar.a());
        ke.a(parcel, 2, (Parcelable) ahVar.c(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ah createFromParcel(Parcel parcel) {
        DataSource dataSource;
        DataType dataType;
        int i;
        DataSource dataSource2 = null;
        int b = a.b(parcel);
        int i2 = 0;
        DataType dataType2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    DataType dataType3 = (DataType) a.a(parcel, a, DataType.CREATOR);
                    i = i2;
                    dataSource = dataSource2;
                    dataType = dataType3;
                    break;
                case 2:
                    dataSource = (DataSource) a.a(parcel, a, DataSource.CREATOR);
                    dataType = dataType2;
                    i = i2;
                    break;
                case 1000:
                    DataSource dataSource3 = dataSource2;
                    dataType = dataType2;
                    i = a.g(parcel, a);
                    dataSource = dataSource3;
                    break;
                default:
                    a.b(parcel, a);
                    dataSource = dataSource2;
                    dataType = dataType2;
                    i = i2;
                    break;
            }
            i2 = i;
            dataType2 = dataType;
            dataSource2 = dataSource;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ah(i2, dataType2, dataSource2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ah[] newArray(int i) {
        return new ah[i];
    }
}
