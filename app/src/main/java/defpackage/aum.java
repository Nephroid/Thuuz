package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.wallet.InstrumentInfo;

/* loaded from: classes.dex */
public class aum implements Parcelable.Creator<InstrumentInfo> {
    public static void a(InstrumentInfo instrumentInfo, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, instrumentInfo.a());
        ke.a(parcel, 2, instrumentInfo.b(), false);
        ke.a(parcel, 3, instrumentInfo.c(), false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public InstrumentInfo createFromParcel(Parcel parcel) {
        String str = null;
        int b = a.b(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
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
        return new InstrumentInfo(i, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public InstrumentInfo[] newArray(int i) {
        return new InstrumentInfo[i];
    }
}
