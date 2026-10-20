package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ok implements Parcelable.Creator<Bucket> {
    public static void a(Bucket bucket, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, bucket.a());
        ke.a(parcel, 1000, bucket.h());
        ke.a(parcel, 2, bucket.b());
        ke.a(parcel, 3, (Parcelable) bucket.c(), i, false);
        ke.a(parcel, 4, bucket.d());
        ke.c(parcel, 5, bucket.e(), false);
        ke.a(parcel, 6, bucket.f());
        ke.a(parcel, 7, bucket.g());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bucket createFromParcel(Parcel parcel) {
        long j = 0;
        ArrayList arrayList = null;
        boolean z = false;
        int b = a.b(parcel);
        int i = 0;
        int i2 = 0;
        Session session = null;
        long j2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    j2 = a.i(parcel, a);
                    break;
                case 2:
                    j = a.i(parcel, a);
                    break;
                case 3:
                    session = (Session) a.a(parcel, a, Session.CREATOR);
                    break;
                case 4:
                    i2 = a.g(parcel, a);
                    break;
                case 5:
                    arrayList = a.c(parcel, a, DataSet.CREATOR);
                    break;
                case 6:
                    i = a.g(parcel, a);
                    break;
                case 7:
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
        return new Bucket(i3, j2, j, session, i2, arrayList, i, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bucket[] newArray(int i) {
        return new Bucket[i];
    }
}
