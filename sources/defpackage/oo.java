package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class oo implements Parcelable.Creator<DataType> {
    public static void a(DataType dataType, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, dataType.a(), false);
        ke.a(parcel, 1000, dataType.d());
        ke.c(parcel, 2, dataType.b(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataType createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    arrayList = a.c(parcel, a, Field.CREATOR);
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
        return new DataType(i, str, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DataType[] newArray(int i) {
        return new DataType[i];
    }
}
