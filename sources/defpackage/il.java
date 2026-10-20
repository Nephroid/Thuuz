package defpackage;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.data.a;
import com.google.android.gms.common.internal.safeparcel.a;

/* loaded from: classes.dex */
public class il implements Parcelable.Creator<a> {
    public static void a(a aVar, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, aVar.a);
        ke.a(parcel, 2, (Parcelable) aVar.b, i, false);
        ke.a(parcel, 3, aVar.c);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a createFromParcel(Parcel parcel) {
        int g;
        ParcelFileDescriptor parcelFileDescriptor;
        int i;
        int i2 = 0;
        int b = com.google.android.gms.common.internal.safeparcel.a.b(parcel);
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.a(a)) {
                case 1:
                    int i4 = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, a);
                    g = i4;
                    break;
                case 2:
                    ParcelFileDescriptor parcelFileDescriptor3 = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, a, ParcelFileDescriptor.CREATOR);
                    i = i3;
                    g = i2;
                    parcelFileDescriptor = parcelFileDescriptor3;
                    break;
                case 3:
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, a);
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, a);
                    g = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
            }
            i3 = i;
            parcelFileDescriptor2 = parcelFileDescriptor;
            i2 = g;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new com.google.android.gms.common.data.a(i3, parcelFileDescriptor2, i2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.google.android.gms.common.data.a[] newArray(int i) {
        return new com.google.android.gms.common.data.a[i];
    }
}
