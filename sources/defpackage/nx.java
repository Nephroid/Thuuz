package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.realtime.internal.ParcelableCollaborator;

/* loaded from: classes.dex */
public class nx implements Parcelable.Creator<ParcelableCollaborator> {
    public static void a(ParcelableCollaborator parcelableCollaborator, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, parcelableCollaborator.a);
        ke.a(parcel, 2, parcelableCollaborator.b);
        ke.a(parcel, 3, parcelableCollaborator.c);
        ke.a(parcel, 4, parcelableCollaborator.d, false);
        ke.a(parcel, 5, parcelableCollaborator.e, false);
        ke.a(parcel, 6, parcelableCollaborator.f, false);
        ke.a(parcel, 7, parcelableCollaborator.g, false);
        ke.a(parcel, 8, parcelableCollaborator.h, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableCollaborator createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int b = a.b(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    z2 = a.c(parcel, a);
                    break;
                case 3:
                    z = a.c(parcel, a);
                    break;
                case 4:
                    str5 = a.o(parcel, a);
                    break;
                case 5:
                    str4 = a.o(parcel, a);
                    break;
                case 6:
                    str3 = a.o(parcel, a);
                    break;
                case 7:
                    str2 = a.o(parcel, a);
                    break;
                case 8:
                    str = a.o(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ParcelableCollaborator(i, z2, z, str5, str4, str3, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableCollaborator[] newArray(int i) {
        return new ParcelableCollaborator[i];
    }
}
