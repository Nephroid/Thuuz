package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.multiplayer.ParticipantResult;

/* loaded from: classes.dex */
public class re implements Parcelable.Creator<ParticipantResult> {
    public static void a(ParticipantResult participantResult, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, participantResult.b(), false);
        ke.a(parcel, 1000, participantResult.a());
        ke.a(parcel, 2, participantResult.c());
        ke.a(parcel, 3, participantResult.d());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParticipantResult createFromParcel(Parcel parcel) {
        int i = 0;
        int b = a.b(parcel);
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    i2 = a.g(parcel, a);
                    break;
                case 3:
                    i = a.g(parcel, a);
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
        return new ParticipantResult(i3, str, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParticipantResult[] newArray(int i) {
        return new ParticipantResult[i];
    }
}
